package com.arahansa.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBackground {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ServerGUI gui;
    private String msg;

    public void setGui(ServerGUI gui) {
        this.gui = gui;
    }

    public static void main(String[] args) throws IOException {
        ServerBackground serverBackground = new ServerBackground();
        serverBackground.setting();
    }

    public void setting() throws IOException {
        serverSocket = new ServerSocket(7777);
        System.out.println("서버 대기중...");
        socket = serverSocket.accept();
        System.out.println(socket.getInetAddress() + "에서 접속했습니다.");

        // 통로 생성
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());

        msg = in.readUTF();
        System.out.println("클라로부터 온 메시지 : " + msg);

        gui.appendMsg(msg);

        // 통로가 연결되어 있는 한, 클라로부터 온 메시지 수신 및 GUI 에 표시
        while(in != null) {
            msg = in.readUTF();
            gui.appendMsg(msg);
        }
    }

    /*
     * 클라 딴으로 메시지 전송
     */
    public void sendMessage(String msg) throws IOException {
        out.writeUTF("서버 : " + msg);
    }

    

    
    
}
