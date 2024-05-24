package beans;

public class ProductoDTO {
		private int cod_pro; 
		private String nom_pro; 	
		private int stock; 
		double pre_producto; 			
		private String fech_ven;
		
		public int getCod_pro() {
			return cod_pro;
		}
		public void setCod_pro(int cod_pro) {
			this.cod_pro = cod_pro;
		}
		public String getNom_pro() {
			return nom_pro;
		}
		public void setNom_pro(String nom_pro) {
			this.nom_pro = nom_pro;
		}
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		public double getPre_producto() {
			return pre_producto;
		}
		public void setPre_producto(double pre_producto) {
			this.pre_producto = pre_producto;
		}
		public String getFech_ven() {
			return fech_ven;
		}
		public void setFech_ven(String fech_ven) {
			this.fech_ven = fech_ven;
		}
		
		
			
}
