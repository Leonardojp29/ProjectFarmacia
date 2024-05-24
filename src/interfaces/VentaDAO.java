package interfaces;

import java.util.List;

import beans.DetalleVentaDTO;
import beans.VentaDTO;

public interface VentaDAO {

    // Poner métodos del CRUD
    public List<VentaDTO> listarVenta();
    public VentaDTO buscarVenta(int cod);
    public int registrarVenta(VentaDTO obj);
    public int actualizarVenta(VentaDTO obj);
    public int registrarDetalleVenta(DetalleVentaDTO obj);
    public int eliminarVenta(int cod);
	public List<DetalleVentaDTO> obtenerDetalleVenta(int codVenta);
	public int eliminarDetalleVenta(int codDet);
	
    
}