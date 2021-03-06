package com.njust.chatonline.netty.process;

import com.alibaba.fastjson.JSONObject;
import com.njust.chatonline.dao.RoomMapper;
import com.njust.chatonline.entity.Room;
import com.njust.chatonline.netty.protocol.IMDecoder;
import com.njust.chatonline.netty.protocol.IMEncoder;
import com.njust.chatonline.netty.protocol.IMMessage;
import com.njust.chatonline.netty.protocol.IMP;
import com.njust.chatonline.service.RoomService;
import com.njust.chatonline.service.impl.RoomServiceImpl;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息处理类
 */
public class MsgProcessor {
    //记录在线用户
    private static ChannelGroup onlineUsers = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private List<List<DefaultChannelGroup>> groupList = new ArrayList<>();
    private IMDecoder decoder = new IMDecoder();
    private IMEncoder encoder = new IMEncoder();
    public static Map<String,ChannelGroup> users = new HashMap<>();
    //channel自定义属性
    private final AttributeKey<String> USERNAME = AttributeKey.valueOf("username");
    private final AttributeKey<String> HEAD_PIC = AttributeKey.valueOf("headPic");
    private final AttributeKey<String> IP_ADDR = AttributeKey.valueOf("ipAddr");
    private final AttributeKey<JSONObject> ATTRS = AttributeKey.valueOf("attrs");
    private final AttributeKey<String> ROOMID = AttributeKey.valueOf("roomId");


    public void process(Channel client, String msg) {
        //将字符串解析为自定义格式
        IMMessage request = decoder.decode(msg);
        if (null == request) {
            return;
        }

        //获取消息发送者
        String username = request.getSender();
        System.out.println(username);
        String roomId = request.getRoomId();
        //判断如果是登录动作，就往onlineUsers中加入一条数据
        if (IMP.LOGIN.getName().equals(request.getCmd())) {
            client.attr(IP_ADDR).getAndSet("");
            client.attr(USERNAME).getAndSet(request.getSender());
            client.attr(HEAD_PIC).getAndSet(request.getHeadPic());
            client.attr(ROOMID).getAndSet(roomId);
            if(users.get(roomId) == null || users.get(roomId).size() == 0){
                ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
                channels.add(client);
                users.put(roomId,channels);
            }else {
                users.get(roomId).add(client);
            }
            onlineUsers.add(client);
            //像所有用户发送系统消息
            for (Channel channel : users.get(roomId)) {//向其他人发送消息

                if (channel != client && channel.attr(ROOMID).get().equals(client.attr(ROOMID).get())) {

                    //自定义系统消息格式 [system][时间戳][用户数量][消息内容]
                    request = new IMMessage(IMP.SYSTEM.getName(), sysTime(), users.get(roomId).size(), username + " 加入聊天室！", roomId);
                    //自定义IM协议解码
                    String text = encoder.encode(request);
                    //发送消息
                    channel.writeAndFlush(new TextWebSocketFrame(text));
                }
                //向自己发送消息
                if(channel == client){
                    request = new IMMessage(IMP.SYSTEM.getName(), sysTime(), users.get(roomId).size(), username + " 欢迎进入聊天室！", roomId);
                      //自定义IM协议解码
                    String text = encoder.encode(request);
                    //发送消息
                    channel.writeAndFlush(new TextWebSocketFrame(text));
                }

            }

        }
        //如果是登出
        else if (IMP.LOGOUT.getName().equals(request.getCmd())) {
            logout(client);
        }
        //如果是聊天信息
        else if (IMP.CHAT.getName().equals(request.getCmd())) {

            for (Channel channel : users.get(roomId)) {//向其他人发送消息
                if (channel != client && channel.attr(ROOMID).get().equals(client.attr(ROOMID).get())) {

                    request.setSender(username);
                    //自定义IM协议解码
                    String text = encoder.encode(request);
                    //发送消息
                    channel.writeAndFlush(new TextWebSocketFrame(text));
                }
                //向自己发送消息
                if(channel == client){
                    request.setSender("MY_SELF");
                    //自定义IM协议解码
                    String text = encoder.encode(request);
                    //发送消息
                    channel.writeAndFlush(new TextWebSocketFrame(text));

                }

            }
        }
        //如果是鲜花
        else if (IMP.FLOWER.getName().equals(request.getCmd())) {
            JSONObject attrs = getAttrs(client);
            long currTime = sysTime();
            if (null != attrs) {
                long lastTime = attrs.getLongValue("lastFlowerTime");
                //60秒之内不允许重复刷鲜花
                int seconds = 10;
                long sub = currTime - lastTime;
                if (sub < 1000 * seconds) {
                    request.setSender("MY_SELF");
                    request.setCmd(IMP.SYSTEM.getName());
                    request.setContent("您送鲜花太频繁," + (seconds - Math.round(sub / 1000)) + "秒后再试");
                    String content = encoder.encode(request);
                    client.writeAndFlush(new TextWebSocketFrame(content));
                    return;
                }
            }

            //正常送花
            for (Channel channel : onlineUsers) {
                if (channel == client) {
                    request.setSender("MY_SELF");
                    request.setContent("你给大家送了一波鲜花雨");
                    setAttrs(client, "lastFlowerTime", currTime);
                } else {
                    request.setSender(getNickName(client));
                    request.setContent(getNickName(client) + "送来一波鲜花雨");
                }
                request.setTime(sysTime());

                String content = encoder.encode(request);
                channel.writeAndFlush(new TextWebSocketFrame(content));
            }
        }


    }


    /**
     * 获取用户昵称
     *
     * @param client
     * @return
     */
    public String getNickName(Channel client) {
        return client.attr(USERNAME).get();
    }

    /**
     * 获取用户远程IP地址
     *
     * @param client
     * @return
     */
    public String getAddress(Channel client) {
        return client.remoteAddress().toString().replaceFirst("/", "");
    }

    /**
     * 获取扩展属性
     *
     * @param client
     * @return
     */
    public JSONObject getAttrs(Channel client) {
        try {
            return client.attr(ATTRS).get();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取扩展属性
     *
     * @param client
     * @return
     */
    private void setAttrs(Channel client, String key, Object value) {
        try {
            JSONObject json = client.attr(ATTRS).get();
            json.put(key, value);
            client.attr(ATTRS).set(json);
        } catch (Exception e) {
            JSONObject json = new JSONObject();
            json.put(key, value);
            client.attr(ATTRS).set(json);
        }
    }

    /**
     * 登出通知
     *
     * @param client
     * @return
     */
    public void logout(Channel client) {
        IMMessage request = new IMMessage();
        request.setSender(client.attr(USERNAME).get());
        request.setCmd(IMP.SYSTEM.getName());
        request.setOnline(onlineUsers.size());
        request.setContent(request.getSender() + " 退出聊天室！");
        //向所有用户发送系统消息
        for (Channel channel : onlineUsers) {//向其他人发送消息
            if (channel != client) {
                //自定义IM协议解码
                String text = encoder.encode(request);
                //发送消息
                channel.writeAndFlush(new TextWebSocketFrame(text));
            }
        }

        onlineUsers.remove(client);
    }

    private long sysTime() {
        return System.currentTimeMillis();
    }

}
