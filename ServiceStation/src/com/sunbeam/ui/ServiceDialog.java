package sunbeam.servicestation.ui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sunbeam.servicestation.MaintenanceService;
import sunbeam.servicestation.OilService;
import sunbeam.servicestation.Service;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ServiceDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JRadioButton radOilService;
	private JRadioButton radMaintenance;
	private JLabel lblDesc;
	private JTextField txtDesc;
	private JLabel lblCost;
	private JTextField txtCost;
	private JButton btnSubmit;
	private ButtonGroup radGroup = new ButtonGroup();

	public ServiceDialog(Window parent) {
		super(parent);
		this.setTitle("New Service");
		initComponents();
	}

	private void initComponents() {
		setForeground(Color.black);
		setBackground(Color.white);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setLayout(null);
		add(getRadOilService());
		add(getRadMaintenance());
		add(getTxtDesc());
		add(getLblCost());
		add(getTxtCost());
		add(getBtnSubmit());
		add(getLblDesc());
		setSize(340, 231);
	}

	private JLabel getLblDesc() {
		if (lblDesc == null) {
			lblDesc = new JLabel();
			lblDesc.setText("Description: ");
			lblDesc.setBounds(20, 58, 86, 20);
		}
		return lblDesc;
	}

	private JRadioButton getRadMaintenance() {
		if (radMaintenance == null) {
			radMaintenance = new JRadioButton();
			radMaintenance.setText("Maintenance");
			radMaintenance.setBounds(169, 15, 104, 24);
			radMaintenance.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					if(radMaintenance.isSelected())
						lblCost.setText("Labor Charges: ");
				}
			});
			radGroup.add(radMaintenance);
		}
		return radMaintenance;
	}

	private JRadioButton getRadOilService() {
		if (radOilService == null) {
			radOilService = new JRadioButton();
			radOilService.setSelected(true);
			radOilService.setText("Oil Service");
			radOilService.setBounds(15, 16, 104, 24);
			radOilService.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					if(radOilService.isSelected())
						lblCost.setText("Oil Cost: ");
				}
			});
			radGroup.add(radOilService);
		}
		return radOilService;
	}

	private JButton getBtnSubmit() {
		if (btnSubmit == null) {
			btnSubmit = new JButton();
			btnSubmit.setText("Submit");
			btnSubmit.setBounds(104, 136, 81, 26);
			btnSubmit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onBtnSubmitClick();
				}
			});
		}
		return btnSubmit;
	}

	private JTextField getTxtCost() {
		if (txtCost == null) {
			txtCost = new JTextField();
			txtCost.setBounds(131, 93, 93, 20);
		}
		return txtCost;
	}

	private JLabel getLblCost() {
		if (lblCost == null) {
			lblCost = new JLabel();
			lblCost.setText("Oil Cost:");
			lblCost.setBounds(18, 93, 100, 20);
		}
		return lblCost;
	}

	private JTextField getTxtDesc() {
		if (txtDesc == null) {
			txtDesc = new JTextField();
			txtDesc.setBounds(130, 57, 174, 20);
		}
		return txtDesc;
	}

	private Service service = null;
	
	public Service getService() {
		return service;
	}

	private void onBtnSubmitClick() {
		String desc = txtDesc.getText();
		double cost = Double.parseDouble(txtCost.getText());
		if(radOilService.isSelected()) {
			OilService os = new OilService();
			os.setDesc(desc);
			os.setCost(cost);
			this.service = os;
		} else {
			MaintenanceService ms = new MaintenanceService();
			ms.setDesc(desc);
			ms.setLaborCharges(cost);
			this.service = ms;
		}
		this.dispose();
	}
}
