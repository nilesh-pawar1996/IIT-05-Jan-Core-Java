package com.sunbeam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyWindow extends JFrame {
	private JButton showDialogButton;

	public MyWindow() {
		setTitle("MyWindow");
		setSize(800, 600);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		
		showDialogButton = new JButton("Show Dialog");
		showDialogButton.setBounds(300, 300, 120, 30);
		this.add(showDialogButton);
		showDialogButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MyDialog dialog = new MyDialog();
				dialog.setModal(true);
				dialog.setVisible(true);
				String essay = dialog.getEssayText();
				JOptionPane.showMessageDialog(MyWindow.this, essay);
			}
		});
	}
}
