package sunbeam.servicestation.ui;

import javax.swing.JOptionPane;

import sunbeam.servicestation.Main;
import sunbeam.servicestation.ServiceStation;

public class UiMain {
	public static void main(String[] args) {
		StringBuilder text = new StringBuilder(Main.welcome());
		text.append("GUI application is implemented to demonstrate the requirements clearly. Also it helps to understand which GUI controls to be used in various screens.\n\n");
		text.append("Since application is not developed as full version, validations and few small points are pending. Consider them while trying application.\n\n");
		text.append("Enjoy Programming!!\n\n");
		JOptionPane.showMessageDialog(null, text, "Java Programming @ Sunbeam Infotech", JOptionPane.INFORMATION_MESSAGE);
		ServiceStation.station.loadCustDetails();
		ServiceStation.station.loadBillDetails();
		ServiceStationFrame frm = new ServiceStationFrame();
		frm.setVisible(true);
	}
}
