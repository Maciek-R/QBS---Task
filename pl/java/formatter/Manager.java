package pl.java.formatter;
import java.io.File;import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;

import pl.java.swing.SearchDialog;

import static pl.java.formatter.Formatter.*;

public class Manager {

	private File fileRoot;
	private String extension;
	private String stringToFind;
	private String stringToReplace;
	
	private ArrayList<String> openedFiles;
	private ArrayList<Integer> numberOfReplacesInFile;
	private ArrayList<String> unOpenedFiles;
	
	private SearchDialog searchDialog;
	private int numberOfCheckedFiles;
	private int numOfAllFiles;
	
	public Manager(File root, String extension, String stringToFind, String stringToReplace, SearchDialog searchDialog){
		this.fileRoot = root;
		this.extension = extension;
		this.stringToFind = stringToFind;
		this.stringToReplace = stringToReplace;
		
		openedFiles = new ArrayList<String>();
		numberOfReplacesInFile = new ArrayList<Integer>();
		unOpenedFiles = new ArrayList<String>();
		
		this.searchDialog = searchDialog;
		numberOfCheckedFiles = 0;
		numOfAllFiles = getNumberOfFiles();
		System.out.println("NUM " + numOfAllFiles);
	}
	
	public void start(){
		unOpenedFiles.clear();
		numberOfReplacesInFile.clear();
		openedFiles.clear();
		
		if(fileRoot!=null)
			goIntoFolder(fileRoot);
	}
	
	public void goIntoFolder(File file){
		System.out.println("Pliki w " + file.getAbsolutePath());

		
		File[] files = getFiles(file, extension);	
		for(File f:files){
			System.out.println("   " + f.getName());
			
			File tmp = null;
			try {
				tmp = File.createTempFile("tmp", null, f.getParentFile());
				tmp.deleteOnExit();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Formatter formatter = new Formatter(f.getAbsolutePath(), tmp.getAbsolutePath(), stringToFind, stringToReplace);
			System.out.println(f.getAbsolutePath());
			int result = formatter.format();
			
			if (result == MESSAGE_OK){
				openedFiles.add(new String(f.getAbsolutePath()));
				numberOfReplacesInFile.add(new Integer(formatter.getNumberOfReplaces()));
				
				searchDialog.setNewOpenedFile(f.getAbsolutePath(), formatter.getNumberOfReplaces());
			}
			else if(result == MESSAGE_PERMISSION_DENIED){
				unOpenedFiles.add(new String(f.getAbsolutePath()));
			}
			
			++numberOfCheckedFiles;
			searchDialog.setPercentProgressBar((int)((float)numberOfCheckedFiles/numOfAllFiles * 100));
			
		}
		File[] directories = getFolders(file);
		
		for(File f:directories){
			goIntoFolder(f);
		}
		
	}
	public int getNumberOfFiles(){
		return goForNumberOfFiles(fileRoot);
	}
	
	public int goForNumberOfFiles(File file){
		int numOfFiles = getFiles(file, extension).length;
		int numOfSubFiles = 0;
		File[] directories = getFolders(file);
		for(File f:directories){
			numOfSubFiles += goForNumberOfFiles(f);
		}
		
		return numOfFiles + numOfSubFiles;
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
	
	public int getNumberOfReplacesInFile(int nr){
		return numberOfReplacesInFile.get(nr);
	}
	
}
