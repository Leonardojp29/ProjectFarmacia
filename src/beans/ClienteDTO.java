package beans;

public class ClienteDTO {
	
	private int cod_cli;
	private String nom_cli, direccion, telefono, correo, dni;
	
		public int getCod_cli() {
			return cod_cli;
		}
		public void setCod_cli(int cod_cli) {
			this.cod_cli = cod_cli;
		}
		
		public String getNom_cli() {
			return nom_cli;
		}
		public void setNom_cli(String nom_cli) {
			this.nom_cli = nom_cli;
		}
		
		public String getDireccion() {
			return direccion;
		}
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
		
		public String getTelefono() {
			return telefono;
		}
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		
		public String getCorreo() {
			return correo;
		}
		public void setCorreo(String correo) {
			this.correo = correo;
		}
		public String getDni() {
			return dni;
		}
		public void setDni(String dni) {
			this.dni = dni;
		}
	
	

}
