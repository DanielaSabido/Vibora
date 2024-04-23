package serpiente;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Menu {

	 JFrame frame1;
	  JTextField textField;
	  String miVariable;

	/**
	 * Launch the application.
	 * @param vcrear 
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio_V window = new Inicio_V();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}
	
	//clase que se usa para poner sonidos y audios que se escuchen dentro del juego
	public void reproducirAudio(String ruta) {
        try {
            File archivoAudio = new File(ruta);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoAudio);
            Clip clip = AudioSystem.getClip();

            // Agrega un oyente para manejar eventos de línea (reproducción completa)
            clip.addLineListener(new LineListener() {
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                }
            });

            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame1 = new JFrame();
		frame1.setBackground(new Color(152, 251, 152));
		frame1.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\icon.png"));
		frame1.setUndecorated(true);
		frame1.getContentPane().setBackground(new Color(46, 139, 87));
		frame1.setBounds(100, 100, 450, 391);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		frame1.setLocationRelativeTo(null); // Centra el JFrame en la pantalla
		
		JLabel lblControl = new JLabel("Controles:");
		lblControl.setHorizontalAlignment(SwingConstants.CENTER);
		lblControl.setBounds(299, 111, 79, 26);
		lblControl.setFont(new Font("Arial Black", Font.PLAIN, 12));
		frame1.getContentPane().add(lblControl);
		
		JButton btnW = new JButton("W");
		btnW.setBounds(315, 148, 50, 23);
		btnW.setFont(new Font("Arial Black", Font.PLAIN, 12));
		frame1.getContentPane().add(btnW);
		btnW.setEnabled(false);
		
		JButton btnS = new JButton("S");
		btnS.setBounds(315, 184, 50, 23);
		btnS.setFont(new Font("Arial Black", Font.PLAIN, 12));
		frame1.getContentPane().add(btnS);
		btnS.setEnabled(false);
		
		JButton btnA = new JButton("A");
		btnA.setBounds(255, 184, 50, 23);
		btnA.setFont(new Font("Arial Black", Font.PLAIN, 12));
		frame1.getContentPane().add(btnA);
		btnA.setEnabled(false);
		
		JButton btnD = new JButton("D");
		btnD.setBounds(375, 184, 44, 23);
		btnD.setFont(new Font("Arial Black", Font.PLAIN, 12));
		frame1.getContentPane().add(btnD);
		btnD.setEnabled(false);
		
		JLabel lblNamePlayer = new JLabel("NOMBRE DEL JUGADOR");
		lblNamePlayer.setBounds(10, 111, 192, 14);
		lblNamePlayer.setFont(new Font("Arial Black", Font.BOLD, 12));
		frame1.getContentPane().add(lblNamePlayer);
		
		textField = new JTextField();
		textField.setBackground(new Color(152, 251, 152));
		textField.setBounds(10, 129, 155, 20);
		textField.setFont(new Font("Arial Black", Font.PLAIN, 12));
		frame1.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNivel = new JLabel("NIVEL");
		lblNivel.setBounds(48, 180, 59, 14);
		lblNivel.setFont(new Font("Arial Black", Font.PLAIN, 15));
		frame1.getContentPane().add(lblNivel);
		
		JRadioButton rdbtnFacil = new JRadioButton("Facil");
		rdbtnFacil.setFocusPainted(false);
		rdbtnFacil.setBackground(new Color(152, 251, 152));
		rdbtnFacil.setBounds(25, 201, 109, 23);
		rdbtnFacil.setFont(new Font("Arial Black", Font.PLAIN, 15));
		frame1.getContentPane().add(rdbtnFacil);
		
		JRadioButton rdbtnMedio = new JRadioButton("Medio");
		rdbtnMedio.setFocusPainted(false);
		rdbtnMedio.setBackground(new Color(152, 251, 152));
		rdbtnMedio.setBounds(25, 240, 109, 23);
		rdbtnMedio.setFont(new Font("Arial Black", Font.PLAIN, 15));
		frame1.getContentPane().add(rdbtnMedio);
		
		JRadioButton rdbtnDificil = new JRadioButton("Dificil");
		rdbtnDificil.setFocusPainted(false);
		rdbtnDificil.setBackground(new Color(152, 251, 152));
		rdbtnDificil.setBounds(25, 280, 109, 23);
		rdbtnDificil.setFont(new Font("Arial Black", Font.PLAIN, 15));
		frame1.getContentPane().add(rdbtnDificil);
		
		ButtonGroup group = new ButtonGroup();
        group.add(rdbtnFacil);
        group.add(rdbtnMedio);
        group.add(rdbtnDificil);
        
        
		
		JButton btnIniciar = new JButton("INICIAR");
		btnIniciar.setBackground(new Color(152, 251, 152));
		btnIniciar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnIniciar.setFocusPainted(false);
		btnIniciar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnIniciar.setBorderPainted(false);
		btnIniciar.setBounds(183, 357, 89, 23);
		//action listener para el boton de iniciar, que se ejecute el hilo y el juego cuando se presiona
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//File filepath= new File(C:\\Users\\erika\\Downloads\\incio.wav);
				reproducirAudio("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\inicio.wav"); 
				int f=0,t=0,sr=0;
				miVariable=textField.getText();
				 Jugador();
				if (rdbtnFacil.isSelected()) {
				   f=1;
				   sr=200;
				   //rf=Integer.parseInt(rdbtnFacil.getText());
				}
				if (rdbtnMedio.isSelected()) {
					   f=1;
					   sr=130;
					  // rm=Integer.parseInt(rdbtnMedio.getText());
					}
				if (rdbtnDificil.isSelected()) {
					   f=1;
					   sr=80;
					   //rf=Integer.parseInt(rdbtnDificil.getText());
					}
				//Vcrear  objtoCal = new Vcrear (sr);
				if (!miVariable.isEmpty()) {
                    t=1;
                    
                }
				else{
					textField.setText("Player");
				}
				if(f==1 && t==1) {
					 //int retrasoMilisegundos = 1500;
		             //Pausamos el hilo principal durante 1 segundo utilizando Thread.sleep y luego creamos la intancia Vprincipal   
					 try {
		                    Thread.sleep(1000);
		                } catch (InterruptedException ex) {
		                    Thread.currentThread().interrupt();
		                }
					Vprincipal otra=new Vprincipal(sr);//guarda el valor numerico de sr para usarlo el la clase Vcrear
					otra.setVisible(true);
					frame1.dispose();

					//new Vprincipal ();
				}
				else {
				JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
				}
			}
		});
		frame1.getContentPane().add(btnIniciar);
		
		JLabel lblWHaciaArriba = new JLabel("W hacia arriba");
		lblWHaciaArriba.setBounds(289, 218, 110, 14);
		lblWHaciaArriba.setFont(new Font("Arial Black", Font.PLAIN, 12));
		frame1.getContentPane().add(lblWHaciaArriba);
		
		JLabel lblSHaciaAbajo = new JLabel("S hacia abajo");
		lblSHaciaAbajo.setBounds(289, 244, 89, 14);
		lblSHaciaAbajo.setFont(new Font("Arial Black", Font.PLAIN, 12));
		frame1.getContentPane().add(lblSHaciaAbajo);
		
		JLabel lblAHaciaLa = new JLabel("A hacia la izquierda");
		lblAHaciaLa.setBounds(289, 272, 129, 14);
		lblAHaciaLa.setFont(new Font("Arial Black", Font.PLAIN, 12));
		frame1.getContentPane().add(lblAHaciaLa);
		
		JLabel lblDHaciaLa = new JLabel("D hacia la derecha");
		lblDHaciaLa.setBounds(289, 297, 139, 14);
		lblDHaciaLa.setFont(new Font("Arial Black", Font.PLAIN, 12));
		frame1.getContentPane().add(lblDHaciaLa);
		
		
		JButton Cerrar = new JButton("X");
		Cerrar.setBorderPainted(false);
		Cerrar.setBackground(new Color(46, 139, 87));
		Cerrar.setBorder(null);
		Cerrar.setFocusPainted(false);
		//action listener para el botón de cerrar que si se presiona se cierra el juego.
		Cerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame1.dispose();

			}
		});
		Cerrar.setFont(new Font("Arial Black", Font.BOLD, 17));
		Cerrar.setBounds(0, 0, 59, 26);
		frame1.getContentPane().add(Cerrar);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(152, 251, 152));
		panel.setBounds(0, 34, 450, 48);
		frame1.getContentPane().add(panel);
		
		JLabel lblJuegoVivora = new JLabel("SNAKE");
		panel.add(lblJuegoVivora);
		lblJuegoVivora.setHorizontalAlignment(SwingConstants.CENTER);
		lblJuegoVivora.setFont(new Font("Bernard MT Condensed", Font.BOLD, 40));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\snake.png"));
		label.setBounds(203, 111, 69, 57);
		frame1.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\snake.png"));
		label_1.setBounds(330, 323, 69, 57);
		frame1.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\icon.png"));
		label_2.setBounds(176, 221, 96, 82);
		frame1.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("C:\\Users\\danie\\OneDrive\\Documentos\\TOP\\JuegoV\\src\\serpiente\\imagen y audio\\snake.png"));
		label_3.setBounds(83, 323, 69, 57);
		frame1.getContentPane().add(label_3);
		frame1.setVisible(true);
	}
	 public void Jugador(){

		    try {
		        // Conexión a la base de datos
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/snake", "root", "");
		        System.out.println("Conectado correctamente");

		        // Obtener valores necesarios
		     // Creas una instancia de la clase MiClase

		        LocalDate fechaActual = LocalDate.now();
		        System.out.println("Nombre: " + miVariable);
		        System.out.println("Fecha: " + fechaActual);
		        System.out.println("Puntos: " + 0);
		        String puntos="0";

		        // Inserción en la base de datos
		        String insertQuery = "INSERT INTO puntajes(Jugador, Puntaje, Fecha) VALUES(?, ?, ?)";
		        PreparedStatement pstmt = conn.prepareStatement(insertQuery);
		        pstmt.setString(1, miVariable);
		        pstmt.setString(2, puntos);
		        pstmt.setObject(3, fechaActual);

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
