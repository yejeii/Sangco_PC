package com.arahansa.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServerBackground {

    private ServerSocket serverSocket;
    private Socket socket;
    private ServerGUI gui;
    private String msg;

    // 사용자 정보를 저장하는 Map
    private Map<String, DataOutputStream> clientsMap = new HashMap<>();

    public void setGui(ServerGUI gui) {
        this.gui = gui;
    }

    public static void main(String[] args) throws IOException {
        ServerBackground serverBackground = new ServerBackground();
        serverBackground.setting();
    }

    public void setting() throws IOException {
        Collections.synchronizedMap(clientsMap);    // 교통정리

        serverSocket = new ServerSocket(7777); 

        // 서버 역할 : 들어오는 사용자를 받아 쓰레드 리시버를 생성
        while (true) { 
            System.out.println("서버 대기중...");

            socket = serverSocket.accept();
            System.out.println(socket.getInetAddress() + "에서 접속했습니다.");

            // 새로운 사용자 쓰레드 생성, 소켓정보 input
            Receiver receiver = new Receiver(socket);
            receiver.start();
        }

    }

    // 클라이언트 저장 및 삭제
    public void addClient(String nick, DataOutputStream out) {
        String addClientMsg = nick + "님이 접속하셨습니다.";
        gui.appendMsg(addClientMsg);
        sendMessage(addClientMsg);
        clientsMap.put(nick, out);
    }
    public void removeClient(String nick) {
        String removeClientMsg = nick + "님이 퇴장하셨습니다.";
        gui.appendMsg(removeClientMsg);
        sendMessage(removeClientMsg);
        clientsMap.remove(nick);
    }
    
    /*
     * 클라 딴으로 메시지 전송
     */
    public void sendMessage(String msg) {
        Iterator<String> it = clientsMap.keySet().iterator();
        String key = "";

        while(it.hasNext()) {
            try {
                key = it.next();
                clientsMap.get(key).writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } 
    

    class Receiver extends Thread {

        private DataInputStream in;
        private DataOutputStream out;
        private String nick;

        /*
         * 네트워크 소켓을 받아 계속 듣고, 요청하는 책임
         */
        public Receiver(Socket socket) throws IOException {
            
            // 통로 생성
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            // 클라이언트 아이디 get
            nick = in.readUTF();
            addClient(nick, out);
        }
 
        @Override
        public void run() {
            try {
                // 통로가 연결되어 있는 한, 클라로부터 온 메시지 수신 및 클라 GUI 로 전송
                while(in != null) {
                    msg = in.readUTF();
                    sendMessage(msg);
                    gui.appendMsg(msg);
                }
            } catch (IOException e) {
                // 사용접속종료 시 여기서 에러 발생 == exit
                // 리무브 클라이언트 처리
                removeClient(nick);
                e.printStackTrace();

            }
            
        }
        
    }
    
    
}
