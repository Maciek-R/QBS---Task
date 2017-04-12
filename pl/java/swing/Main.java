package pl.java.swing;

import java.awt.EventQueue;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class Main {

	
	public static void main(String[] args){
		EventQueue.invokeLater(() -> {
			Frame myFrame = new Frame();
			myFrame.setVisible(true);
		});
		
		
	}
}
