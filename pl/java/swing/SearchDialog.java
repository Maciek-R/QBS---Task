package pl.java.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import pl.java.formatter.Manager;

public class SearchDialog extends JDialog{

	Manager manager;
	
	
	JProgressBar progressBar;
	JTextArea textArea;
	JScrollPane scrollPane;
	JButton buttonOk;
	ProgressBarWorker progres;
	
	public SearchDialog(File root, String extension, byte[] stringToFind, byte[] stringToReplace){
		setTitle("Postêp");
    	setLocationRelativeTo(this);
    	setMinimumSize(new Dimension(550, 200));
    	//setPreferredSize(new Dimension(550, 200));
    	getContentPane().setBackground(Color.decode("#e9ebee"));
    	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	setModal(true);
    	
    	initComponents();
    	initLayout();
    	initActions();
    	
    	
		manager = new Manager(root, extension, stringToFind, stringToReplace, this);
		//manager.start();
	//	setModal(true);
		progres = new ProgressBarWorker();
		progres.execute();
		setVisible(true);
		
	}
	private void initComponents(){
		progressBar = new JProgressBar(0, 100);
		progressBar.setStringPainted(true);
    	textArea = new JTextArea(15, 50);
		textArea.setEditable(false);
		textArea.setBackground(Color.decode("#e9ebee"));
		scrollPane = new JScrollPane(textArea);
		buttonOk = new JButton("OK");
		buttonOk.setBackground(Color.decode("#4267b2"));
		buttonOk.setForeground(Color.WHITE);
		buttonOk.setEnabled(false);
		
	}
	private void initLayout(){
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;
		
		gc.gridx=0;
		gc.gridy=0;
		add(progressBar, gc);
		
		gc.weighty = 10;
		gc.gridx=0;
		gc.gridy=1;
		add(scrollPane, gc);
		
		gc.weighty=1;
		gc.gridx = 0;
		gc.gridy = 2;
		add(buttonOk, gc);
		
		pack();
	}
	public void start(){
		int numOfFiles = manager.getNumberOfFiles();
		manager.start();
		
		System.out.println("Zakonczono");
		JOptionPane.showMessageDialog(this, "Zakoñczono\nLiczba przeszukanych plików: "+manager.getOpenedFiles().size());
		
		if(manager.isAnyUnOpenedFile()){
			StringBuilder sB = new StringBuilder();
			
			for(String s:manager.getUnOpenedFiles()){
				sB.append(s);
				sB.append('\n');
			}
			
			JOptionPane.showMessageDialog(this, "Nie uda³o siê przeszukac nastepuj¹cych plikow z powodu braku uprawnieñ("+manager.getUnOpenedFiles().size() +")\n" + sB.toString());
		}
		
		
		if(!manager.isAnyOpenedFile())
			textArea.setText("Nie znaleziono ¿adnego pliku o podanym rozszerzeniu");
		else{
			textArea.append("Liczba przeszukanych plików: " + manager.getOpenedFiles().size());
		}
	}
	public void setPercentProgressBar(int percent){
		progressBar.setValue(percent);
		progressBar.setString(Integer.toString(percent) + "%");
	}
	public void setNewOpenedFile(String fileName, int numberOfReplaces){
		
			textArea.append(fileName + " " + "Liczba zmian: " + numberOfReplaces + "\n");
		
	}
	public void initActions(){
		buttonOk.addActionListener((ActionEvent event)->{
			dispose();
		});
	}
	
	class ProgressBarWorker extends SwingWorker<Void, Void>{

		@Override
		protected Void doInBackground() throws Exception {
			start();
			System.out.println("start");
			
			return null;
		}

		@Override
		protected void done() {
			progressBar.setValue(100);
			System.out.println("done");
			progressBar.setString("100%");
			buttonOk.setEnabled(true);
			super.done();
		}
		
		
	}
}

