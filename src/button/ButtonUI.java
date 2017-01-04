package button;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ButtonUI implements ActionListener{
	
JPanel p;
JButton b;
Button btn;
	
	public ButtonUI() throws RemoteException{
		
		btn = new Button();
		
	    JFrame f = new JFrame( "Button" );
	    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    p = new JPanel();
	    b = new JButton("Klick me!");
	    b.addActionListener(this);
	    p.setBackground(Color.GRAY);
	    p.add(b, BorderLayout.CENTER);
	    f.add(p);
	    
	    f.setVisible( true );
	    
	    
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			btn.press();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws RemoteException{
		new ButtonUI();
		
	}

	

}
