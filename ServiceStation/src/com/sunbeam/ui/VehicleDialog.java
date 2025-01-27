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
import sunbeam.servicestation.Vehicle;

//VS4E -- DO NOT REMOVE THIS LINE!
public class VehicleDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel1;
	private JLabel jLabel0;
	private JLabel jLabel2;
	private JTextField txtVehNumber;
	private JTextField txtCompany;
	private JTextField txtModel;
	private JButton btnSubmit;

	public VehicleDialog(Window parent) {
		super(parent);
		this.setTitle("New Vehicle");
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
		add(getTxtVehNumber());
		add(getTxtCompany());
		add(getTxtModel());
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

	private JTextField getTxtModel() {
		if (txtModel == null) {
			txtModel = new JTextField();
			txtModel.setBounds(127, 88, 150, 20);
		}
		return txtModel;
	}

	private JTextField getTxtCompany() {
		if (txtCompany == null) {
			txtCompany = new JTextField();
			txtCompany.setBounds(125, 54, 152, 20);
		}
		return txtCompany;
	}

	private JTextField getTxtVehNumber() {
		if (txtVehNumber == null) {
			txtVehNumber = new JTextField();
			txtVehNumber.setBounds(125, 18, 155, 20);
		}
		return txtVehNumber;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Model : ");
			jLabel2.setBounds(16, 87, 70, 16);
		}
		return jLabel2;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Veh Number : ");
			jLabel0.setBounds(17, 18, 107, 16);
		}
		return jLabel0;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Company : ");
			jLabel1.setBounds(16, 49, 113, 16);
		}
		return jLabel1;
	}

	private Vehicle vehicle = null;
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	private void btnSubmitActionActionPerformed(ActionEvent event) {
		vehicle = new Vehicle();
		vehicle.setNumber(txtVehNumber.getText());
		vehicle.setCompany(txtCompany.getText());
		vehicle.setModel(txtModel.getText());
		this.dispose();
	}
}
