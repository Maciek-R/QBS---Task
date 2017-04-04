package pl.java.formatter;
import java.awt.List;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import javax.annotation.Generated;

import static pl.java.formatter.Formatter.*;

public class Manager {

	private File fileRoot;
	private String extension;
	private String stringToFind;
	private String stringToReplace;
	
	private ArrayList<String> openedFiles;
	private ArrayList<String> unOpenedFiles;
	
	public Manager(File root, String extension, String stringToFind, String stringToReplace){
		this.fileRoot = root;
		this.extension = extension;
		this.stringToFind = stringToFind;
		this.stringToReplace = stringToReplace;
		
		openedFiles = new ArrayList<String>();
		unOpenedFiles = new ArrayList<String>();
	}
	
	public void start(){
		unOpenedFiles.clear();
		openedFiles.clear();
		
		if(fileRoot!=null)
			goIntoFolder(fileRoot);
	}
	
	public void goIntoFolder(File file){
		System.out.println("Pliki w " + file.getAbsolutePath());

		
		File[] files = getFiles(file, extension);	
		for(File f:files){
			System.out.println("   " + f.getName());
			Formatter formatter = new Formatter(f.getAbsolutePath(), f.getAbsolutePath()+"1", stringToFind, stringToReplace);
			System.out.println(f.getAbsolutePath());
			int result = formatter.format();
			
			if (result == MESSAGE_OK){
				openedFiles.add(new String(f.getAbsolutePath()));
			}
			else if(result == MESSAGE_PERMISSION_DENIED){
				unOpenedFiles.add(new String(f.getAbsolutePath()));
			}
		}
		File[] directories = getFolders(file);
		
		for(File f:directories){
			goIntoFolder(f);
		}
		
	}
	
	public File[] getFiles(File root, String ending){
		
		File[] files = root.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if(pathname.isFile() && pathname.getName().toLowerCase().endsWith(ending))
					return true;
				return false;
			}
		});
		
		return files;
	}
	public File[] getFolders(File root){
		
		File[] folders = root.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if(pathname.isDirectory())
					return true;
				return false;
			}
		});
		
		return folders;
	}
	
	public boolean isAnyOpenedFile(){
		return !openedFiles.isEmpty();
	}
	public ArrayList<String> getOpenedFiles(){
		return openedFiles;
	}
	
	public boolean isAnyUnOpenedFile(){
		return !unOpenedFiles.isEmpty();
	}
	
	public ArrayList<String> getUnOpenedFiles(){
		return unOpenedFiles;
	}

}
