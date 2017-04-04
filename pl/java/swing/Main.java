package pl.java.swing;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args){
		EventQueue.invokeLater(() -> {
			Frame myFrame = new Frame();
			myFrame.setVisible(true);
		});
	/*	byte[] b = new byte[]{'B', 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13 , 14,22,31,29, 33, 48, 0, 1, 10, 11, 13, 12, 14, 15};
		
		String s = new String(b);
		System.out.println(s);
		
		byte[] bytes = s.getBytes();
		for(int i=0; i<b.length; ++i){
			System.out.print(bytes[i] + " ");
		}*/
	}
	
}
