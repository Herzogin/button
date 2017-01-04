package button;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

import buttonLampInterfaces.ServerInterface;

public class Button extends java.rmi.server.UnicastRemoteObject implements buttonLampInterfaces.ButtonInterface {
Scanner scanner = new Scanner(System.in);
boolean insert = true;
	protected Button() throws RemoteException {
		super();
	}

	@Override
	public void press() throws RemoteException {
		try {
			buttonLampInterfaces.ServerInterface s = (buttonLampInterfaces.ServerInterface) Naming.lookup("rmi://localhost:3000/Server"); //Proxy-Object
			
			while(insert){
				System.out.println("typ 'press' to switch lamp and 'exit' to stop");
				String input = scanner.nextLine();
				input = input.toLowerCase();
				
				switch (input) {
				case "press":
					s.sendMessage(input);
					break;

				case "exit":
					scanner.close();
					System.out.println("Insert closed!");
					insert = false;
					break;
					
				default:
					System.out.println("wrong insert try again!");
					break;
				}
			}
			
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
