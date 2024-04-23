package serpiente;


import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;



import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.DebugGraphics;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;

//juego contenido
//public class Vcrear extends JPanel implements ActionListener
public class Vcrear extends JPanel{ //implementamos elactionlistener para que nuestra clase interactue connosostros
	//Variables
	static final int PANTALLA = 600;
	static final int TAM_CUADRO=24;
	static final int CUADROPE=(int) PANTALLA/TAM_CUADRO;//basicamente es el tamaño del panel
	static final int TOT_VIBORA = (PANTALLA*PANTALLA)/TAM_CUADRO;//determinar el tamaño de la sepiente
	
	int[] viboraA=new int [TOT_VIBORA];//ARREGLO que guarda el tamaño del cuerpo total de la vibora
	int[] viboraB=new int [TOT_VIBORA];
	int CU_vibora=3;//Inicializamos el tamaño del cuerpo de la vibora
	int contadorPuntos = 0; //inicializamos el contador para el puntaje
	char camino='d';//Seleccion del teclado aswd
	
	
	private BufferedImage imagenComida; 
	private BufferedImage imagenCara;
	private BufferedImage imagenCuerpo; 
	private BufferedImage imagenCuerpoV;

	//Declaración de variables
	int comidaA, comidaB;
	String op,puntajesm;
	String names;
	LocalDate fechaActual;
	String puntos;
	//public int valor1;
	Random random = new Random(); 
	
	Menu objetoA = new Menu();

	
	/**/

	//objetoA.reproducirAudio(String ruta)
	//Constructor
	int veloz;
	//Clase para la visualización de la ventana
	Vcrear(int veloz){
		
		setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
		setFont(new Font("Arial Black", Font.PLAIN, 11));
		objetoA.frame1.setVisible(false);
		this.setPreferredSize(new Dimension(601, 642));//especificamos tamaño cuadrado
		this.setFocusable(true);
		this.addKeyListener(new control()); //creamos el obejto control para interactuar con la vibora
		this.setBackground(new Color(152, 251, 152));//color al panel
		this.veloz=veloz;//inicializamos la velocidad de la vibora
		setLayout(null);
		
		JLabel lblpuntaje = new JLabel("Puntaje:");
		lblpuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblpuntaje.setBackground(new Color(60, 179, 113));
		lblpuntaje.setFont(new Font("Arial", Font.BOLD, 12));
		lblpuntaje.setBounds(21, 602, 59, 29);
		add(lblpuntaje);
		
		puntajerec = new JTextField();
		puntajerec.setHorizontalAlignment(SwingConstants.CENTER);
		puntajerec.setRequestFocusEnabled(false);
		puntajerec.setEditable(false);
		puntajerec.setBorder(null);
		puntajerec.setBackground(new Color(143, 188, 143));
		puntajerec.setFont(new Font("Arial", Font.BOLD, 12));
		puntajerec.setBounds(80, 605, 59, 24);
		add(puntajerec);
		puntajerec.setColumns(10);
		
		JLabel puntajealto = new JLabel("Puntaje m\u00E1s alto:");
		puntajealto.setHorizontalAlignment(SwingConstants.CENTER);
		puntajealto.setFont(new Font("Arial", Font.BOLD, 12));
		puntajealto.setBackground(new Color(60, 179, 113));
		puntajealto.setBounds(210, 602, 98, 29);
		add(puntajealto);
        
		puntajemas = new JTextField();
		puntajemas.setRequestFocusEnabled(false);
		puntajemas.setHorizontalAlignment(SwingConstants.CENTER);
		puntajemas.setFont(new Font("Arial", Font.BOLD, 12));
		puntajemas.setEditable(false);
		puntajemas.setColumns(10);
		puntajemas.setBorder(null);
		puntajemas.setBackground(new Color(143, 188, 143));
		puntajemas.setBounds(318, 607, 204, 24);
		add(puntajemas);
		InicioVi(); //llamamos a la clase Iniciovi para que empiece a correr el hilo
		//this.velocidad=velocidad;
		//agregamos un try catch para que se vean las imagenes del juego agregadas
		try {
			imagenCuerpoV = ImageIO.read(new File("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\cuerV.png"));
			imagenCuerpo = ImageIO.read(new File("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\cuer.png"));
			imagenCara = ImageIO.read(new File("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\ser.png"));
            imagenComida = ImageIO.read(new File("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\apple.png")); // Reemplaza con la ruta de tu imagen
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	boolean runnig =true; //se le pone la variable true para que avance y se ejecute
	//final int DELAY = 100;//rapidez en la que se mueve nuestra vibora
	Timer timer; //ERROR DE IMPORTACION LINEA 3
	private JTextField puntajerec;
	private JTextField puntajemas;
	//clase donde llama a las clases para ejecutarlas en un mismo hilo
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
	
	//Está clase permite que se mueva nuestra vibora, básicamente es la lógica que hay dentro del juego
	public void InicioVi(){
		ComidaVi();
		
		//timer=new Timer (veloz,this);//ocupamos el timer y el valor de veloz lo que eligi en su nivel del juego
	    //timer.start();//Redibujamos nuestra ventana de juego 

	    LogicaGuego hilo = new LogicaGuego();
        hilo.start();
        
        //base de datos para el puntaje
		try
		{
			 // Conexión a la base de datos
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/snake","root","");
			System.out.println("Conectado correctamente"); //si sale esto quiere decir que el código funciono

			// Ahora, obtén el puntaje más alto y el nombre del ganador
						String query = "SELECT * FROM puntajes ORDER BY Puntaje ASC"
								+ "";
				        PreparedStatement stmt1 = conn.prepareStatement(query);
				        ResultSet rc = stmt1.executeQuery();
				        while (rc.next()) {
				        	String ganador = rc.getString("Jugador"); // Obtener el valor de la columna "Jugador" como String
				            String puntaje = rc.getString("Puntaje");  // Obtener el valor de la columna "Puntaje" como String
				            puntajemas.setText(ganador+" con "+puntaje);
				            
				        } 
						conn.close();
						stmt1.close();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Error al cargar el controlador"+e);
		}
		catch(SQLException e)
		{
			System.out.println("Error en la conexión"+e);
		}
	}
	
	//Clase que provoca que cuando coma una manzana se genere otra en un area random pero dentro del panel
	public void ComidaVi(){
		comidaA=random.nextInt(CUADROPE)*TAM_CUADRO;// indicamos que la comida solo se genera dentro del perimetro del panel
		comidaB=random.nextInt(CUADROPE)*TAM_CUADRO;
	}
	
	//Esta clase es la que le da movilidad a la serpiente, para que se vaya izq derecha arriba abajo
	public void VivirVi(){
		for(int i=CU_vibora;i>0;i--){
			viboraA[i]=viboraA[i-1];//damomovilidad a la serpiente
			viboraB[i]=viboraB[i-1];
		}
		//el switch case hace que nuestras teclas tengan funcionalidad 
		switch(camino){
		case'd':
			viboraA[0]=viboraA[0]+TAM_CUADRO;
			break;
			
	    case'a':
		viboraA[0]=viboraA[0]-TAM_CUADRO;
		break;
		
	    case'w':
			viboraB[0]=viboraB[0]-TAM_CUADRO;
			break;
	    case's':
			viboraB[0]=viboraB[0]+TAM_CUADRO;
			break;
		}
		
	}
	
	
	//En esta clase se incrementa el contador y cada que come una manzana hace que haga un sonido
	public void ReComidaVi(){
		if(viboraA[0]==comidaA && viboraB[0]==comidaB){
			CU_vibora++;
	        contadorPuntos++; // Incrementa el contador de puntos
	        String contador = Integer.toString(contadorPuntos);//Convierte un contador int en una string y se lo pasa a contador
	        puntajerec.setText(contador); //le establece a puntajerec la cantidad de puntos que logró acumular el contador
			objetoA.reproducirAudio("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\comi.wav");//Accedemos al metodo RA de la clase menu para ejecutar el audio
			ComidaVi();//se llama a la clase para que se ejecute
		}
	}
	
	//esta clase es para cuando la serpiente muere, condiciones de como se puede morir dentro del juego
	public void MuereVi(){
		//objetoA.reproducirAudio("C:\\Users\\erika\\Downloads\\over.wav");
		
		//condiciones de como morir
		if(viboraA[0]<0){
			objetoA.reproducirAudio("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\over.wav");
			runnig=false;
			ActualizarPuntaje();

			int resultado = JOptionPane.showConfirmDialog(null, "GAME OVER. ¿Quieres jugar de nuevo?", "Game Over", JOptionPane.YES_NO_OPTION);
			if (resultado == JOptionPane.YES_OPTION) {
	            // Cerrar la ventana actual
				Window w = SwingUtilities.getWindowAncestor(this);
		        w.dispose();
	            Menu m=new Menu();
	            m.frame1.setVisible(true);

			}
			else{
				System.exit(0);

			}


		}
		if(viboraB[0]<0){
			objetoA.reproducirAudio("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\over.wav");
			runnig=false;
			ActualizarPuntaje();

			//condición para que salga un mensaje de confirmación para seguir jugando o no
			int resultado = JOptionPane.showConfirmDialog(null, "GAME OVER. ¿Quieres jugar de nuevo?", "Game Over", JOptionPane.YES_NO_OPTION);
			if (resultado == JOptionPane.YES_OPTION) {
	            // Cerrar la ventana actual
				Window w = SwingUtilities.getWindowAncestor(this); 
		        w.dispose();
	            Menu m=new Menu();
	            m.frame1.setVisible(true);

			}
			else{

				System.exit(0);

			}


		}
		if(viboraA[0]>PANTALLA-TAM_CUADRO){
			objetoA.reproducirAudio("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\over.wav");
			runnig=false;
			ActualizarPuntaje();

			int resultado = JOptionPane.showConfirmDialog(null, "GAME OVER. ¿Quieres jugar de nuevo?", "Game Over", JOptionPane.YES_NO_OPTION);
			if (resultado == JOptionPane.YES_OPTION) {
	            // Cerrar la ventana actual
				Window w = SwingUtilities.getWindowAncestor(this);
		        w.dispose();
	            Menu m=new Menu();
	            m.frame1.setVisible(true);

			}
			else{
				System.exit(0);

			}

		}
		if(viboraB[0]>PANTALLA-TAM_CUADRO){
			objetoA.reproducirAudio("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\over.wav");
			runnig=false;
			ActualizarPuntaje();

			int resultado = JOptionPane.showConfirmDialog(null, "GAME OVER. ¿Quieres jugar de nuevo?", "Game Over", JOptionPane.YES_NO_OPTION);
			if (resultado == JOptionPane.YES_OPTION) {
	            // Cerrar la ventana actual
				Window w = SwingUtilities.getWindowAncestor(this);
		        w.dispose();
	            Menu m=new Menu();
	            m.frame1.setVisible(true);	

			}
			else{
				System.exit(0);

			}



		}
		for (int i = 1; i < CU_vibora; i++) {
	        if (viboraA[0] == viboraA[i] && viboraB[0] == viboraB[i]) {
	        	objetoA.reproducirAudio("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\over.wav");
	            runnig = false;
	            int resultado = JOptionPane.showConfirmDialog(null, "GAME OVER. ¿Quieres jugar de nuevo?", "Game Over", JOptionPane.YES_NO_OPTION);
				if (resultado == JOptionPane.YES_OPTION) {
		            // Cerrar la ventana actual
					Window w = SwingUtilities.getWindowAncestor(this);
			        w.dispose();
		            Menu m=new Menu();
		            m.frame1.setVisible(true);
				}
				else{
					System.exit(0);
				}
				ActualizarPuntaje();
	            break; // Detener el bucle si se encontró una colisión

	        }
	    }
		
	}
	
	/*@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(runnig){ // verificaos que el juego siga para llamar a los siguentes metodos
			VivirVi();
			ReComidaVi();
			MuereVi();
		}
		repaint();// definimos el repaint para que se redibuje en funcion al timer 	
	}*/
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
		for(int i=1;i<CU_vibora;i++){
			if (imagenCara != null) {
				    g.drawImage(imagenCara, viboraA[0], viboraB[0], TAM_CUADRO, TAM_CUADRO, this);
				}
			
			//Esto lo hacemos para cambiar el 
		        if (viboraA[i] == viboraA[i - 1]) {
		            // La serpiente se está moviendo verticalmente
		            if (imagenCuerpoV != null) {
		                g.drawImage(imagenCuerpoV, viboraA[i], viboraB[i], TAM_CUADRO, TAM_CUADRO, this);
		            }
		        } else if (viboraB[i] == viboraB[i - 1]) {
		            // La serpiente se está moviendo horizontalmente
		            if (imagenCuerpo != null) {
		                g.drawImage(imagenCuerpo, viboraA[i], viboraB[i], TAM_CUADRO, TAM_CUADRO, this);
		            }
		        } 
			
			}//g.fillRect(vivoraA[i],vivoraB[i],TAM_CUADRO,TAM_CUADRO);	
		//dibujos cadasegmento que tendra la serpiente en este momento
		
	}
	
	//clase que ayuda para ver cuando se apretan las teclas y ue hacer si se presionan
	public class control extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e){
			switch(e.getKeyChar()){
            case 'd':
                if (camino != 'a') {
                    camino = 'd';
                }
                break;
            case 'a':
                if (camino != 'd') {
                    camino = 'a';
                }
                break;
            case 'w':
                if (camino != 's') {
                    camino = 'w';
                }
                break;
            case 's':
                if (camino != 'w') {
                    camino = 's';
                }
                break;
			}
			
		}
		
	}


    
	 //clase para insertar puntajes a la bd
	public void ActualizarPuntaje() {
	    try {
	        // Conexión a la base de datos
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/snake", "root", "");
	        System.out.println("Conectado correctamente");

	        // Obtener valores necesarios
	        LocalDate fechaActual = LocalDate.now();
	        String puntos = puntajerec.getText();
	        System.out.println("Fecha: " + fechaActual);
	        System.out.println("Puntos: " + puntos);

	        // Actualización en la base de datos
	        String updateQuery = "UPDATE puntajes SET Puntaje = ? WHERE Fecha = ?";
	        PreparedStatement pstmt = conn.prepareStatement(updateQuery);
	        pstmt.setString(1, puntos);
	        pstmt.setObject(2, fechaActual);

	        int rowsAffected = pstmt.executeUpdate();
	        System.out.println("Filas afectadas: " + rowsAffected);

	        pstmt.close();
	        conn.close();
	    } catch (ClassNotFoundException e) {
	        System.out.println("Error al cargar el controlador: " + e.getMessage());
	    } catch (SQLException e) {
	        System.out.println("Error en la conexión: " + e.getMessage());
	    }
	}


}