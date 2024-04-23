package serpiente;


import javax.imageio.ImageIO;
import javax.swing.JOptionPane;



import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

//juego contenido
public class Vcrear extends JPanel implements ActionListener{ //implementamos elactionlistener para que nuestra clase interactue connosostros
	//Variables
	static final int PANTALLA = 600;
	static final int TAM_CUADRO=25;
	static final int CUADROPE=(int) PANTALLA/TAM_CUADRO;
	static final int TOT_VIVORA = (PANTALLA*PANTALLA)/TAM_CUADRO;//determinar el tamaño de la sepiente
	
	int[] vivoraA=new int [TOT_VIVORA];//ARREGLO que guarda el tamaño del cuerpo total de la vivora
	int[] vivoraB=new int [TOT_VIVORA];
	int CU_vivora=3;//Inicializamos el tamaño del cuerpo de la vivora
	char camino='d';//Seleccion del teclado aswd
	
	private BufferedImage imagenComida; 
	private BufferedImage imagenCara;
	private BufferedImage imagenCuerpo;
	private BufferedImage imagenCuerpoV;
	
	int comidaA, comidaB;
	String op;
	//public int valor1;
	Random random = new Random(); 
	
	Menu objetoA = new Menu();
	
	/**/

	//objetoA.reproducirAudio(String ruta)
	//Constructor
	int veloz;
	Vcrear(int veloz){
		this.setPreferredSize(new Dimension(PANTALLA,PANTALLA));//especificamos tamaño cuadrado
		this.setFocusable(true);
		this.addKeyListener(new control()); //creamos el obejto control para interactuar con la vivora
		this.setBackground(Color.pink);//color al panel
		this.veloz=veloz;
		InicioVi();
		//this.velocidad=velocidad;
		try {
			imagenCuerpoV = ImageIO.read(new File("C:\\Users\\erika\\Downloads\\cuerV.png"));
			imagenCuerpo = ImageIO.read(new File("C:\\Users\\erika\\Downloads\\cuer.png"));
			imagenCara = ImageIO.read(new File("C:\\Users\\erika\\Downloads\\ser.png"));
            imagenComida = ImageIO.read(new File("C:\\Users\\erika\\Downloads\\man.png")); // Reemplaza con la ruta de tu imagen
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	boolean runnig =true;
	//final int DELAY = 100;//rapidez en la que se mueve nuestra viviora
	Timer timer; //ERROR DE IMPORTACION LINEA 3
	private class LogicaGuego extends Thread {
        @Override
        public void run() {
            while (runnig) {
                VivirVi();
                ReComidaVi();
                MuereVi();
                repaint();
                try {
                    Thread.sleep(veloz);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	public void InicioVi(){
		ComidaVi();
		//timer=new Timer (veloz,this);//ocupamos el timer y el valor de veloz lo que eligi en su nivel del juego
	    //timer.start();//Redibujamos nuestra ventana de juego 
	    LogicaGuego hilo = new LogicaGuego();
        hilo.start();
	}
	
	public void ComidaVi(){
		comidaA=random.nextInt(CUADROPE)*TAM_CUADRO;// indicamos que la comida solo se genera dentro del perimetro del panel
		comidaB=random.nextInt(CUADROPE)*TAM_CUADRO;
	}
	
	public void VivirVi(){
		for(int i=CU_vivora;i>0;i--){
			vivoraA[i]=vivoraA[i-1];//damomovilidad a la serpiente
			vivoraB[i]=vivoraB[i-1];
		}
		switch(camino){
		case'd':
			vivoraA[0]=vivoraA[0]+TAM_CUADRO;
			break;
			
	    case'a':
		vivoraA[0]=vivoraA[0]-TAM_CUADRO;
		break;
		
	    case'w':
			vivoraB[0]=vivoraB[0]-TAM_CUADRO;
			break;
	    case's':
			vivoraB[0]=vivoraB[0]+TAM_CUADRO;
			break;
		}
		
	}
	
	public void ReComidaVi(){
		if(vivoraA[0]==comidaA && vivoraB[0]==comidaB){
			CU_vivora++;
			objetoA.reproducirAudio("C:\\Users\\erika\\Downloads\\comi.wav");//Accedemos al metodo RA de la clase menu para ejecutar el audio
			ComidaVi();
		}
	}
	
	public void MuereVi(){
		//objetoA.reproducirAudio("C:\\Users\\erika\\Downloads\\over.wav");
		if(vivoraA[0]<0){
			objetoA.reproducirAudio("C:\\Users\\erika\\Downloads\\over.wav");
			runnig=false;
			JOptionPane.showMessageDialog(null, "GAME OVER");
		}
		if(vivoraB[0]<0){
			objetoA.reproducirAudio("C:\\Users\\erika\\Downloads\\over.wav");
			runnig=false;
			JOptionPane.showMessageDialog(null, "GAME OVER");
		}
		if(vivoraA[0]>PANTALLA-TAM_CUADRO){
			objetoA.reproducirAudio("C:\\Users\\erika\\Downloads\\over.wav");
			runnig=false;
			JOptionPane.showMessageDialog(null, "GAME OVER");
		}
		if(vivoraB[0]>PANTALLA-TAM_CUADRO){
			objetoA.reproducirAudio("C:\\Users\\erika\\Downloads\\over.wav");
			runnig=false;
			JOptionPane.showMessageDialog(null, "GAME OVER");
		}
		for (int i = 1; i < CU_vivora; i++) {
	        if (vivoraA[0] == vivoraA[i] && vivoraB[0] == vivoraB[i]) {
	        	objetoA.reproducirAudio("C:\\Users\\erika\\Downloads\\over.wav");
	            runnig = false;
	            JOptionPane.showMessageDialog(null, "GAME OVER");
	            break; // Detener el bucle si se encontró una colisión
	        }
	    }
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(runnig){ // verificaos que el jeugo siga para llamar a los siguentes metodos
			VivirVi();
			ReComidaVi();
			MuereVi();
		}
		repaint();// definimos el repaint para que se redibuje en funcion al timer 	
	}
	//sobre escribimos
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);// el component es para que se redibuje lo que hay dentro del metodo
		for(int i=0;i<CUADROPE;i++){
			g.drawLine(0, TAM_CUADRO*i, PANTALLA,TAM_CUADRO*i );//Dibujamos una linea 0 en A, TAM_CUADRO en B y el otro extremo va ser toda la panatlla
			g.drawLine(TAM_CUADRO*i,0,TAM_CUADRO*i,PANTALLA );//invertimos los lugares 
		}
		//g.setColor(Color.black);//Color de comida
		//g.fillOval(comidaA, comidaB, TAM_CUADRO, TAM_CUADRO);//forma de la comida
		if (imagenComida != null) {
          g.drawImage(imagenComida, comidaA, comidaB, TAM_CUADRO, TAM_CUADRO, this);
        }
		
		
		//if (imagenCara != null) {
		  //  g.drawImage(imagenCara, vivoraA[0], vivoraB[0], TAM_CUADRO, TAM_CUADRO, this);
		//}
		//g.setColor(Color.green);
		for(int i=1;i<CU_vivora;i++){
			if (imagenCara != null) {
				    g.drawImage(imagenCara, vivoraA[0], vivoraB[0], TAM_CUADRO, TAM_CUADRO, this);
				}
			
			//Esto lo hacemos para cambiar el 
		        if (vivoraA[i] == vivoraA[i - 1]) {
		            // La serpiente se está moviendo verticalmente
		            if (imagenCuerpoV != null) {
		                g.drawImage(imagenCuerpoV, vivoraA[i], vivoraB[i], TAM_CUADRO, TAM_CUADRO, this);
		            }
		        } else if (vivoraB[i] == vivoraB[i - 1]) {
		            // La serpiente se está moviendo horizontalmente
		            if (imagenCuerpo != null) {
		                g.drawImage(imagenCuerpo, vivoraA[i], vivoraB[i], TAM_CUADRO, TAM_CUADRO, this);
		            }
		        } 
			
			}//g.fillRect(vivoraA[i],vivoraB[i],TAM_CUADRO,TAM_CUADRO);	
		//dibujos cadasegmento que tendra la serpiente en este momento
		
	}
	
	public class control extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e){
			switch(e.getKeyChar()){
			case'd':
				camino='d';
				break;
			case'a':
				camino='a';
				break;
			case'w':
				camino='w';
				break;
			case's':
				camino='s';
				break;
			}
			
		}
		
	}
}
