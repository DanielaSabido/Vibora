package serpiente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class aplicacion {

	//basicamente esta ventana es la que hace que corra el programa es un main
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		//Inicio_V l = new Inicio_V();
		//new Vprincipal ();
		new Menu();
	}

}
