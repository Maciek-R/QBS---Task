package pl.java.swing;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args){
		EventQueue.invokeLater(() -> {
			Frame myFrame = new Frame();
			myFrame.setVisible(true);
		});
		
	}
	
}
