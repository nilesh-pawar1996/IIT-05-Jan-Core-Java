package sunbeam.servicestation.ui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import sunbeam.servicestation.Customer;
import sunbeam.servicestation.ServiceRequest;
import sunbeam.servicestation.ServiceStation;
import sunbeam.servicestation.Vehicle;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ServiceReqDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel lblCustomer;
	private JComboBox<String> cmbCustomer;
	private JLabel lblVehicle;
	private JComboBox<String> cmbVehicle;
	private JButton btnSubmit;


	public ServiceReqDialog(Window parent) {
		super(parent);
		this.setTitle("New Service Request");
		initComponents();
	}

	private void initComponents() {
		setForeground(Color.black);
		setBackground(Color.white);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setLayout(null);
		add(getLblCustomer());
		add(getCmbCustomer());
		add(getLblVehicle());
		add(getCmbVehicle());
		add(getBtnSubmit());
		setSize(273, 160);
	}

	private JButton getBtnSubmit() {
		if (btnSubmit == null) {
			btnSubmit = new JButton();
			btnSubmit.setText("Submit");
			btnSubmit.setBounds(96, 91, 81, 26);
			btnSubmit.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnSubmitActionActionPerformed(event);
				}
			});
		}
		return btnSubmit;
	}

	private JComboBox<String> getCmbVehicle() {
		if (cmbVehicle == null) {
			cmbVehicle = new JComboBox<String>();
			cmbVehicle.setModel(new DefaultComboBoxModel<String>(new String[] { "-Select Vehicle", "* New Vehicle" }));
			cmbVehicle.setDoubleBuffered(false);
			cmbVehicle.setBorder(null);
			cmbVehicle.setBounds(96, 46, 149, 25);
			cmbVehicle.addItemListener(new ItemListener() {
	
				public void itemStateChanged(ItemEvent event) {
					cmbVehicleItemItemStateChanged(event);
				}
			});
		}
		return cmbVehicle;
	}

	private JLabel getLblVehicle() {
		if (lblVehicle == null) {
			lblVehicle = new JLabel();
			lblVehicle.setText("Vehicle : ");
			lblVehicle.setBounds(19, 51, 66, 16);
		}
		return lblVehicle;
	}

	private JComboBox<String> getCmbCustomer() {
		if (cmbCustomer == null) {
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(new String[] { "-Select Customer", "* New Customer" });
			cmbCustomer = new JComboBox<>();
			cmbCustomer.setModel(model);
			cmbCustomer.setDoubleBuffered(false);
			cmbCustomer.setBorder(null);
			cmbCustomer.setBounds(96, 11, 150, 25);
			cmbCustomer.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent event) {
					cmbCustomerItemItemStateChanged(event);
				}
			});
			for (Customer cust : ServiceStation.station.getCustList()) {
				model.addElement(cust.getName());
			}
		}
		return cmbCustomer;
	}


	private JLabel getLblCustomer() {
		if (lblCustomer == null) {
			lblCustomer = new JLabel();
			lblCustomer.setText("Customer : ");
			lblCustomer.setBounds(20, 14, 69, 16);
		}
		return lblCustomer;
	}
	
	private void cmbCustomerItemItemStateChanged(ItemEvent event) {
		DefaultComboBoxModel<String> model ;
		if (event.getStateChange() == ItemEvent.SELECTED) {
	          String item = (String) event.getItem();
	          if(item.startsWith("-"))
	        	  return;
	          if(item.startsWith("*")) {
	        	  CustomerDialog dialog = new CustomerDialog(this);
	        	  dialog.setModal(true);
	        	  dialog.setVisible(true);
	        	  Customer newCust = dialog.getCustomer();
	        	  if(newCust!=null) {
		        	  ServiceStation.station.newCustomer(newCust);
		        	  model = (DefaultComboBoxModel<String>) cmbCustomer.getModel();
		        	  model.addElement(newCust.getName());
		        	  cmbCustomer.setSelectedItem(newCust.getName());
		        	  model = (DefaultComboBoxModel<String>) cmbVehicle.getModel();
		        	  model.removeAllElements();
		        	  model.addElement("-Select Vehicle");
		        	  model.addElement("* New Vehicle");
	        	  }
	          } else {
	        	  String customerName = (String) cmbCustomer.getSelectedItem();
	        	  Customer cust = ServiceStation.station.findCustomer(customerName);
	        	  if(cust!=null) {
	        		  model = (DefaultComboBoxModel<String>) cmbVehicle.getModel();
	        		  model.removeAllElements();
	        		  model.addElement("-Select Vehicle");
	        		  model.addElement("* New Vehicle");
	        		  for (Vehicle veh : cust.getVehicleList()) {
	        			  model.addElement(veh.getNumber());
	        		  }
	        	  }
	          }
	    }
	}

	private void cmbVehicleItemItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
	          String item = (String) event.getItem();
	          if(item.startsWith("-"))
	        	  return;
	          if(item.startsWith("*")) {
	        	  VehicleDialog dialog = new VehicleDialog(this);
	        	  dialog.setModal(true);
	        	  dialog.setVisible(true);
	        	  String customerName = (String) cmbCustomer.getSelectedItem();
	        	  Customer cust = ServiceStation.station.findCustomer(customerName);
	        	  if(cust==null) {
	        		  JOptionPane.showMessageDialog(this, "Select Customer First!");
	        		  return;
	        	  }
	        	  Vehicle newVeh = dialog.getVehicle();
	        	  if(newVeh!=null) {
		        	  cust.newVehicle(newVeh);
		        	  DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) cmbVehicle.getModel();
		        	  model.addElement(newVeh.getNumber());
		        	  cmbVehicle.setSelectedItem(newVeh.getNumber());
	        	  }
	          } 
	    }
	}
	
	private ServiceRequest serviceRequest = null;
	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	private void btnSubmitActionActionPerformed(ActionEvent event) {
		String custName = (String) cmbCustomer.getSelectedItem();
		String vehNumber = (String) cmbVehicle.getSelectedItem();
		if(custName.startsWith("*") || custName.startsWith("-") || vehNumber.startsWith("*") || vehNumber.startsWith("-")) {
			JOptionPane.showMessageDialog(this, "Select Customer & Vehicle Properly.");
			return;
		}
		serviceRequest = new ServiceRequest();
		serviceRequest.setCustomerName(custName);
		serviceRequest.setVehicleNumber(vehNumber);
		this.dispose();
	}
}

