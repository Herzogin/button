package button;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import buttonLampInterfaces.ControllerInterface;

public class Button extends java.rmi.server.UnicastRemoteObject implements buttonLampInterfaces.ButtonInterface {
	private ArrayList<ControllerInterface> observers;
	String version;
	
	protected Button(String version) throws RemoteException {
		super();
		this.version = version;
		observers = new ArrayList<ControllerInterface>();
	}

	@Override
	public void press() throws RemoteException {
		try {
			notifyObservers();	
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void notifyObservers() throws RemoteException {
		for(ControllerInterface observer : observers) {  
			observer.update(this.version);
		}
	}

	@Override
	public void register(ControllerInterface ci) throws RemoteException {
		observers.add(ci);
	}

	@Override
	public void unregister(ControllerInterface ci) throws RemoteException {
		observers.remove(ci);
	}
	
}
