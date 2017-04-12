package pl.java.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Frame extends JFrame{

	private JButton pathButton;
	private JLabel chosedCurrentPath;
	private TextField extensionField;
	private TextField byte1FieldText;
	private TextField byte2FieldText;
	TextField byte1FieldHex;
	TextField byte2FieldHex;
	
	private JLabel pathLabel;
	private JLabel chosedPathLabel;
	private JLabel extensionLabel;
	private JLabel byte1Label;
	private JLabel text1Label;
	private JLabel hex1Label;
	
	private JLabel byte2Label;
	private JLabel text2Label;
	private JLabel hex2Label;
	
	private JCheckBox checkBoxText1;
	private JCheckBox checkBoxHex1;
	private JCheckBox checkBoxText2;
	private JCheckBox checkBoxHex2;
	
	ArrayList<Byte> bytes1;
	ArrayList<Byte> bytes2;
    
	private ExtraDialog dialog;
	
    JButton acceptButton;
    JButton specialButton1;
    JButton specialButton2;
    File root;
    
	
	public Frame(){
		setTitle("QBS App");
		setMinimumSize(new Dimension(550, 500));
	    setSize(550, 500);
	    
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	  //  setPreferredSize(new Dimension(500, 500));
	    getContentPane().setBackground(Color.decode("#e9ebee"));
	  //  getContentPane().setBackground(new Color(235, 237, 240));
	    
	    initComponents();
	    initLayout();  
	    initActions();
	}
	
	private void initLayout(){
		 
		setLayout(new GridBagLayout());
		
		    JPanel labelListPane = new JPanel();
		    
		  //  labelListPane.setLayout(new GridLayout(5, 1, 0, 32)); 
		    GridBagConstraints gc = new GridBagConstraints();
		    gc.insets = new Insets(5, 5, 5, 5);
		    labelListPane.setLayout(new GridBagLayout());
		    labelListPane.setBackground(Color.GRAY);
		    
		    
		    gc.weightx = 1;
		    gc.weighty = 1;
		    gc.gridx=0;
		    gc.gridy=0;
		    
		    gc.anchor = GridBagConstraints.LINE_START;
		    add(pathLabel, gc);
		    
		    gc.gridx = 0;
		    gc.gridy = 1;
		    
		    add(chosedPathLabel, gc);
		    
		    gc.gridx = 0;
		    gc.gridy = 2;
		    add(extensionLabel, gc);

		    gc.gridx = 0;
		    gc.gridy = 3;
		    
		    add(byte1Label, gc);
		    
		    gc.gridy = 4;
		    add(text1Label, gc);
		    
		    gc.gridy = 5;
		    add(hex1Label, gc);

		    gc.gridx = 0;
		    gc.gridy = 6;
		    
		    add(byte2Label, gc);
		    
		    gc.gridy = 7;
		    add(text2Label, gc);
		    
		    gc.gridy = 8;
		    add(hex2Label, gc);
		    
		    gc.anchor = GridBagConstraints.LINE_END;
		    gc.gridy=4;
		    add(checkBoxText1, gc);
		    gc.gridy=5;
		    add(checkBoxHex1, gc);
		    gc.gridy=7;
		    add(checkBoxText2, gc);
		    gc.gridy=8;
		    add(checkBoxHex2, gc);
		    gc.anchor = GridBagConstraints.LINE_START;
		    
		    
		    JPanel fieldListPane = new JPanel();
		    
		  // fieldListPane.setLayout(new GridLayout(5, 1, 5, 20));
		   // fieldListPane.setLayout(new BoxLayout(fieldListPane, BoxLayout.PAGE_AXIS));
		    fieldListPane.setLayout(new GridBagLayout());
		    fieldListPane.setBackground(Color.GRAY);
		    
		    gc.weightx = 25;
		    gc.weighty = 5;
		    gc.gridx = 1;
		    gc.gridy = 0;
		    
		   // gc.anchor = GridBagConstraints.LINE_START;
		    gc.fill=GridBagConstraints.HORIZONTAL;
		    add(pathButton, gc);
		    gc.gridy = 1;
		    add(chosedCurrentPath, gc);
		    gc.gridy = 2;
		    add(extensionField, gc);
		    gc.gridy = 4;
		    add(byte1FieldText, gc);
		    gc.gridy = 5;
		    add(byte1FieldHex, gc);
		    gc.gridy = 7;
		    add(byte2FieldText, gc);
		    gc.gridy = 8;
		    add(byte2FieldHex, gc);
		    
		    gc.weightx = 1;
		    gc.gridx = 2;
		    gc.gridy = 5;
		    gc.fill = GridBagConstraints.HORIZONTAL;
		    add(specialButton1, gc);
		  //  gc.weightx = 30;
		    gc.gridx = 2;
		    gc.gridy = 8;
		    add(specialButton2, gc);
		    
		    gc.anchor = GridBagConstraints.FIRST_LINE_START;
		    gc.fill = GridBagConstraints.BOTH;
		    gc.weightx = 1;
		    gc.gridx = 0;
		    gc.gridy = 9;
		    gc.gridwidth = 3;
		    add(acceptButton, gc);
		    
		    
	}
	
	private void initComponents(){
		pathLabel = new JLabel("Wybranie katalogu: ");
		chosedPathLabel = new JLabel("Œciezka: ");
	    extensionLabel = new JLabel("Rozszerzenie: ");
	    byte1Label = new JLabel("Ciag bajtow 1: ");
	    byte2Label = new JLabel("Ciag bajtow 2: ");
	    text1Label = new JLabel("Tekstowo: ");
	    hex1Label = new JLabel("Hexadecymalnie: ");
	    text2Label = new JLabel("Tekstowo: ");
	    hex2Label = new JLabel("Hexadecymalnie: ");
	    
	    pathButton = new JButton("Podaj katalog");
	    pathButton.setBackground(Color.decode("#4267b2"));
	    pathButton.setForeground(Color.WHITE);
	    pathButton.setFocusPainted(false);
	    
	    chosedCurrentPath = new JLabel("");
	 //   chosedCurrentPath.setPreferredSize(new Dimension(225, 50));
	  //  chosedCurrentPath.setMaximumSize(new Dimension(500, 10));
	    
	    extensionField = new TextField(30);
	    byte1FieldText = new TextField(30);
	    byte1FieldHex = new TextField(30);
	    byte1FieldHex.setEditable(false);
	    byte1FieldHex.setFocusable(false);
	    byte1FieldHex.setCursor(Cursor.getDefaultCursor());
	    
	    byte2FieldText = new TextField(30);
	    byte2FieldHex = new TextField(30);
	    byte2FieldHex.setEditable(false);
	    byte2FieldHex.setFocusable(false);
	    byte2FieldHex.setCursor(Cursor.getDefaultCursor());
	    
	    acceptButton = new JButton("Rozpocznij");
	    acceptButton.setBackground(Color.decode("#4267b2"));
	    acceptButton.setForeground(Color.WHITE);
	    specialButton1 = new JButton("Zaawansowane");
	    specialButton1.setEnabled(false);
	    specialButton1.setBackground(Color.decode("#4267b2"));
	    specialButton1.setForeground(Color.WHITE);
	    
	    specialButton2 = new JButton("Zaawansowane");
	    specialButton2.setEnabled(false);
	    specialButton2.setBackground(Color.decode("#4267b2"));
	    specialButton2.setForeground(Color.WHITE);
	    
	    
	    checkBoxText1 = new JCheckBox();
	    checkBoxText1.setSelected(true);
	    checkBoxText1.setBackground(Color.decode("#e9ebee"));
	    
	    checkBoxHex1 = new JCheckBox();
	    checkBoxHex1.setBackground(Color.decode("#e9ebee"));
	    
	    checkBoxText2 = new JCheckBox();
	    checkBoxText2.setSelected(true);
	    checkBoxText2.setBackground(Color.decode("#e9ebee"));
	    
	    checkBoxHex2 = new JCheckBox();
	    checkBoxHex2.setBackground(Color.decode("#e9ebee"));
	    
	}
	private void initActions(){
		pathButton.addActionListener((ActionEvent event) -> {
	    	JFileChooser fileChooser = new JFileChooser();
	    	fileChooser.setCurrentDirectory(new File("."));
	    	fileChooser.setDialogTitle("Wybierz Katalog");
	    	fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    	fileChooser.setAcceptAllFileFilterUsed(false);
	    	
	    	int result = fileChooser.showOpenDialog(this);
	    	
	    	if(result == JFileChooser.APPROVE_OPTION){
	    		
	    		//this.pathButton.setText(fileChooser.getSelectedFile().getAbsolutePath());
	    		
	    		File file = fileChooser.getSelectedFile();
	    		
	    		if(file.exists() && file.isDirectory()){
	    			this.chosedCurrentPath.setText(file.getAbsolutePath());
	    			root = fileChooser.getSelectedFile();
	    		}
	    		else{
	    			JOptionPane.showMessageDialog(this, "Taki katalog nie istnieje");
	    		}
	    	}
	    	
			
	    });
	    
	    acceptButton.addActionListener((ActionEvent event) -> {
	    	
	    	int result = JOptionPane.showConfirmDialog(this, "Uwaga. Wykonanie tego polecenia mo¿e byc niebezpieczne dla tego komputera. Skutki mog¹ byæ nieodwracalne. Czy jesteœ pewien, ¿e chcesz wykonaæ tê czynnoœæ?", "Uwaga", JOptionPane.WARNING_MESSAGE);
	    
	  
	    	
	    	if(result == JOptionPane.YES_OPTION){
	    	//	System.out.println("Rozpoczeto");
	    		if(extensionField.getText().equals("")){
	    			JOptionPane.showMessageDialog(this, "Nie podano rozszerzenia!\nPrzyk³adowo: txt, bmp.");
	    			return;
	    		}
	    		if(root == null){
	    			JOptionPane.showMessageDialog(this, "Nie wybrano katalogu!");
	    			return;
	    		}
	    		if(checkBoxText1.isSelected() && byte1FieldText.getText().equals("")){
	    			JOptionPane.showMessageDialog(this, "Nie podano tekstowego ci¹gu bajtów 1");
	    			return;
	    		}
	    		if(checkBoxHex1.isSelected() && byte1FieldHex.getText().equals("")){
	    			JOptionPane.showMessageDialog(this, "Nie podano hexadecymalnego ci¹gu bajtów 1");
	    			return;
	    		}
	    		
	    		
	    		//System.out.println(root.getAbsolutePath());
	    		
	    		byte[] sequence1;
	    		byte[] sequence2;
	    		
	    		if(checkBoxText1.isSelected())
	    			sequence1 = byte1FieldText.getText().getBytes();
	    		else
	    			sequence1 = getStringFromBytesArray(1);			//trzeba zamienic bytes1 na Stringa
	    		
	    		if(checkBoxText2.isSelected())
	    			sequence2 = byte2FieldText.getText().getBytes();
	    		else
	    			sequence2 = getStringFromBytesArray(2);	
	    		
	    		SearchDialog searchDialog = new SearchDialog(root, extensionField.getText(), sequence1, sequence2);
	    		  		
	    	}
	    });
	    
	    specialButton1.addActionListener((ActionEvent event) ->{
	    	
	    	
	    	
	    	dialog = new ExtraDialog(this, 1);
	    	//JPanel panel = new JPanel();
	    	//JProgressBar progressBar = new JProgressBar();
	    	//panel.add(progressBar);
	    	
	    //	searchDialog.start();
	    	//JOptionPane.showMessageDialog(this, panel);
	    	
	    	
	    });
	    specialButton2.addActionListener((ActionEvent event) ->{
	    	
	    	
	    	
	    	dialog = new ExtraDialog(this, 2);
	    	
	    	
	    	
	    });
	    checkBoxText1.addActionListener((ActionEvent event)->{
	    	if(checkBoxText1.isSelected()){
	    		checkBoxHex1.setSelected(false);
	    		specialButton1.setEnabled(false);
	    	}
	    	else{
	    		checkBoxHex1.setSelected(true);
	    		specialButton1.setEnabled(true);
	    	}
	    });
	    checkBoxHex1.addActionListener((ActionEvent event)->{
	    	if(checkBoxHex1.isSelected()){
	    		checkBoxText1.setSelected(false);
	    		specialButton1.setEnabled(true);
	    	}
	    	else{
	    		checkBoxText1.setSelected(true);
	    		specialButton1.setEnabled(false);
	    	}
	    });
	    checkBoxText2.addActionListener((ActionEvent event)->{
	    	if(checkBoxText2.isSelected()){
	    		checkBoxHex2.setSelected(false);
	    		specialButton2.setEnabled(false);
	    	}
	    	else{
	    		checkBoxHex2.setSelected(true);
	    		specialButton2.setEnabled(true);
	    	}
	    });
	    checkBoxHex2.addActionListener((ActionEvent event)->{
	    	if(checkBoxHex2.isSelected()){
	    		checkBoxText2.setSelected(false);
	    		specialButton2.setEnabled(true);
	    	}
	    	else{
	    		checkBoxText2.setSelected(true);
	    		specialButton2.setEnabled(false);
	    	}
	    });
	   
	}
	
	private byte[] getStringFromBytesArray(int nr){
		byte[] bs = null;
		ArrayList<Byte> bytes = null;
		if(nr==1){
			if(bytes1 == null) return new byte[]{};
			bs = new byte[bytes1.size()];
			bytes = bytes1;
		}
		else if(nr == 2){
			if(bytes2 == null) return new byte[]{};
			bs = new byte[bytes2.size()];
			bytes = bytes2;
		}
		
    	int j=0;
    	for(Byte b:bytes){
    		bs[j] = bytes.get(j);
    		++j;
    	}
    	for(byte b:bs){
    		System.out.println(b);
    	}
    	return bs;
    	//return new String(bs);
	}
}
