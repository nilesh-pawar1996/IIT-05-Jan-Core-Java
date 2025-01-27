package sunbeam.servicestation.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sunbeam.servicestation.Bill;
import sunbeam.servicestation.Main;
import sunbeam.servicestation.ServiceRequest;

//VS4E -- DO NOT REMOVE THIS LINE!
public class BillDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextArea txtBill;
	private JScrollPane jScrollPane0;
	private JTextField txtPaidAmount;
	private JLabel jLabel0;
	private JButton btnBillPaid;
	private JLabel jLabel1;
	private JTextField txtTotalBill;
	private ServiceRequest serviceRequest;
	private Bill bill;
	private double paidAmount;
	public BillDialog(Window parent, ServiceRequest serviceRequest) {
		super(parent);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				bill = null;
			}
		});
		this.serviceRequest = serviceRequest;
		this.bill = new Bill(serviceRequest, Main.getToday());
		this.bill.computeAmount();
		initComponents();
	}

	public Bill getBill() {
		return bill;
	}
	
	public double getPaidAmount() {
		return paidAmount;
	}

	private void initComponents() {
		setForeground(Color.black);
		setBackground(Color.white);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setLayout(null);
		setTitle("Bill : " + this.serviceRequest);
		add(getJScrollPane0());
		add(getJLabel0());
		add(getBtnBillPaid());
		add(getTxtTotalBill());
		add(getJLabel1());
		add(getTxtPaidAmount());
		setSize(605, 371);
	}

	private JTextField getTxtTotalBill() {
		if (txtTotalBill == null) {
			txtTotalBill = new JTextField();
			txtTotalBill.setEditable(false);
			String strAmount = String.format("%.2f", bill.computeTotalBill());
			txtTotalBill.setText(strAmount);
			txtTotalBill.setBounds(109, 268, 103, 34);
		}
		return txtTotalBill;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Total Bill : ");
			jLabel1.setBounds(30, 277, 61, 16);
		}
		return jLabel1;
	}

	private JButton getBtnBillPaid() {
		if (btnBillPaid == null) {
			btnBillPaid = new JButton();
			btnBillPaid.setText("Bill Paid");
			btnBillPaid.setBounds(492, 272, 81, 26);
			btnBillPaid.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnBillPaidActionActionPerformed(event);
				}
			});
		}
		return btnBillPaid;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Paid Amount : ");
			jLabel0.setBounds(249, 277, 86, 16);
		}
		return jLabel0;
	}

	private JTextField getTxtPaidAmount() {
		if (txtPaidAmount == null) {
			txtPaidAmount = new JTextField();
			txtPaidAmount.setText("0");
			txtPaidAmount.setBounds(358, 268, 103, 34);
		}
		return txtPaidAmount;
	}

	private JTextArea getTxtBill() {
		if (txtBill == null) {
			txtBill = new JTextArea();
			txtBill.setEditable(false);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			bill.print(out);
			String billText = out.toString();
			txtBill.setText(billText);
		}
		return txtBill;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setBounds(7, 8, 587, 249);
			jScrollPane0.setViewportView(getTxtBill());
		}
		return jScrollPane0;
	}

	public BillDialog() {
		initComponents();
	}

	private void btnBillPaidActionActionPerformed(ActionEvent event) {
		paidAmount = Double.parseDouble(txtPaidAmount.getText());
		dispose();
	}
}
