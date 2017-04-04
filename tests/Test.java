package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;


import pl.java.formatter.Formatter;

public class Test {

	private void createFileAndAdd(String content){
		File file = new File("test1.txt");
		try(FileOutputStream fos = new FileOutputStream(file)){
			
			fos.write(content.getBytes());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getResult(){
		File file = new File("test2.txt");
		try(RandomAccessFile raf = new RandomAccessFile(file, "r")){
			
			byte[] bytes = new byte[1024]; 
			raf.read(bytes);
			
			
			return new String(bytes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	@org.junit.Test
	public void test1() {
		String find = "XA";
		String replace = "XAXA";
		String shouldBe = "XAXAMXAXA";
		
	/*	byte[] b = new byte[]{'X', 'A','X', 'A', 'M', 'X', 'A','X', 'A',};
		
		System.out.println("BYTES: " + new String(b) + " ");
		System.out.println("TAKIE SAME: " + new String(b).equals(shouldBe));*/
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("XAMXA");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
			
			String result = getResult();
			System.out.println(result);
			assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));
		}
	/*	if(shouldBe.equals(result.substring(0, shouldBe.length()))){
			System.out.println("dobrze");
		}
		else{
			System.out.println("zle");
		}*/
		
		
	}
	@org.junit.Test
	public void test2() {
		String find = "ABA";
		String replace = "X";
		String shouldBe = "XBXBXBA";
		
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("ABABABABABABA");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
			String result = getResult();
		
			assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));
		}
	}
	@org.junit.Test
	public void test3() {
		String find = "ABA";
		String replace = "X";
		String shouldBe = "X   X";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("ABA   ABA");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
			String result = getResult();
			assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test4() {
		String find = "BCB";
		String replace = "P";
		String shouldBe = "APCCPAPCABC";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("ABCBCCBCBABCBCABC");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
		
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test5() {
		String find = "pX";
		String replace = "PXP";
		String shouldBe = "XP   APXPPXPap XPXPXp";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("XP   ApXpXap XpXXp");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
			String result = getResult();
			//System.out.println("RESULT: " + result.substring(0, shouldBe.length()));
			//System.out.println("SHOULD: " + shouldBe);
			assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));	
		}
	}
	@org.junit.Test
	public void test6() {
		String find = "  ";
		String replace = "X";
		String shouldBe = "X' 'X";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("  ' '  ");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test7() {
		String find = "a";
		String replace = "z";
		String shouldBe ="zbccbcbbzbcbzbcbbcbzbcbbzbczbcbbzczcbzbcbbbcbbczbcbzbcbzbcbbcbzbczbbczbbbzcb";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("abccbcbbabcbabcbbcbabcbbabcabcbbacacbabcbbbcbbcabcbabcbabcbbcbabcabbcabbbacb");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test8() {
		String find = "a";
		String replace = "aa";
		String shouldBe ="aabccbcbbaabcbaabcbbcbaabcbbaabcaabcbbaacaacbaabcbbbcbbcaabcbaabcbaabcbbcbaabcaabbcaabbbaacb";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("abccbcbbabcbabcbbcbabcbbabcabcbbacacbabcbbbcbbcabcbabcbabcbbcbabcabbcabbbacb");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test9() {
		String find = "adfjg";
		String replace = "2343t4jk43";
		String shouldBe ="sdljfadlsjg";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("sdljfadlsjg");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test10() {
		String find = "adgbf";
		String replace = "^";
		String shouldBe ="12453";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("12453");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test11() {
		String find = "AB";
		String replace = "ABBA";
		String shouldBe ="ABBABAABBACB";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("ABBAABCB");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test12() {
		String find = "ABBAABCB";
		String replace = " ";
		String shouldBe =" ";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("ABBAABCB");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test13() {
		String find = "sd";
		String replace = "du[a";
		String shouldBe ="in sdu[agifadhdkhifdgfdjaifa ";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("in ssdgifadhdkhifdgfdjaifa ");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test14() {
		String find = " ";
		String replace = "      ";
		String shouldBe ="      ";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd(" ");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test15() {
		String find = "Jan";
		String replace = "Mistrz";
		String shouldBe ="Mistrz Antoniak";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("Jan Antoniak");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test16() {
		String find = "\n";
		String replace = "x";
		String shouldBe ="\t\txxx";
		
		for(int i=find.length(); i <= find.length()*5; ++i){
			createFileAndAdd("\t\t\n\n\n");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	 
	/*@org.junit.Test
	public void test6() {
		createFileAndAdd("XP   ApXpXap XpXXp");
		String find = "pX";
		String replace = "PXP";
		String shouldBe = "XP   APXPPXPap XPXPXp";
		
		for(int i=0; i < 5; ++i){
			
			
		}
	}*/

}
