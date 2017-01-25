package button;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import buttonLampInterfaces.ControllerInterface;

public class Button extends java.rmi.server.UnicastRemoteObject implements buttonLampInterfaces.ButtonInterface {
	private ArrayList<ControllerInterface> observers;
	String version;
	
	protected Button(String version, String hostname) throws RemoteException, UnknownHostException, AlreadyBoundException {
		super();
		this.version = version;
		observers = new ArrayList<ControllerInterface>();
		
		Registry registry = LocateRegistry.getRegistry(3000);
		registry.bind("button"+"/"+hostname+"/"+System.currentTimeMillis(), this);
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
