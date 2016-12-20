package button;

import java.rmi.Naming;
import java.rmi.RemoteException;

import lampButtonServer.ButtonInterface;
import lampButtonServer.ServerInterface;

public class Button extends java.rmi.server.UnicastRemoteObject implements ButtonInterface {

	protected Button() throws RemoteException {
		super();
	}

	@Override
	public void press() throws RemoteException {
		try {
			ServerInterface s = (ServerInterface) Naming.lookup("rmi://localhost:1099/Server"); //Proxy-Object
			s.sendMessage("press");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) throws RemoteException {
		Button b = new Button();
		try {
			b.press();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
