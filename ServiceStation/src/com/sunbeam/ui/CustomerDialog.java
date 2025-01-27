package sunbeam.servicestation.ui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import sunbeam.servicestation.Customer;

//VS4E -- DO NOT REMOVE THIS LINE!
public class CustomerDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel1;
	private JLabel jLabel0;
	private JLabel jLabel2;
	private JTextField txtCustomerName;
	private JTextField txtAddress;
	private JTextField txtMobile;
	private JButton btnSubmit;

	public CustomerDialog(Window parent) {
		super(parent);
		this.setTitle("New Customer");
		initComponents();
	}

	private void initComponents() {
		setForeground(Color.black);
		setBackground(Color.white);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setLayout(null);
		add(getJLabel1());
		add(getJLabel0());
		add(getJLabel2());
		add(getTxtCustomerName());
		add(getTxtAddress());
		add(getTxtMobile());
		add(getBtnSubmit());
		setSize(301, 200);
	}

	private JButton getBtnSubmit() {
		if (btnSubmit == null) {
			btnSubmit = new JButton();
			btnSubmit.setText("Submit");
			btnSubmit.setBounds(103, 123, 81, 26);
			btnSubmit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					btnSubmitActionActionPerformed(event);
				}
			});
		}
		return btnSubmit;
	}

	private JTextField getTxtMobile() {
		if (txtMobile == null) {
			txtMobile = new JTextField();
			txtMobile.setBounds(127, 88, 150, 20);
		}
		return txtMobile;
	}

	private JTextField getTxtAddress() {
		if (txtAddress == null) {
			txtAddress = new JTextField();
			txtAddress.setBounds(125, 54, 152, 20);
		}
		return txtAddress;
	}

	private JTextField getTxtCustomerName() {
		if (txtCustomerName == null) {
			txtCustomerName = new JTextField();
			txtCustomerName.setBounds(125, 18, 155, 20);
		}
		return txtCustomerName;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Mobile : ");
			jLabel2.setBounds(16, 87, 70, 16);
		}
		return jLabel2;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Customer Name : ");
			jLabel0.setBounds(17, 18, 107, 16);
		}
		return jLabel0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Address : ");
			jLabel1.setBounds(16, 49, 113, 16);
		}
		return jLabel1;
	}

	private Customer customer = null;
	
	public Customer getCustomer() {
		return customer;
	}

	private void btnSubmitActionActionPerformed(ActionEvent event) {
		customer = new Customer();
		customer.setName(txtCustomerName.getText());
		customer.setAddress(txtAddress.getText());
		customer.setMobile(txtMobile.getText());
		this.dispose();
	}

}
