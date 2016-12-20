package button;

import java.rmi.Naming;
import java.rmi.RemoteException;

import lampButtonServer.ButtonInterface;
import lampButtonServer.Server;

public class Button implements ButtonInterface {

	@Override
	public void press() throws RemoteException {
		try {
			Server s = (Server) Naming.lookup("rmi://localhost:1099/Server"); //Proxy-Object
			s.sendMessage("press");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		Button b = new Button();
		try {
			b.press();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
