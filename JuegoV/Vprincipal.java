package serpiente;

import java.awt.LayoutManager;

import javax.swing.JFrame;

//juegoventana
public class Vprincipal extends JFrame{
	Vprincipal(int velocidad){
		this.setTitle("Vivora");
		this.add(new Vcrear(velocidad));//el nivel que eligio guarda un valor numerico y utiliza en la lógica del juego
		//this.add(new Vcrear(velocidad));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//terminar el proceso
		this.setResizable(false);//bloque de ventana
		this.pack();
		this.setLocationRelativeTo(null);//centrar la venta 
		this.setVisible(true);
		
	}
	

}
