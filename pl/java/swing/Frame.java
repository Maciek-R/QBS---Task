package pl.java.swing;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import pl.java.formatter.Manager;

public class Frame extends JFrame{

	private JButton pathButton;
	private TextField extensionField;
	private TextField byte1Field;
	private TextField byte2Field;
	
	private JLabel pathLabel;
	private JLabel extensionLabel;
	private JLabel byte1Label;
	private JLabel byte2Label;
    
    JButton acceptButton;
    File root;
	
	public Frame(){
		setTitle("QBS App");
	    setSize(700, 250);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
	    initComponents();
	    initLayout();  
	}
	
	private void initLayout(){
		    setLayout(new FlowLayout());   
		 
		    JPanel labelListPane = new JPanel();
		    labelListPane.setLayout(new GridLayout(4, 2, 5, 20)); 
		    
		    labelListPane.add(pathLabel);
		    labelListPane.add(pathButton);
		    
		    labelListPane.add(extensionLabel);
		    labelListPane.add(extensionField);
		    
		    labelListPane.add(byte1Label);
		    labelListPane.add(byte1Field);
		    
		    labelListPane.add(byte2Label);
		    labelListPane.add(byte2Field);
		    
		    JPanel valuesPane = new JPanel();
		    valuesPane.add(acceptButton);
		    
		    add(labelListPane);
		    add(valuesPane);
		    
	//	    pack();
	}
	
	private void initComponents(){
		pathLabel = new JLabel("Sciezka do katalogu: ");
	    extensionLabel = new JLabel("Rozszerzenie: ");
	    byte1Label = new JLabel("Ciag bajtow 1: ");
	    byte2Label = new JLabel("Ciag bajtow 2: ");
	    
	    pathButton = new JButton("Podaj katalog");
	    extensionField = new TextField(25);
	    byte1Field = new TextField(25);
	    byte2Field = new TextField(25);
	    
	    acceptButton = new JButton("Rozpocznij");
	    
	    pathButton.addActionListener((ActionEvent event) -> {
	    	JFileChooser fileChooser = new JFileChooser();
	    	fileChooser.setCurrentDirectory(new File("."));
	    	fileChooser.setDialogTitle("Wybierz Katalog");
	    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	fileChooser.setAcceptAllFileFilterUsed(false);
	    	
	    	if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
	    		
	    		this.pathButton.setText(fileChooser.getSelectedFile().getAbsolutePath());
	    		root = fileChooser.getSelectedFile();
	    		
	    	}
	    });
	    
	    acceptButton.addActionListener((ActionEvent event) -> {
	    	
	    	int result = JOptionPane.showConfirmDialog(this, "Uwaga. Wykonanie tego polecenia moze byc niebezpieczne dla tego komputera. Czy jestes pewien, ¿e chcesz wykonac te czynnosc?");
	    
	    	if(result == JOptionPane.YES_OPTION){
	    		System.out.println("Rozpoczeto");
	    		if(extensionField.getText().equals("")){
	    			JOptionPane.showMessageDialog(this, "Nie podano rozszerzenia!");
	    			return;
	    		}
	    		if(root == null){
	    			JOptionPane.showMessageDialog(this, "Nie wybrano katalogu!");
	    			return;
	    		}
	    		if(byte1Field.getText().equals("")){
	    			JOptionPane.showMessageDialog(this, "Nie podano ciagu bajtow 1");
	    			return;
	    		}
	    		
	    		System.out.println(root.getAbsolutePath());
	    		
	    		Manager manager = new Manager(root, extensionField.getText(), byte1Field.getText(), byte2Field.getText());
	    		manager.start();
	    		
	    		if(manager.isAnyUnOpenedFile()){
	    			StringBuilder sB = new StringBuilder();
	    			
	    			for(String s:manager.getUnOpenedFiles()){
	    				sB.append(s);
	    				sB.append('\n');
	    			}
	    			
	    			JOptionPane.showMessageDialog(this, "Nie udalo sie przeszukac nastepujacych plikow z powodu braku uprawnien("+manager.getUnOpenedFiles().size() +")\n" + sB.toString());
	    			
	    		}
	    		
	    		
	    		StringBuilder sB = new StringBuilder();
	    		if(manager.isAnyOpenedFile()){
	    			sB.append("Liczba przeszukanych plikow: " + manager.getOpenedFiles().size() + "\n");
	    			for(String s:manager.getOpenedFiles()){
	    				sB.append(s);
	    				sB.append('\n');
	    			}
	    		}
	    		JTextArea textArea = new JTextArea(10, 40);
	    		textArea.setEditable(false);
	    		textArea.setText(sB.toString());
	    		
	    		JScrollPane scrollPane = new JScrollPane(textArea);
	    		JOptionPane.showMessageDialog(this, "Zakonczono");
	    		JOptionPane.showMessageDialog(this, scrollPane);
	    	}
	    });
	}
}
