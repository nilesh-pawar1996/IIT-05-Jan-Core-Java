package com.sunbeam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyDialog extends JDialog {
	private JTextArea essayTextArea;
	private JScrollPane essayScrollPane;
	private JButton closeButton;
	private String essayText;
	
	public MyDialog() {
		setTitle("MyDialog");
		setSize(400, 400);
		setLayout(null);
		
		essayTextArea = new JTextArea(5, 100);
		
		JScrollPane essayScrollPane = new JScrollPane(essayTextArea);
		essayScrollPane.setBounds(10, 10, 360, 300);
		this.add(essayScrollPane);
		
		closeButton = new JButton("Close");
		closeButton.setBounds(160, 320, 80, 30);
		this.add(closeButton);
		closeButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				essayText = essayTextArea.getText();
				MyDialog.this.dispose();
			}
		});
	}

	public String getEssayText() {
		return essayText;
	}
}
