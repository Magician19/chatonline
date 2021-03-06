//package com.njust.chatonline.netty;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//
//public class WebSocketServer {
//    public static void main(String[] args) throws Exception {
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
//            ServerBootstrap b = new ServerBootstrap();
//            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
//                    .childHandler(new WebSocketServerInitializer()).option(ChannelOption.SO_BACKLOG, 1024)
//                    .childOption(ChannelOption.SO_KEEPALIVE, true);
//
//            System.out.println("WebsocketChatServer 启动了");
//            // 绑定端口，开始接收进来的连接
//            ChannelFuture f = b.bind(8081).sync();
//            // 让服务器不会立马关闭
//            f.channel().closeFuture().sync();
//        } finally {
//            workerGroup.shutdownGracefully();
//            bossGroup.shutdownGracefully();
//            System.out.println("WebsocketChatServer 关闭了 ");
//        }
//    }
//}
