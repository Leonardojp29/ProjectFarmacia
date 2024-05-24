package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ProductoDTO;
import service.ProductoService;

@WebServlet("/ServletProducto")
public class ServletProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductoService p = new ProductoService();

    /**
     * Default constructor. 
     */
    public ServletProducto() {
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
		else if (xtipo.equals("buscarProductoPorNombre")) {
			buscarProductoPorNombre(request, response);
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("cod"));
		p.eliminaProducto(cod);
		listar(request, response);
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String codStr = request.getParameter("txt_cod");
        if (codStr.isEmpty()) {
            request.setAttribute("msj", "El codigo es requerido");
            request.getRequestDispatcher("actualizarProducto.jsp").forward(request, response);
            return;
        }
	    int cod, stock;
	    double pre;
	    cod = Integer.parseInt(request.getParameter("txt_cod"));
	    String nom = request.getParameter("txt_nom_pro");
	    String stockStr = request.getParameter("txt_stock");
	    String preStr = request.getParameter("txt_pre_pro");
	    String fech = request.getParameter("txt_fech");
	    
	 // Validar el formato de fecha (YYYY-MM-DD)
	    if (!fech.matches("\\d{4}-\\d{2}-\\d{2}")) {
	        String mensajeError = "El formato de fecha es incorrecto. Debe ser AAAA-MM-DD.";
	        request.setAttribute("msj", mensajeError);
	        request.getRequestDispatcher("actualizarProducto.jsp").forward(request, response);
	        return;
	    }

	    // Validar que los campos num�ricos no est�n vac�os
	    if (nom.isEmpty() || stockStr.isEmpty() || preStr.isEmpty() || fech.isEmpty()) {
	        String mensajeError = "Todos los campos son obligatorios";
	        request.setAttribute("msj", mensajeError);
	        request.getRequestDispatcher("actualizarProducto.jsp").forward(request, response);
	        return;
	    }

	    // Validar que los campos num�ricos contengan valores v�lidos
	    try {
	        stock = Integer.parseInt(stockStr);
	        pre = Double.parseDouble(preStr);
	    } catch (NumberFormatException e) {
	        String mensajeError = "El stock y el precio deben ser valores numericos";
	        request.setAttribute("msj", mensajeError);
	        request.getRequestDispatcher("actualizarProducto.jsp").forward(request, response);
	        return;
	    }

	    ProductoDTO obj = new ProductoDTO();
	    obj.setCod_pro(cod);
	    obj.setNom_pro(nom);
	    obj.setStock(stock);
	    obj.setPre_producto(pre);
	    obj.setFech_ven(fech);
	    p.actualizaProducto(obj);
	    listar(request, response);
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int stock;
	    double pre;
	    String nom = request.getParameter("txt_nom_pro");
	    String stockStr = request.getParameter("txt_stock");
	    String preStr = request.getParameter("txt_pre_pro");
	    String fech = request.getParameter("txt_fech");
	    
	 // Validar el formato de fecha (YYYY-MM-DD)
	    if (!fech.matches("\\d{4}-\\d{2}-\\d{2}")) {
	        String mensajeError = "El formato de fecha es incorrecto. Debe ser AAAA-MM-DD.";
	        request.setAttribute("msj", mensajeError);
	        request.getRequestDispatcher("registrarProducto.jsp").forward(request, response);
	        return;
	    }


	    // Validar que los campos no est�n vac�os
	    if (nom.isEmpty() || stockStr.isEmpty() || preStr.isEmpty() || fech.isEmpty()) {
	        String mensajeError = "Todos los campos son obligatorios";
	        request.setAttribute("msj", mensajeError);
	        request.getRequestDispatcher("registrarProducto.jsp").forward(request, response);
	        return;
	    }

	    // Validar que los campos num�ricos contengan valores v�lidos
	    try {
	        stock = Integer.parseInt(stockStr);
	        pre = Double.parseDouble(preStr);
	    } catch (NumberFormatException e) {
	        String mensajeError = "El stock y el precio deben ser valores numericos";
	        request.setAttribute("msj", mensajeError);
	        request.getRequestDispatcher("registrarProducto.jsp").forward(request, response);
	        return;
	    }

	    ProductoDTO obj = new ProductoDTO();
	    obj.setNom_pro(nom);
	    obj.setStock(stock);
	    obj.setPre_producto(pre);
	    obj.setFech_ven(fech);
	    p.registraProducto(obj);
	    listar(request, response);
	}

	private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("cod"));
		request.setAttribute("Producto", p.buscaProducto(cod));
		request.getRequestDispatcher("actualizarProducto.jsp").forward(request, response);		
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("data", p.listaProducto());
		request.getRequestDispatcher("listarProducto.jsp").forward(request, response);
	}
	private void buscarProductoPorNombre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String nombre = request.getParameter("nombre");
	    request.setAttribute("data", p.buscaProductoPorNombre(nombre));
	    request.getRequestDispatcher("listarProducto.jsp").forward(request, response);
	}



	

}