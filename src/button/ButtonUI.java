package button;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ButtonUI implements ActionListener{
	
JPanel p;
JButton b;
Button btn;
	
	public ButtonUI() throws RemoteException, AlreadyBoundException, UnknownHostException, MalformedURLException, NotBoundException{
		
		btn = new Button();
		
	    JFrame f = new JFrame( "Button" );
	    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    //f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    f.setSize(600, 600);
	    f.setLayout(new BorderLayout());
	    p = new JPanel(new BorderLayout());
	    GradientCircularButton b = new GradientCircularButton("Click me!!!");
//		b.setPreferredSize(new Dimension(450, 450));
	    b.addActionListener(this);
	    p.add(b,BorderLayout.CENTER);
	    p.setBackground(Color.GRAY);
	    f.add(p,BorderLayout.CENTER);
	    f.setVisible( true );
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			btn.press();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException, UnknownHostException, MalformedURLException, NotBoundException {
		new ButtonUI();
		
	}

	

}
