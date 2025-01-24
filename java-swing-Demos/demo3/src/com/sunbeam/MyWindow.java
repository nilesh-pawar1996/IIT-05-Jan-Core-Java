package com.sunbeam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class MyWindow extends JFrame {
	private JButton testButton;
	private JTable prodTable;
	private JScrollPane prodScrollPane;
	
	public MyWindow() {
		setTitle("MyWindow");
		setSize(800, 600);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
		
		Object[][] data = {
			{1, "Prod 1", 100.0},
			{2, "Prod 2", 200.0},
		};
		Object[] colNames = { "Id", "Name", "Price" };
		prodTable = new JTable(data, colNames);
		prodScrollPane = new JScrollPane(prodTable);
		prodScrollPane.setBounds(10, 10, 360, 250);
		this.add(prodScrollPane);
		
		testButton = new JButton("Test");
		testButton.setBounds(300, 300, 120, 30);
		this.add(testButton);
		testButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TableModel model = prodTable.getModel();
				String value = (String) model.getValueAt(0, 1);
				value = value.toUpperCase();
				model.setValueAt(value, 0, 1);
			}
		});
	}
}
