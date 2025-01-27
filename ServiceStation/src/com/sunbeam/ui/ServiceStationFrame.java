package sunbeam.servicestation.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import sunbeam.servicestation.Bill;
import sunbeam.servicestation.Customer;
import sunbeam.servicestation.Main;
import sunbeam.servicestation.MaintenanceService;
import sunbeam.servicestation.Service;
import sunbeam.servicestation.ServiceRequest;
import sunbeam.servicestation.ServiceStation;
import sunbeam.servicestation.SparePart;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ServiceStationFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private ArrayList<ServiceRequest> activeServiceList = new ArrayList<>();
	private JList<String> listActiveServiceRequests;
	private JScrollPane jScrollPane0;
	private JButton btnNewServiceReq;
	private JButton btnFinishServiceReq;
	private JLabel lblCustomerName;
	private JLabel lblVehNumber;
	private JList<String> listSevicesList;
	private JScrollPane jScrollPane1;
	private JButton btnNewService;
	private JLabel lblServiceDesc;
	private JList<String> listSparePartList;
	private JScrollPane jScrollPane2;
	private JButton btnNewSparePart;
	private JLabel jLabel0;
	private JButton btnShowAccounts;
	private JTextField txtDate;
	private JTable tblBills;
	private JScrollPane scrollPaneBillsTable;
	private JButton btnSaveAll;
	private JButton btnPrintBill;
	private DefaultListModel<String> activeServiceRequestsListModel = new DefaultListModel<>();
	private DefaultListModel<String> sparePartListModel = new DefaultListModel<>();
	private DefaultListModel<String> serviceListModel = new DefaultListModel<>();
	private DefaultTableModel billsTableModel = new DefaultTableModel(new Object[]{"Details", "Amount"}, 0) {
		private static final long serialVersionUID = 1L;
		Class<?>[] types = new Class<?>[] { String.class, Double.TYPE };
		public Class<?> getColumnClass(int columnIndex) {
			return types[columnIndex];
		}	
	};
	private JLabel lblBusinessAmount;
	private List<Bill> billList;
	private ServiceRequest selServiceRequest;

	public ServiceStationFrame() {
		super(ServiceStation.name);
		initComponents();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				btnSaveAllActionActionPerformed(null);
				if(activeServiceRequestsListModel.getSize() > 0 || activeServiceList.size() > 0) {
					JOptionPane.showMessageDialog(ServiceStationFrame.this, "First finish all pending service requests!");
					return;
				}
				dispose();
			}
		});
	}

	private void initComponents() {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Vehicle Service Station - Java Programming @ SunBeam Infotech");
		setLayout(null);
		add(getLblServiceDesc());
		add(getJScrollPane2());
		add(getLblCustomerName());
		add(getLblVehNumber());
		add(getBtnNewSparePart());
		add(getJScrollPane1());
		add(getBtnNewService());
		add(getBtnNewServiceReq());
		add(getBtnFinishServiceReq());
		add(getJLabel0());
		add(getJScrollPane0());
		add(getBtnShowAccounts());
		add(getTxtDate());
		add(getBtnSaveAll());
		add(getBtnPrintBill());
		add(getScrollPaneBillsTable());
		add(getLblBusinessAmount());
		btnShowAccountsActionActionPerformed(null);
		setSize(740, 350);
	}

	private JLabel getLblBusinessAmount() {
		if (lblBusinessAmount == null) {
			lblBusinessAmount = new JLabel();
			lblBusinessAmount.setText("Total Business : ");
			lblBusinessAmount.setBounds(21, 234, 209, 16);
		}
		return lblBusinessAmount;
	}

	private JButton getBtnSaveAll() {
		if (btnSaveAll == null) {
			btnSaveAll = new JButton();
			btnSaveAll.setText("Save All");
			btnSaveAll.setBounds(134, 264, 95, 26);
			btnSaveAll.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnSaveAllActionActionPerformed(event);
				}
			});
		}
		return btnSaveAll;
	}

	private JButton getBtnPrintBill() {
		if (btnPrintBill == null) {
			btnPrintBill = new JButton();
			btnPrintBill.setText("Print Bill");
			btnPrintBill.setBounds(19, 263, 94, 26);
			btnPrintBill.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnPrintBillActionActionPerformed(event);
				}
			});
		}
		return btnPrintBill;
	}

	private JScrollPane getScrollPaneBillsTable() {
		if (scrollPaneBillsTable == null) {
			scrollPaneBillsTable = new JScrollPane();
			scrollPaneBillsTable.setBounds(16, 74, 217, 152);
			scrollPaneBillsTable.setViewportView(getTblBills());
		}
		return scrollPaneBillsTable;
	}

	private JTable getTblBills() {
		if (tblBills == null) {
			tblBills = new JTable();
			tblBills.setModel(billsTableModel);
			tblBills.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if(tblBills.getSelectedRow() >= 0) {
						int index = tblBills.getSelectedRow();
						Bill bill = billList.get(index);
						tblBillsSelectionChanged(bill);
					}
				}
			});
		}
		return tblBills;
	}

	private JTextField getTxtDate() {
		if (txtDate == null) {
			txtDate = new JTextField();
			Date today = Main.getToday();
			SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
			txtDate.setText(fmt.format(today));
			txtDate.setBounds(15, 16, 106, 39);
		}
		return txtDate;
	}

	private JButton getBtnShowAccounts() {
		if (btnShowAccounts == null) {
			btnShowAccounts = new JButton();
			btnShowAccounts.setText("Accounts");
			btnShowAccounts.setBounds(135, 13, 100, 44);
			btnShowAccounts.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnShowAccountsActionActionPerformed(event);
				}
			});
		}
		return btnShowAccounts;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Active Service Requests");
			jLabel0.setBounds(262, 16, 120, 16);
		}
		return jLabel0;
	}

	private JButton getBtnNewServiceReq() {
		if (btnNewServiceReq == null) {
			btnNewServiceReq = new JButton();
			btnNewServiceReq.setText("New Service Request");
			btnNewServiceReq.setBounds(254, 263, 139, 26);
			btnNewServiceReq.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnNewServiceReqActionActionPerformed(event);
				}
			});
		}
		return btnNewServiceReq;
	}

	private JButton getBtnFinishServiceReq() {
		if (btnFinishServiceReq == null) {
			btnFinishServiceReq = new JButton();
			btnFinishServiceReq.setText("Finish Request");
			btnFinishServiceReq.setBounds(254, 230, 139, 26);
			btnFinishServiceReq.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					btnFinishServiceReqActionActionPerformed(event);
				}
			});
		}
		return btnFinishServiceReq;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setBounds(254, 42, 137, 180);
			jScrollPane0.setViewportView(getListActiveServices());
		}
		return jScrollPane0;
	}

	private JList<String> getListActiveServices() {
		if (listActiveServiceRequests == null) {
			listActiveServiceRequests = new JList<>();
			listActiveServiceRequests.setModel(activeServiceRequestsListModel);
			listActiveServiceRequests.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					int index = listActiveServiceRequests.getSelectedIndex();
					if(index >= 0) {
						tblBills.clearSelection();
						ServiceRequest req = activeServiceList.get(index);
						selServiceRequest = req;
						displayServiceRequestDetails(req);
					}
				}
			});
		}
		return listActiveServiceRequests;
	}

	private JButton getBtnNewSparePart() {
		if (btnNewSparePart == null) {
			btnNewSparePart = new JButton();
			btnNewSparePart.setText("New Part");
			btnNewSparePart.setBounds(574, 264, 137, 26);
			btnNewSparePart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					btnNewSparePartActionActionPerformed(event);
				}
			});
		}
		return btnNewSparePart;
	}

	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(570, 70, 139, 175);
			jScrollPane2.setViewportView(getListPartList());
		}
		return jScrollPane2;
	}

	private JList<String> getListPartList() {
		if (listSparePartList == null) {
			listSparePartList = new JList<>();
			listSparePartList.setModel(sparePartListModel);
		}
		return listSparePartList;
	}

	private JLabel getLblServiceDesc() {
		if (lblServiceDesc == null) {
			lblServiceDesc = new JLabel();
			lblServiceDesc.setText("ServiceDesc");
			lblServiceDesc.setBounds(570, 8, 121, 16);
		}
		return lblServiceDesc;
	}

	private JButton getBtnNewService() {
		if (btnNewService == null) {
			btnNewService = new JButton();
			btnNewService.setText("New Service");
			btnNewService.setBounds(415, 265, 136, 26);
			btnNewService.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					btnNewServiceActionActionPerformed(event);
				}
			});
		}
		return btnNewService;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(414, 68, 136, 179);
			jScrollPane1.setViewportView(getListServiceList());
		}
		return jScrollPane1;
	}

	private JList<String> getListServiceList() {
		if (listSevicesList == null) {
			listSevicesList = new JList<>();
			listSevicesList.setModel(serviceListModel);
			listSevicesList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					int index = listSevicesList.getSelectedIndex();
					if(index >= 0 && selServiceRequest!=null) {
						Service serv = selServiceRequest.getServiceList().get(index);
						lstServicesListSelectionChanged(serv);
					}
				}
			});
		}
		return listSevicesList;
	}

	private JLabel getLblCustomerName() {
		if (lblCustomerName == null) {
			lblCustomerName = new JLabel();
			lblCustomerName.setText("customerName");
			lblCustomerName.setBounds(413, 13, 137, 16);
		}
		return lblCustomerName;
	}

	private JLabel getLblVehNumber() {
		if (lblVehNumber == null) {
			lblVehNumber = new JLabel();
			lblVehNumber.setText("vehicleNumber");
			lblVehNumber.setBounds(413, 39, 136, 16);
		}
		return lblVehNumber;
	}

	private void btnPrintBillActionActionPerformed(ActionEvent event) {
		int index = tblBills.getSelectedRow();
		if(index < 0) {
			JOptionPane.showMessageDialog(this, "Select one bill");
			return;
		}
		Bill bill = billList.get(index);
		String filePath = JOptionPane.showInputDialog(this, "Enter Bill Print File Name : ");
		try {
			FileOutputStream out = new FileOutputStream(filePath);
			bill.print(out);
			out.close();
			String[] args = new String[] {"notepad.exe", filePath};
			Runtime.getRuntime().exec(args);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.toString());
		}		
	}

	private void btnSaveAllActionActionPerformed(ActionEvent event) {
		ServiceStation.station.storeCustDetails();
		ServiceStation.station.storeBillDetails();
	}

	private void btnNewServiceReqActionActionPerformed(ActionEvent event) {
		ServiceReqDialog dlg = new ServiceReqDialog(this);
		dlg.setModal(true);
		dlg.setVisible(true);
		ServiceRequest req = dlg.getServiceRequest();
		if(req!=null) {
			activeServiceList.add(req);
			activeServiceRequestsListModel.addElement(req.toString());
			int index = activeServiceRequestsListModel.getSize() - 1;
			listActiveServiceRequests.setSelectedIndex(index);
		}
	}

	private void btnNewServiceActionActionPerformed(ActionEvent event) {
		if(listActiveServiceRequests.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, "Select One Active Service First.");
			return;
		}
		ServiceDialog dlg = new ServiceDialog(this);
		dlg.setModal(true);
		dlg.setVisible(true);
		Service newService = dlg.getService();
		if(newService!=null) {
			selServiceRequest.newService(newService);
			serviceListModel.addElement(newService.toString());
			listSevicesList.setSelectedIndex(serviceListModel.getSize()-1);
		}
	}

	private void btnNewSparePartActionActionPerformed(ActionEvent event) {
		if(listActiveServiceRequests.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, "Spare Parts can be Added Only for Active Requests");
			return;
		}
		int index = listSevicesList.getSelectedIndex();
		if(index < 0) {
			JOptionPane.showMessageDialog(this, "Please Select a Maintenance Service");
			return;
		}
		Service serv = selServiceRequest.getServiceList().get(index);
		if(!(serv instanceof MaintenanceService)) {
			JOptionPane.showMessageDialog(this, "Please Select a Maintenance Service");
			return;
		}
		
		MaintenanceService mntService = (MaintenanceService) serv;
		SparePartDialog dlg = new SparePartDialog(this);
		dlg.setModal(true);
		dlg.setVisible(true);
		
		SparePart part = dlg.getSparePart();
		if(part!=null) {
			mntService.newSparePart(part);
			sparePartListModel.addElement(part.toString());
		}
	}

	private void btnShowAccountsActionActionPerformed(ActionEvent event) {
		String dateStr = txtDate.getText();
		Date date = Main.getDate(dateStr);
		double total = 0.0;
		billList = ServiceStation.station.getBillList(date);
		while(billsTableModel.getRowCount() > 0)
			billsTableModel.removeRow(0);
		for (Bill bill : billList) {
			Object[] rowData = new Object[] {bill.getServiceRequest().toString(), bill.computeTotalBill() };
			billsTableModel.addRow(rowData);
			total+=bill.getPaidAmount();
		}
		lblBusinessAmount.setText("Total Business: Rs. " + total);
	}
	
	private void tblBillsSelectionChanged(Bill bill) {
		listActiveServiceRequests.clearSelection();
		ServiceRequest req = bill.getServiceRequest();
		this.selServiceRequest = req;
		displayServiceRequestDetails(req);
	}

	private void lstServicesListSelectionChanged(Service serv) {
		sparePartListModel.removeAllElements();
		if(serv instanceof MaintenanceService) {
			MaintenanceService mntService = (MaintenanceService) serv;
			for (SparePart part : mntService.getPartList()) {
				sparePartListModel.addElement(part.toString());
			}
			lblServiceDesc.setText("Parts Changed : " + mntService.getPartList().size());
		}
		else
			lblServiceDesc.setText("Oil Service");
	}

	private void displayServiceRequestDetails(ServiceRequest req) {
		lblCustomerName.setText(req.getCustomerName());
		lblVehNumber.setText(req.getVehicleNumber());
		LinkedList<Service> servList = req.getServiceList();
		serviceListModel.removeAllElements();
		for (Service serv : servList) {
			serviceListModel.addElement(serv.toString());
		}
		if(serviceListModel.getSize() > 0) {
			listSevicesList.setSelectedIndex(0);
		}
	}
	
	protected void btnFinishServiceReqActionActionPerformed(ActionEvent event) {
		int index = listActiveServiceRequests.getSelectedIndex();
		if(index < 0) {
			JOptionPane.showMessageDialog(this, "Select Some Active Service First");
			return;
		}
		ServiceRequest req = activeServiceList.get(index);
		BillDialog dlg = new BillDialog(this, req);
		dlg.setModal(true);
		dlg.setVisible(true);
		
		Bill bill = dlg.getBill();
		if(bill!=null) {
			Customer cust = ServiceStation.station.findCustomer(req.getCustomerName());
			double balance = cust.payBill(bill, dlg.getPaidAmount());
			String text = String.format("Bill Paid.\r\nBalance Amount : %.2f", balance);
			JOptionPane.showMessageDialog(this, text);
			ServiceStation.station.newBill(bill);
			activeServiceList.remove(index);
			activeServiceRequestsListModel.remove(index);
			serviceListModel.removeAllElements();
			sparePartListModel.removeAllElements();
			lblCustomerName.setText("");
			lblVehNumber.setText("");
			lblServiceDesc.setText("");
			selServiceRequest = null;
			if(activeServiceRequestsListModel.getSize() > 0)
				listActiveServiceRequests.setSelectedIndex(0);
		}
	}
}
