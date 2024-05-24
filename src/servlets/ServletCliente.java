package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ClienteDTO;
import service.ClienteService;
import java.util.regex.Pattern;

/**
 * Servlet implementation class ServletCliente
 */
@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteService c = new ClienteService();
       
    
    public ServletCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String xtipo = request.getParameter("tipo");
		if (xtipo.equals("listar")) {
			listar(request, response);
		}
		else if (xtipo.equals("buscar")) {
			buscar(request, response);
		}
		else if (xtipo.equals("registrar")) {
			registrar(request, response);
		}
		else if (xtipo.equals("actualizar")) {
			actualizar(request, response);
		}
		else if (xtipo.equals("eliminar")) {
			eliminar(request, response);
		}
		else if (xtipo.equals("buscarClientePorNombre")) {
			buscarClientePorNombre(request, response);
		}
	}

    private void buscarClientePorNombre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String nombre = request.getParameter("nombre");
	    request.setAttribute("listaClientes", c.buscaClientePorNombre(nombre));
	    request.getRequestDispatcher("listarCliente.jsp").forward(request, response);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codCli = Integer.parseInt(request.getParameter("codCli"));
		c.eliminaCliente(codCli);
		listar(request, response);
	}
	
	private boolean validarFormatoCorreo(String correo) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(regex, correo);
    }
	

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
	        String codStr = request.getParameter("txt_codCli");
	        if (codStr.isEmpty()) {
	            request.setAttribute("msj", "El c�digo es requerido");
	            request.getRequestDispatcher("actualizarCliente.jsp").forward(request, response);
	            return;
	        }
	        int codCli;
	        codCli = Integer.parseInt(request.getParameter("txt_codCli"));
	        String nom = request.getParameter("txt_nom_cli");
	        String direccion = request.getParameter("txt_direccion");
	        String telefono = request.getParameter("txt_telefono");
	        String correo = request.getParameter("txt_correo");
	        String dni = request.getParameter("txt_dni");
	
	        // Validar que los campos no est�n vac�os
	        if (nom.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || correo.isEmpty() || dni.isEmpty()) {
	            String mensajeError = "Todos los campos son obligatorios";
	            request.setAttribute("msj", mensajeError);
	            request.getRequestDispatcher("actualizarCliente.jsp").forward(request, response);
	            return;
	        }
	        
	        // Verificar si el DNI no tiene 8 d�gitos
	        if (dni.length() != 8) {
	            request.setAttribute("msg", "El DNI debe contener exactamente 8 dígitos");
	            request.getRequestDispatcher("actualizarCliente.jsp").forward(request, response);
	            return;
	        }
	
	        // Validar formato del correo electr�nico
	        if (!validarFormatoCorreo(correo)) {
	            String mensajeError = "El correo electr�nico no tiene un formato válido";
	            request.setAttribute("msj", mensajeError);
	            request.getRequestDispatcher("actualizarCliente.jsp").forward(request, response);
	            return;
	        }
	
	        ClienteDTO obj = new ClienteDTO();
	        obj.setCod_cli(codCli);
	        obj.setNom_cli(nom);
	        obj.setDireccion(direccion);
	        obj.setTelefono(telefono);
	        obj.setCorreo(correo);
	        obj.setDni(dni);
	        c.actualizaCliente(obj);
	        listar(request, response);
	    }

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String nom = request.getParameter("txt_nom_cli");
	    String direccion = request.getParameter("txt_direccion");
	    String telefono = request.getParameter("txt_telefono");
	    String correo = request.getParameter("txt_correo");
	    String dni = request.getParameter("txt_dni");
	
	    // Validar que los campos no est�n vac�os
	    if (nom.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || correo.isEmpty() || dni.isEmpty()) {
	        String mensajeError = "Todos los campos son obligatorios";
	        request.setAttribute("msj", mensajeError);
	        request.getRequestDispatcher("registrarCliente.jsp").forward(request, response);
	        return;
	    }
	    
	    // Verificar si el DNI no tiene 8 d�gitos
	    if (dni.length() != 8) {
	        request.setAttribute("msg", "El DNI debe contener exactamente 8 dígitos");
	        request.getRequestDispatcher("registrarCliente.jsp").forward(request, response);
	        return;
	    }
	    
	    // Validar formato del correo electr�nico
	    if (!validarFormatoCorreo(correo)) {
	        String mensajeError = "El correo electr�nico no tiene un formato válido";
	        request.setAttribute("msj", mensajeError);
	        request.getRequestDispatcher("registrarCliente.jsp").forward(request, response);
	        return;
	    }
	
	    ClienteDTO obj = new ClienteDTO();
	    obj.setNom_cli(nom);
	    obj.setDireccion(direccion);
	    obj.setTelefono(telefono);
	    obj.setCorreo(correo);
	    obj.setDni(dni);
	    c.registraCliente(obj);
	    listar(request, response);
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codCli = Integer.parseInt(request.getParameter("codCli"));
		request.setAttribute("Cliente", c.buscaCliente(codCli));
		request.getRequestDispatcher("actualizarCliente.jsp").forward(request, response);		
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listaClientes", c.listaCliente());
		request.getRequestDispatcher("listarCliente.jsp").forward(request, response);
	}

}
