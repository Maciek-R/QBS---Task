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
		byte[] find = new byte[]{'X','A'};
		byte[] replace = new byte[]{'X', 'A', 'X', 'A'};
		String shouldBe = "XAXAMXAXA";
		
	/*	byte[] b = new byte[]{'X', 'A','X', 'A', 'M', 'X', 'A','X', 'A',};
		
		System.out.println("BYTES: " + new String(b) + " ");
		System.out.println("TAKIE SAME: " + new String(b).equals(shouldBe));*/
		
		for(int i=find.length; i <= find.length*5; ++i){
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
		byte[] find = new byte[]{'A','B','A'};
		byte[] replace = new byte[]{'X'};
		String shouldBe = "XBXBXBA";
		
		
		for(int i=find.length; i <= find.length*5; ++i){
			createFileAndAdd("ABABABABABABA");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, 15);
			formatter.format();
		
			String result = getResult();
		
			assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));
		}
	}
	@org.junit.Test
	public void test3() {
		byte[] find = new byte[]{'A','B','A'};
		byte[] replace = new byte[]{'X'};
		String shouldBe = "X   X";
		
		for(int i=find.length; i <= find.length*5; ++i){
			createFileAndAdd("ABA   ABA");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
			String result = getResult();
			assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test4() {
		byte[] find = new byte[]{'B','C','B'};
		byte[] replace = new byte[]{'P'};
		String shouldBe = "APCCPAPCABC";
		
		for(int i=find.length; i <= find.length*5; ++i){
			createFileAndAdd("ABCBCCBCBABCBCABC");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
		
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test5() {
		byte[] find = new byte[]{'p','X'};
		byte[] replace = new byte[]{'P','X','P'};
		String shouldBe = "XP   APXPPXPap XPXPXp";
		
		for(int i=find.length; i <= find.length*5; ++i){
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
		byte[] find = new byte[]{' ',' '};
		byte[] replace = new byte[]{'X'};
		String shouldBe = "X' 'X";
		
		for(int i=find.length; i <= find.length*5; ++i){
			createFileAndAdd("  ' '  ");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test7() {
		byte[] find = new byte[]{'a'};;
		byte[] replace = new byte[]{'z'};
		String shouldBe ="zbccbcbbzbcbzbcbbcbzbcbbzbczbcbbzczcbzbcbbbcbbczbcbzbcbzbcbbcbzbczbbczbbbzcb";
		
		for(int i=find.length; i <= find.length*5; ++i){
			createFileAndAdd("abccbcbbabcbabcbbcbabcbbabcabcbbacacbabcbbbcbbcabcbabcbabcbbcbabcabbcabbbacb");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test8() {
		byte[] find = new byte[]{'a'};;
		byte[] replace = new byte[]{'a','a'};
		String shouldBe ="aabccbcbbaabcbaabcbbcbaabcbbaabcaabcbbaacaacbaabcbbbcbbcaabcbaabcbaabcbbcbaabcaabbcaabbbaacb";
		
		for(int i=find.length; i <= find.length*5; ++i){
			createFileAndAdd("abccbcbbabcbabcbbcbabcbbabcabcbbacacbabcbbbcbbcabcbabcbabcbbcbabcabbcabbbacb");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test9() {
		byte[] find = new byte[]{'a','d','f', 'j', 'g'};
		byte[] replace = new byte[]{'2','3','4','3','t','4','j','k','4','3'};
		String shouldBe ="sdljfadlsjg";
		
		for(int i=find.length; i <= find.length*5; ++i){
			createFileAndAdd("sdljfadlsjg");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test10() {
		byte[] find = new byte[]{'a','d','g','b','f'};;
		byte[] replace = new byte[]{'^'};
		String shouldBe ="12453";
		
		for(int i=find.length; i <= find.length; ++i){
			createFileAndAdd("12453");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test11() {
		byte[] find = new byte[]{'A','B'};
		byte[] replace = new byte[]{'A','B', 'B', 'A'};
		String shouldBe ="ABBABAABBACB";
		
		for(int i=find.length; i <= find.length*5; ++i){
			createFileAndAdd("ABBAABCB");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test12() {
		byte[] find = new byte[]{'A','B','B','A','A','B','C','B'};
		byte[] replace = new byte[]{' '};
		String shouldBe =" ";
		
		for(int i=find.length; i <= find.length*5; ++i){
			createFileAndAdd("ABBAABCB");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test13() {
		byte[] find = new byte[]{'s','d'};
		byte[] replace = new byte[]{'d','u','[','a'};
		String shouldBe ="in sdu[agifadhdkhifdgfdjaifa ";
		
		for(int i=find.length; i <= find.length*5; ++i){
			createFileAndAdd("in ssdgifadhdkhifdgfdjaifa ");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test14() {
		byte[] find = new byte[]{' '};
		byte[] replace = new byte[]{' ',' ',' ',' ',' ',' '};
		String shouldBe ="      ";
		
		for(int i=find.length; i <= find.length*5; ++i){
			createFileAndAdd(" ");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test15() {
		byte[] find = new byte[]{'J','a','n'};
		byte[] replace = new byte[]{'M','i','s','t','r','z'};
		String shouldBe ="Mistrz Antoniak";
		
		for(int i=find.length; i <= find.length*5; ++i){
			createFileAndAdd("Jan Antoniak");
			Formatter formatter = new Formatter("test1.txt", "test2.txt", find, replace, i);
			formatter.format();
		
				String result = getResult();
				assertTrue(shouldBe.equals(result.substring(0, shouldBe.length())));		
		}
	}
	@org.junit.Test
	public void test16() {
		byte[] find = new byte[]{'\n'};
		byte[] replace = new byte[]{'x'};
		String shouldBe ="\t\txxx";
		
		for(int i=find.length; i <= find.length*5; ++i){
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
