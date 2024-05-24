package dao;

import interfaces.ProductoDAO;
import interfaces.PersonalDAO;
import interfaces.ClienteDAO;
import interfaces.VentaDAO;

public abstract class DAOFactory {

	// Posibles or�genes de datos
	public static final int MYSQL = 1;
	public static final int ORACLE = 2;
	public static final int DB2 = 3;
	public static final int SQLSERVER = 4;
	public static final int INFORMIX = 5;
	
	// Existir� un m�todo get por cada DAO en el Sistema
	public abstract ProductoDAO getProducto();
	public abstract PersonalDAO getPersonal();
	public abstract ClienteDAO getCliente();
	public abstract VentaDAO getVenta();
	
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch(whichFactory) {
			case MYSQL:
				return new MySQLDAOFactory();
			case ORACLE:
				// return new OracleDAOFactory();
			case SQLSERVER:
				// return new SQLServerDAOFactory();
		}
		return null;
	}
	
}
