package pl.java.formatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.internal.ArrayComparisonFailure;

public class Formatter {
	
	public static int MESSAGE_OK = 0;
	public static int MESSAGE_PERMISSION_DENIED = -1;

	public static int BUFFER_LENGTH = 1024;
	public static byte[] STRING_TO_FIND;
	public static byte[] STRING_TO_REPLACE;
	public static int STRING_TO_FIND_LENGTH;
	public static int STRING_TO_REPLACE_LENGTH;
	
	private File fileInput;
	private File fileOutput;
	
	private int numberOfReplaces = 0;
	
	byte[] bytes;
	
	
	
	public Formatter(String fileInputName, String fileOutputName, byte[] stringToFind, byte[] stringToReplace){
		
		
		fileInput = new File(fileInputName);
		fileOutput = new File(fileOutputName);
		
		STRING_TO_FIND = stringToFind;
		STRING_TO_REPLACE = stringToReplace;
		STRING_TO_FIND_LENGTH = STRING_TO_FIND.length;
		STRING_TO_REPLACE_LENGTH = STRING_TO_REPLACE.length;
		
		bytes = new byte[BUFFER_LENGTH];
	}
	public Formatter(String fileInputName, String fileOutputName, byte[] stringToFind, byte[] stringToReplace, int bufferLength){
		fileInput = new File(fileInputName);
		fileOutput = new File(fileOutputName);
		
		STRING_TO_FIND = stringToFind;
		STRING_TO_REPLACE = stringToReplace;
		STRING_TO_FIND_LENGTH = STRING_TO_FIND.length;
		STRING_TO_REPLACE_LENGTH = STRING_TO_REPLACE.length;
		BUFFER_LENGTH = bufferLength;
		
		bytes = new byte[BUFFER_LENGTH];
	}
	
	public int format(){
		
				try (RandomAccessFile raf = new RandomAccessFile(fileInput, "rw");
					 FileOutputStream fos = new FileOutputStream(fileOutput);){
					int len=0;
					while ((len = raf.read(bytes, 0, BUFFER_LENGTH)) != -1){
						
						//String s1 = new String(bytes, 0, len);
						
						int indexBehindLastFoundedWord = findIndexBehindLastFoundedWord(bytes, len);
						int count = countNumberOfReplaces(bytes, len);
						numberOfReplaces+=count;
						
						
						int indexWhereStartToSearch = BUFFER_LENGTH - (STRING_TO_FIND_LENGTH - 1);	
						
						int transition = BUFFER_LENGTH - indexWhereStartToSearch;
						
						if(indexBehindLastFoundedWord != -1){
							if(indexBehindLastFoundedWord >= indexWhereStartToSearch){
								transition = BUFFER_LENGTH - indexBehindLastFoundedWord;	//o tyle pozycji trzeba przesun¹æ kursor w lewo
								
								
									//String spom = s1.substring(0, indexBehindLastFoundedWord);
									byte[] spom = substring(bytes, 0, indexBehindLastFoundedWord);
									byte[] ss = replaceAll(spom, spom.length);
							
									
									
									//fos.write(ss.getBytes());
									fos.write(ss);
							}
							else{
								if(len == BUFFER_LENGTH){
									
									//String spom = s1.substring(0, indexWhereStartToSearch);
									//String ss = spom.replaceAll(STRING_TO_FIND, STRING_TO_REPLACE);
									//fos.write(ss.getBytes());
									byte[] spom = substring(bytes, 0, indexWhereStartToSearch);
									byte[] ss = replaceAll(spom, spom.length);
									fos.write(ss);
								}
								else{
								//	String ss = s1.replaceAll(STRING_TO_FIND, STRING_TO_REPLACE);
									//fos.write(ss.getBytes());
									
								
									
									byte[] ss = replaceAll(bytes, len);
									fos.write(ss);
								}
							}
						}
						else{//nie znaleziono podanego ci¹gu
							byte[] ss;
							if(len==BUFFER_LENGTH){
								//String spom = s1.substring(0, indexWhereStartToSearch);
								//ss = spom;
								byte[] spom = substring(bytes, 0, indexWhereStartToSearch);
								ss = spom;
								
								
								//ss = spom.replaceAll(STRING_TO_FIND, STRING_TO_REPLACE);
							}
							else{
							//	ss = s1;
								ss = substring(bytes, 0, len);
								
								
								//ss = s1.replaceAll(STRING_TO_FIND, STRING_TO_REPLACE);
							}
							//fos.write(ss.getBytes());
							fos.write(ss);
						}
						
						if(len!=BUFFER_LENGTH){	//koniec pliku, to nie przesuwaj ju¿ kursora
							break;
						}
						
						raf.seek(raf.getFilePointer()-transition);
						
					}
					
					
					
					
				} catch (FileNotFoundException e) {
					System.out.println("Brak uprawnien");
					return MESSAGE_PERMISSION_DENIED;
					
				} catch (IOException e) {
					return -2;
				}
				
				changeName();
				return MESSAGE_OK;			
		
	}
	
	byte[] substring(byte[] bytes, int from, int to){
		
		
		int size = to - from;
		byte[] b = new byte[size];
		
		int ind = 0;
		for(int i=from; i<to; ++i){
			b[ind] = bytes[i];
			++ind;
		}
		
		return b;
	}
	
	
	
	byte[] replace(byte[] bytes, int len, int index){
		byte[] pom = new byte[len + STRING_TO_REPLACE_LENGTH - STRING_TO_FIND_LENGTH];
		
		System.arraycopy(bytes, 0, pom, 0, index);
		System.arraycopy(STRING_TO_REPLACE, 0, pom, index, STRING_TO_REPLACE_LENGTH);
		System.arraycopy(bytes, index+STRING_TO_FIND_LENGTH, pom, index+STRING_TO_REPLACE_LENGTH, pom.length-STRING_TO_REPLACE_LENGTH-index);
		
		bytes = pom;		
		return bytes;
	}
	
	byte[] replaceAll(byte[] byt, int len){
		
		byte[] b = byt;
		int index = 0;
		int l = len;
		while((index = indexOf(b, l, index)) != -1){
			b = replace(b, l, index);
			l = b.length;
			index+=STRING_TO_REPLACE.length;
		}
			
		return b;
		
	}
	
	private int indexOf(byte[] s, int len, int from){
		for(int i=from; i<len; ++i){
				int j=i; int p=0;
				while(s[j] == STRING_TO_FIND[p]){
					++j;
					++p;
					
					if(p == STRING_TO_FIND_LENGTH) {return i; }
					
					//if(p>STRING_TO_FIND_LENGTH) break;
					if(j>=len) break;
				}
		
		}
		return -1;
	}
	private int findIndexBehindLastFoundedWord(byte[] s, int len){
		int fromIndex = 0;
		int lastIndex = -1;
		
		while((fromIndex = indexOf(s, len, fromIndex)) != -1){
			fromIndex += STRING_TO_FIND_LENGTH;
			lastIndex = fromIndex;
		}
		
	//	System.out.println("Wybrano: " + lastIndex);
		
		return lastIndex;
	}
	private int countNumberOfReplaces(byte[] s, int len){
		int fromIndex = 0;
		int count = 0;
		
		while((fromIndex = indexOf(s, len, fromIndex))!=-1){
			fromIndex += STRING_TO_FIND_LENGTH;
			++count;
		}
		
		return count;
	}
	
	private void changeName(){
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
