package button;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import buttonLampInterfaces.ServerInterface;

public class Button extends java.rmi.server.UnicastRemoteObject implements buttonLampInterfaces.ButtonInterface {
	
	protected Button() throws RemoteException {
		super();
		
	}

	@Override
	public void press() throws RemoteException {
		try {
			buttonLampInterfaces.ServerInterface s = (buttonLampInterfaces.ServerInterface) Naming.lookup("rmi://141.45.215.117:3000/Server"); //Proxy-Object
			
			s.sendMessage("press");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
