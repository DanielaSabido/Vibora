package serpiente;

import javax.swing.JFrame;

import java.awt.Frame;
import java.awt.Toolkit;

//juegoventana
public class Vprincipal extends JFrame{
	JFrame frame2;
	//esta clase es la que se encarga de como saldr� la pesta�a de vcrear y lo ejecuta.
	Vprincipal(int velocidad){
		setUndecorated(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\icon.png"));
		this.setTitle("Snake");
		getContentPane().add(new Vcrear(velocidad));//el nivel que eligio guarda un valor numerico y utiliza en la l�gica del juego
		//this.add(new Vcrear(velocidad));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();// dimensiona el marco de tal manera que el contenido del marco est� en o por encima de sus tama�os preferidos.
		this.setLocationRelativeTo(null);//centrar la venta 
		this.setVisible(true);		
	}
}
