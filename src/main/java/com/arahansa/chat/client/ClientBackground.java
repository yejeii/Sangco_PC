package com.arahansa.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientBackground {
    
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ClientGUI gui;
    private String msg;

    private String nickName;

    public static void main(String[] args) throws IOException {
        ClientBackground clientBackground = new ClientBackground();
        clientBackground.connect();

    }

    public void connect()  {
        try {
            socket = new Socket("127.0.0.1", 7777);
            System.out.println("서버와 연결됨");

            // 통로 생성
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            // 클라 접속하자마자 닉네임 전송, 서버가 이를 닉네임으로 인식, Map 에 저장
            out.writeUTF(nickName); 
            System.out.printf("%s : 접속 완료 \n", nickName);

            // 통로가 연결되어 있는 한, 서버로부터 온 메시지 수신 및 GUI 에 표시
            while(in != null) {
                msg = in.readUTF();
                gui.apppendMsg(msg);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void setGui(ClientGUI gui) {
        this.gui = gui;
    }

    /*
     * 서버 딴으로 메시지 전송
     */
	public void sendMessage(String cliMsg) throws IOException {
		out.writeUTF(cliMsg);	
    }

    public void setNickname(String nickName) {
        this.nickName = nickName;
    }

}
