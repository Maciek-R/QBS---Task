package pl.java.formatter;

import static pl.java.formatter.Formatter.BUFFER_LENGTH;
import static pl.java.formatter.Formatter.STRING_TO_FIND;
import static pl.java.formatter.Formatter.STRING_TO_REPLACE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;

public class Formatter {
	
	public static int MESSAGE_OK = 0;
	public static int MESSAGE_PERMISSION_DENIED = -1;

	public static int BUFFER_LENGTH = 1024;
	public static String STRING_TO_FIND;
	public static String STRING_TO_REPLACE;
	public static int STRING_TO_REPLACE_LENGTH;
	
	private File fileInput;
	private File fileOutput;
	
	private int numberOfReplaces = 0;
	
	byte[] bytes;
	
	
	
	public Formatter(String fileInputName, String fileOutputName, String stringToFind, String stringToReplace){
		
		
		fileInput = new File(fileInputName);
		fileOutput = new File(fileOutputName);
		
		STRING_TO_FIND = stringToFind;
		STRING_TO_REPLACE = stringToReplace;
		STRING_TO_REPLACE_LENGTH = STRING_TO_REPLACE.length();
		
		bytes = new byte[BUFFER_LENGTH];
	}
	public Formatter(String fileInputName, String fileOutputName, String stringToFind, String stringToReplace, int bufferLength){
		fileInput = new File(fileInputName);
		fileOutput = new File(fileOutputName);
		
		STRING_TO_FIND = stringToFind;
		STRING_TO_REPLACE = stringToReplace;
		STRING_TO_REPLACE_LENGTH = STRING_TO_REPLACE.length();
		BUFFER_LENGTH = bufferLength;
		
		bytes = new byte[BUFFER_LENGTH];
	}
	
	public int format(){
		
				try (RandomAccessFile raf = new RandomAccessFile(fileInput, "rw");
					 FileOutputStream fos = new FileOutputStream(fileOutput);){
					
					int len;
					while ((len = raf.read(bytes, 0, BUFFER_LENGTH)) != -1){
						
						
						String s1 = new String(bytes, 0, len);
						//System.out.println(s1);
						
					//	int indexBehindLastFoundedWord = s1.lastIndexOf(STRING_TO_FIND);
					//	if(indexBehindLastFoundedWord!=-1)
					//		indexBehindLastFoundedWord+=STRING_TO_FIND.length();
						
					//	System.out.println("INDEX: " +indexBehindLastFoundedWord);
						int indexBehindLastFoundedWord = findIndexBehindLastFoundedWord(s1);
						int count = countNumberOfReplaces(s1);
						numberOfReplaces+=count;
						
						
						int indexWhereStartToSearch = BUFFER_LENGTH - (STRING_TO_FIND.length() - 1);
					//	System.out.println(indexBehindLastFoundedWord);
					//	System.out.println(indexWhereStartToSearch);
						
						
						
						
						
						
						int transition = BUFFER_LENGTH - indexWhereStartToSearch;
						
						if(indexBehindLastFoundedWord != -1){
							if(indexBehindLastFoundedWord >= indexWhereStartToSearch){
								transition = BUFFER_LENGTH - indexBehindLastFoundedWord;	//o tyle pozycji trzeba przesun¹æ kursor w lewo
								
								//zamien wszystkie s1 na poprawne i wrzuc do pliku obok
							//	if(len == BUFFER_LENGTH){
									String spom = s1.substring(0, indexBehindLastFoundedWord);
									String ss = spom.replaceAll(STRING_TO_FIND, STRING_TO_REPLACE);
							
									fos.write(ss.getBytes());
								//	System.out.println("doda: "+ss);
								//}
							/*	else{
									String ss = s1.replaceAll(STRING_TO_FIND, STRING_TO_REPLACE);
									fos.write(ss.getBytes());
									System.out.println("doda: "+ss);
								}*/
								//System.out.println("aaa" + ss);
								
								
							}
							else{
							//	z = BUFFER_LENGTH - bbb;
								if(len == BUFFER_LENGTH){
									String spom = s1.substring(0, indexWhereStartToSearch);
								//	System.out.println("spom: "+spom);
									String ss = spom.replaceAll(STRING_TO_FIND, STRING_TO_REPLACE);
									fos.write(ss.getBytes());
							//		System.out.println("dodaje: "+ss);
								}
								else{
									String ss = s1.replaceAll(STRING_TO_FIND, STRING_TO_REPLACE);
									fos.write(ss.getBytes());
							//		System.out.println("doda: "+ss);
								}
							}
						}
						else{
							String ss;
							if(len==BUFFER_LENGTH){
								String spom = s1.substring(0, indexWhereStartToSearch);
								ss = spom.replaceAll(STRING_TO_FIND, STRING_TO_REPLACE);
							//	System.out.println(ss);
							}
							else{
								ss = s1.replaceAll(STRING_TO_FIND, STRING_TO_REPLACE);
							//	System.out.println(ss);
							}
							fos.write(ss.getBytes());
						//	System.out.println("doda: "+ss);
						//	System.out.println(ss);
						}
						
						if(len!=BUFFER_LENGTH){	//koniec pliku, to nie przesuwaj ju¿ kursora
							//System.out.println("tu");
							break;
						}
						
						raf.seek(raf.getFilePointer()-transition);
						
					}
					
					
					
					
				} catch (FileNotFoundException e) {
				//	System.out.println("Nie masz odpowiednich uprawnieñ");
					System.out.println("Brak uprawnien");
					return MESSAGE_PERMISSION_DENIED;
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
					//System.out.println("Nie masz odpowiednich uprawnieñ");
					return -2;
				}
				
				changeName();
				return MESSAGE_OK;			
		
	}
	private int findIndexBehindLastFoundedWord(String s){
		int fromIndex = 0;
		int lastIndex = -1;
		while((fromIndex = s.indexOf(STRING_TO_FIND, fromIndex))!=-1){
			fromIndex += STRING_TO_FIND.length();
			lastIndex = fromIndex;
		}
		
		//toDo
		//tu moge policzyc liczbe zamian, a raczej z tego skorzystac
		//
		return lastIndex;
	}
	private int countNumberOfReplaces(String s){
		int fromIndex = 0;
		int count = 0;
		while((fromIndex = s.indexOf(STRING_TO_FIND, fromIndex))!=-1){
			fromIndex += STRING_TO_FIND.length();
			++count;
		}
		return count;
	}
	
	private void changeName(){
		System.out.println("zmiana");
		String name = fileInput.getName();
		System.out.println(name);
		
			boolean x = fileInput.delete();
		
		
		fileOutput.renameTo(new File(fileOutput.getParentFile()+"\\"+name));
		System.out.println(fileOutput.getParentFile());
	}
	public int getNumberOfReplaces(){
		return numberOfReplaces;
	}
}
