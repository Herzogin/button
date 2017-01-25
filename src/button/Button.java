package button;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import org.htw.fiw.vs.IBinder;
import org.htw.fiw.vs.team1.ButtonInterface;
import org.htw.fiw.vs.team1.ControllerInterface;

public class Button extends java.rmi.server.UnicastRemoteObject implements ButtonInterface {
	private ArrayList<ControllerInterface> observers;
	String name;
	
	protected Button() throws RemoteException, UnknownHostException, AlreadyBoundException, MalformedURLException, NotBoundException {
		super();
		this.name = "button" + "/" + InetAddress.getLocalHost().getHostName() + "/" + System.currentTimeMillis();
		observers = new ArrayList<ControllerInterface>();
		
		IBinder registry = (IBinder) Naming.lookup("rmi://141.45.209.97/binder");
		registry.bind(this.name, this);
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
			observer.update(this.name);
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
