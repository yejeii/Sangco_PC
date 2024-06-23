package com.arahansa;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PCServerBackground {

    private ServerSocket serverSocket;
    private Socket socket;
    private MainFrameViewTest gui;

    public void setting(){
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("waiting.....");
			socket = serverSocket.accept();
			gui.connectClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public void setGui(MainFrameViewTest gui) {
        this.gui = gui;
    }

}
