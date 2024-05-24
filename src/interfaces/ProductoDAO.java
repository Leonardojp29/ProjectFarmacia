package interfaces;

import java.util.List;

import beans.ProductoDTO;

public interface ProductoDAO {

    // Poner métodos del CRUD
    public List<ProductoDTO> listarProducto();
    public ProductoDTO buscarProducto(int cod);
    public List<ProductoDTO> buscarProductoPorNombre(String nombre);
    public int registrarProducto(ProductoDTO obj);
    public int actualizarProducto(ProductoDTO obj);
    public int eliminarProducto(int cod);
    
}
