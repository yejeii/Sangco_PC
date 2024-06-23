package com.arahansa.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.arahansa.view.MainFrameView;

public class PCServerBackground {

    private ServerSocket serverSocket;
    private Socket socket;
    private MainFrameView gui;

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

    public void setGui(MainFrameView gui) {
        this.gui = gui;
    }

}
