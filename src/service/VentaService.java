package service;

import java.util.List;

import beans.DetalleVentaDTO;
import beans.VentaDTO;
import dao.DAOFactory;
import interfaces.VentaDAO;
import utils.Constantes;

public class VentaService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGEN_DE_DATOS_MYSQL);
	VentaDAO objVen = fabrica.getVenta();
	
	public List<VentaDTO> listaVenta() {
		return objVen.listarVenta();
	}
	
	public VentaDTO buscaVenta(int cod) {
		return objVen.buscarVenta(cod);
	}

	public int eliminaVenta(int cod) {
		return objVen.eliminarVenta(cod);
	}
	
	public int eliminaDetalleVenta(int codDet) {
		return objVen.eliminarDetalleVenta(codDet);
	}

	public List<DetalleVentaDTO> obtenerDetalleVenta(int codVenta) {
	    return objVen.obtenerDetalleVenta(codVenta);
	}
	
	public int registraVenta(VentaDTO obj) {
		return objVen.registrarVenta(obj);
	}
	
	public int registraDetalleVenta(DetalleVentaDTO obj) {
		return objVen.registrarDetalleVenta(obj);
	}
	public int actualizaVenta(VentaDTO obj) {
		return objVen.actualizarVenta(obj);
	}
	
	public double calcularTotalPorVenta(int codVenta) {
	    List<DetalleVentaDTO> detalleVenta = obtenerDetalleVenta(codVenta);
	    double totalPorVenta = 0.0;

	    for (DetalleVentaDTO detalle : detalleVenta) {
	        totalPorVenta += detalle.getPre_producto() * detalle.getCantidad();
	    }

	    return totalPorVenta;
	}

	
}
