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
import java.awt.event.ActionEvent;

public class Menu {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 391);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblJuegoVivora = new JLabel("JUEGO VIVORA");
		lblJuegoVivora.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblJuegoVivora.setBounds(146, 21, 155, 14);
		frame.getContentPane().add(lblJuegoVivora);
		
		JLabel lblControl = new JLabel("Control");
		lblControl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblControl.setBounds(304, 63, 79, 26);
		frame.getContentPane().add(lblControl);
		
		JButton btnW = new JButton("W");
		btnW.setBounds(304, 100, 50, 23);
		frame.getContentPane().add(btnW);
		btnW.setEnabled(false);
		
		JButton btnS = new JButton("S");
		btnS.setBounds(304, 136, 50, 23);
		frame.getContentPane().add(btnS);
		btnS.setEnabled(false);
		
		JButton btnA = new JButton("A");
		btnA.setBounds(244, 136, 50, 23);
		frame.getContentPane().add(btnA);
		btnA.setEnabled(false);
		
		JButton btnD = new JButton("D");
		btnD.setBounds(364, 136, 44, 23);
		frame.getContentPane().add(btnD);
		btnD.setEnabled(false);
		
		JLabel lblNamePlayer = new JLabel("NAME PLAYER");
		lblNamePlayer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNamePlayer.setBounds(26, 69, 128, 14);
		frame.getContentPane().add(lblNamePlayer);
		
		textField = new JTextField();
		textField.setBounds(10, 101, 128, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNivel = new JLabel("NIVEL");
		lblNivel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNivel.setBounds(26, 140, 59, 14);
		frame.getContentPane().add(lblNivel);
		
		JRadioButton rdbtnFacil = new JRadioButton("Facil");
		rdbtnFacil.setBounds(26, 181, 109, 23);
		frame.getContentPane().add(rdbtnFacil);
		
		JRadioButton rdbtnMedio = new JRadioButton("Medio");
		rdbtnMedio.setBounds(26, 220, 109, 23);
		frame.getContentPane().add(rdbtnMedio);
		
		JRadioButton rdbtnDificil = new JRadioButton("Dificil");
		rdbtnDificil.setBounds(26, 260, 109, 23);
		frame.getContentPane().add(rdbtnDificil);
		
		ButtonGroup group = new ButtonGroup();
        group.add(rdbtnFacil);
        group.add(rdbtnMedio);
        group.add(rdbtnDificil);
        
        
		
		JButton btnIniciar = new JButton("INICIAR");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//File filepath= new File(C:\\Users\\erika\\Downloads\\incio.wav);
				reproducirAudio("C:\\Users\\erika\\Downloads\\inicio.wav"); 
				int f=0,t=0,sr=0;
				String texto= textField.getText();
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
				if (!texto.isEmpty()) {
                    t=1;
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
					//new Vprincipal ();
				}
				else {
				JOptionPane.showMessageDialog(null, "LLENE TODOS LOS CAMPOS");
				}
			}
		});
		btnIniciar.setBounds(158, 318, 89, 23);
		frame.getContentPane().add(btnIniciar);
		
		JLabel lblWHaciaArriba = new JLabel("W hacia arriba");
		lblWHaciaArriba.setBounds(244, 185, 89, 14);
		frame.getContentPane().add(lblWHaciaArriba);
		
		JLabel lblSHaciaAbajo = new JLabel("S hacia abajo");
		lblSHaciaAbajo.setBounds(244, 211, 89, 14);
		frame.getContentPane().add(lblSHaciaAbajo);
		
		JLabel lblAHaciaLa = new JLabel("A hacia la izquierda");
		lblAHaciaLa.setBounds(244, 239, 110, 14);
		frame.getContentPane().add(lblAHaciaLa);
		
		JLabel lblDHaciaLa = new JLabel("D hacia la derecha");
		lblDHaciaLa.setBounds(244, 264, 110, 14);
		frame.getContentPane().add(lblDHaciaLa);
		frame.setVisible(true);
	}
}
