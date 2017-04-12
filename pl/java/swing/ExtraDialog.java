package pl.java.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ExtraDialog extends JDialog{
	
	private Frame frame;
	private int nr;
	
	private JLabel label;
	private JTextField textField;
	private JLabel labelHex;
	private JTextField textHexField;
	
	private JLabel hexLabel;
	private JTextField hexTextField;
	private JButton buttonAddByte;
	private JButton buttonAddHex;
	private JButton buttonOk;
	private JButton buttonErase;
	private JButton buttonClear;
	
	private ArrayList<Byte> bytes = new ArrayList<>();
	
	public ExtraDialog(Frame frame, int nr){
		this.frame = frame;
		this.nr = nr;
		
		setTitle("Zaawansowane");
    	setLocationRelativeTo(this);
    	setMinimumSize(new Dimension(480, 200));
    	setPreferredSize(new Dimension(480, 200));
    	getContentPane().setBackground(Color.decode("#e9ebee"));
    	setModal(true);
    	
    	
    	
    	initComponents();
    	initLayout();
    	initActions();
    	
    	setVisible(true);
    	
	}
	
	private void initLayout(){
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(5, 5, 5, 5);
		setLayout(new GridBagLayout());
		gc.anchor = GridBagConstraints.LINE_START;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridx = 0;
		gc.gridy = 0;
    	add(label, gc);
    	
    	gc.gridx=0;
    	gc.gridy=1;
    	add(labelHex, gc);
    	
    	gc.gridx = 0;
    	gc.gridy = 2;
    	add(hexLabel, gc);
    	
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	gc.weightx = 25;
    	gc.gridx = 1;
    	gc.gridy = 0;
    	//gc.gridwidth = 2;
    	add(textField, gc);
    	
    	gc.gridx=1;
    	gc.gridy=1;
    	add(textHexField, gc);
    	
    //	gc.fill = GridBagConstraints.HORIZONTAL;
    	
    	
    	
    	
    	
    	
    	//gc.gridwidth = 1;
    	
    	
    	//gc.gridwidth = 2;
    	gc.gridx = 1;
    	gc.gridy = 2;
    	add(hexTextField, gc);
    	
    	gc.gridx = 2;
    	gc.gridy = 0;
    	gc.gridwidth=2;
    	gc.weightx = 1;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(buttonAddByte, gc);
    	
    	gc.gridx = 2;
    	gc.gridy = 1;
    //	gc.weightx = 5;
    	add(buttonAddHex, gc);
    	
    	gc.gridwidth=1;
    	gc.gridx = 2;
    	gc.gridy = 2;
    	add(buttonErase, gc);
    	
    	gc.gridx=3;
    	gc.gridy=2;
    	add(buttonClear, gc);
    	
    	gc.gridx = 0;
    	gc.gridy = 3;
    	gc.gridwidth = 4;
    	gc.fill = GridBagConstraints.HORIZONTAL;
    	add(buttonOk, gc);
		
	//	pack();
	}
	private void initComponents(){
		label = new JLabel("Bajt(0-255): ");
    	textField = new JTextField(10);
    	labelHex = new JLabel("Hex(00-FF)");
    	textHexField = new JTextField(10);
    	hexLabel = new JLabel("Wynik(Hex): ");
    	hexTextField = new JTextField(10);
    	hexTextField.setEditable(false);
    	buttonAddByte = new JButton("Dodaj");
    	buttonAddByte.setBackground(Color.decode("#4267b2"));
    	buttonAddByte.setForeground(Color.WHITE);
    	buttonAddHex = new JButton("Dodaj");
    	buttonAddHex.setBackground(Color.decode("#4267b2"));
    	buttonAddHex.setForeground(Color.WHITE);
    	buttonOk = new JButton("OK");
    	buttonOk.setBackground(Color.decode("#4267b2"));
    	buttonOk.setForeground(Color.WHITE);
    	buttonErase = new JButton("Usun");
    	buttonErase.setBackground(Color.decode("#4267b2"));
    	buttonErase.setForeground(Color.WHITE);
    	buttonClear = new JButton("Wyczysc");
    	buttonClear.setBackground(Color.decode("#4267b2"));
    	buttonClear.setForeground(Color.WHITE);
    	
	}
	private void initActions(){
		
		buttonAddByte.addActionListener((ActionEvent ev) -> {
    		
    		try{
    			int num = Integer.parseInt(textField.getText());
    			if(num<0 || num >=256){
    			//	System.out.println("zly format");
    				//JOptionPane.showMessageDialog(this, "Wartoœæ powinna byæ liczb¹ z przedzia³u 0-255", "Niepoprawny format", JOptionPane.ERROR_MESSAGE);
    				//JOptionPane.showMe
    				
    				throw new NumberFormatException();
    				//return;
    			}
    			byte[] bb = new byte[]{(byte) num};
    			
    			//Integer.toHexString(num);
    			if(num < 16)
    				hexTextField.setText(hexTextField.getText()+"0");
    			hexTextField.setText(hexTextField.getText()+Integer.toHexString(num));
    			bytes.add(new Byte((byte)num));
    			
    			//frame.byte1FieldHex.setText(new String(bb));
    			
    		}
    		catch(NumberFormatException e){
    			JOptionPane.showMessageDialog(this, "Wartoœæ powinna byæ liczb¹ z przedzia³u 0-255", "Niepoprawny format", JOptionPane.ERROR_MESSAGE);
    			System.out.println("zly format");
    		}
    	});
		
		buttonAddHex.addActionListener((ActionEvent ev) -> {
    		
    		try{
    			//int num = Integer.parseInt(textHexField.getText());
    			if(textHexField.getText().length()!=4)
    				throw new NumberFormatException();
    			int num = Integer.decode(textHexField.getText());
    			if(num<0 || num >=256){
    			//	System.out.println("zly format");
    				throw new NumberFormatException();
    				//JOptionPane.showMessageDialog(this, "Wartoœæ powinna byæ w formacie 0x00 - 0xFF", "Niepoprawny format", JOptionPane.ERROR_MESSAGE);
    				//JOptionPane.showMe
    			//	return;
    			}
    			byte[] bb = new byte[]{(byte) num};
    			
    			//Integer.toHexString(num);
    		//	int x = Integer.decode("0xFF");
    			if(num < 16)
    				hexTextField.setText(hexTextField.getText()+"0");
    			hexTextField.setText(hexTextField.getText()+Integer.toHexString(num));
    			bytes.add(new Byte((byte)num));
    			
    			//frame.byte1FieldHex.setText(new String(bb));
    			
    		}
    		catch(NumberFormatException e){
    			JOptionPane.showMessageDialog(this, "Wartoœæ powinna byæ w formacie 0x00 - 0xFF", "Niepoprawny format", JOptionPane.ERROR_MESSAGE);
    			System.out.println("zly format");
    		}
    	});
		
		
		
		buttonOk.addActionListener((ActionEvent ev) -> {
    		
			if(nr == 1){
				frame.byte1FieldHex.setText(hexTextField.getText());
				frame.bytes1 = bytes;
			}
			else if(nr == 2){
				frame.byte2FieldHex.setText(hexTextField.getText());
				frame.bytes2 = bytes;
			}
    		dispose();
    	});
		buttonClear.addActionListener((ActionEvent ev) -> {
    		
			hexTextField.setText("");
			bytes.clear();
    	});
		buttonErase.addActionListener((ActionEvent ev) -> {
    		
			if(hexTextField.getText().length() > 0)
				hexTextField.setText(hexTextField.getText().substring(0, hexTextField.getText().length()-2));
			if(bytes.size() > 0)
				bytes.remove(bytes.size()-1);
    	});
	}
}
