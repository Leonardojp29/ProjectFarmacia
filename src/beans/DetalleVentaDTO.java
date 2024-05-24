package beans;

public class DetalleVentaDTO {
    private int codDetalleVenta;
    private int codVenta;
    private int cod_pro;
    private int cantidad;
    double pre_producto; 
    private String nom_pro;
    
	public int getCodDetalleVenta() {
		return codDetalleVenta;
	}
	public void setCodDetalleVenta(int codDetalleVenta) {
		this.codDetalleVenta = codDetalleVenta;
	}
	public int getCodVenta() {
		return codVenta;
	}
	public void setCodVenta(int codVenta) {
		this.codVenta = codVenta;
	}
	public int getCod_pro() {
		return cod_pro;
	}
	public void setCod_pro(int cod_pro) {
		this.cod_pro = cod_pro;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPre_producto() {
		return pre_producto;
	}
	public void setPre_producto(double pre_producto) {
		this.pre_producto = pre_producto;
	}
	public String getNom_pro() {
		return nom_pro;
	}
	public void setNom_pro(String nom_pro) {
		this.nom_pro = nom_pro;
	}
    

}

