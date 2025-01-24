package com.sunbeam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MyWindow extends JFrame {
	private JButton submitButton;
	private JLabel nameLabel;
	private JLabel genderLabel;
	private JLabel cityLabel;
	private JTextField nameTextField;
	private JRadioButton maleRadioButton;
	private JRadioButton femaleRadioButton;
	private ButtonGroup genderGroup;
	private JComboBox cityComboBox;

	public MyWindow() {
		// set window properties
		setTitle("My Window");
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // destroy window when [X] is clicked.
		//setDefaultCloseOperation(EXIT_ON_CLOSE); // exit application when [X] is clicked.
		setLayout(null);
		
		// add controls on window
		nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(50, 50, 80, 30);
		this.add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(150, 50, 200, 30);
		this.add(nameTextField);
		//String name = nameTextField.getText();
		
		genderLabel = new JLabel("Gender: ");
		genderLabel.setBounds(50, 100, 80, 30);
		this.add(genderLabel);
		
		maleRadioButton = new JRadioButton("Male");
		maleRadioButton.setBounds(150, 100, 90, 30);
		maleRadioButton.setSelected(true);
		this.add(maleRadioButton);
		
		femaleRadioButton = new JRadioButton("Female");
		femaleRadioButton.setBounds(250, 100, 90, 30);
		this.add(femaleRadioButton);
		
		genderGroup = new ButtonGroup();
		genderGroup.add(maleRadioButton);
		genderGroup.add(femaleRadioButton);
		//boolean isMale = maleRadioButton.isSelected();
		//boolean isFemale = femaleRadioButton.isSelected();
		
		cityLabel = new JLabel("City: ");
		cityLabel.setBounds(50, 150, 80, 30);
		this.add(cityLabel);
		
		String[] cities = { "Mumbai", "Pune", "Nagpur", "Karad", "Nagar" };
		cityComboBox = new JComboBox<>(cities);
		cityComboBox.setBounds(150, 150, 120, 30);
		this.add(cityComboBox);
		//int selectedIndex = cityComboBox.getSelectedIndex();
		//String selectedItem = (String) cityComboBox.getSelectedItem();
		
		submitButton = new JButton("Submit");
		submitButton.setBounds(100, 250, 100, 30);
		this.add(submitButton);
		submitButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// event handling logic
				String message = "Name: " + nameTextField.getText() +
								"\nGender: " + (maleRadioButton.isSelected()? "Male" : "Female") +
								"\nCity: " + cityComboBox.getSelectedItem();
				//System.out.println(message);
				JOptionPane.showMessageDialog(MyWindow.this, message);
			}
		});
		
		/*
		// event handling - step 1 - implement listener interface in some class
		//		and add event handling logic there
		class MyListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// event handling logic
			}
		}
		// event handling - step 2 - attach listener class object to that control
		submitButton.addActionListener(new MyListener());
		*/
	}
}
