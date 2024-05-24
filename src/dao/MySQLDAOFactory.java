package dao;

import interfaces.ProductoDAO;
import interfaces.VentaDAO;
import interfaces.ClienteDAO;
import interfaces.PersonalDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public ProductoDAO getProducto() {
		return new MySQLProductoDAO();
	}

	@Override
	public PersonalDAO getPersonal() {
		return new MySQLPersonalDAO();
	}

	@Override
	public ClienteDAO getCliente() {
		return new MySQLClienteDAO();
	}

	@Override
	public VentaDAO getVenta() {
		return new MySQLVentaDAO();
	}	

}
