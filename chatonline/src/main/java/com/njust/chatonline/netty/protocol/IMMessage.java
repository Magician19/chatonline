package com.njust.chatonline.netty.protocol;

import org.msgpack.annotation.Message;


/**
 * 自定义消息实体类
 *
 */
//自定义登录命令格式  [LOGIN][时间戳][username]
//自定义消息格式 [CHAT][时间戳][username][头像][消息内容]
//自定义花命令格式  [FLOWER][时间戳][username]
@Message
public class IMMessage{

	private String addr;		//IP地址及端口
	private String cmd;		//命令类型[LOGIN]或者[SYSTEM]或者[LOGOUT]
	private long time;		//命令发送时间
	private int online;		//当前在线人数
	private String sender;  //发送人
	private String headPic;
	private String receiver;	//接收人
	private String content;	//消息内容
	private String roomId;//房间号

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public IMMessage(){}

	public IMMessage(String cmd,long time,int online,String content,String roomId){
		this.cmd = cmd;
		this.time = time;
		this.online = online;
		this.content = content;
		this.roomId = roomId;
	}

	public IMMessage(String cmd,long time,String sender,String roomId){
		this.cmd = cmd;
		this.time = time;
		this.sender = sender;
		this.roomId = roomId;
	}
	public IMMessage(String cmd,long time,String sender,String content,String roomId){
		this.cmd = cmd;
		this.time = time;
		this.sender = sender;
		this.content = content;
		this.roomId = roomId;
	}

	public IMMessage(String cmd,long time,String sender,String content,String headPic,String roomId){
		this.cmd = cmd;
		this.time = time;
		this.sender = sender;
		this.content = content;
		this.headPic = headPic;
		this.roomId = roomId;
	}
	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}

	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
}
