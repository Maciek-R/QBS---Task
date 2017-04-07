package pl.java.swing;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args){
		EventQueue.invokeLater(() -> {
			Frame myFrame = new Frame();
			myFrame.setVisible(true);
		});
		
	/*	try {
			File f = File.createTempFile("tmp", null, new File("."));
			System.out.println(f.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	
		
		
		/*byte[] b = new byte[]{'B', 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13 , 14,22,31,29, 33, 48, 0, 1, 10, 11, 13, 12, 14, 15, (byte) (149 & 0xFF)};
		
		String s = new String(b);
		System.out.println(s);
		
		byte[] bytes = s.getBytes();
		for(int i=0; i<b.length; ++i){
			System.out.print(bytes[i] + " ");
		}
		
		System.out.println();
		System.out.println();
		System.out.println(Byte.toString((byte) 130));
		System.out.println();
		byte by = (byte) 250;
		int i = by & 0xFF;
		
		//String ss = new String(by);
		
		System.out.println(i);*/
	}
	
}
