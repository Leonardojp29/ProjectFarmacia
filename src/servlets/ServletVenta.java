package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.VentaDTO;
import service.VentaService;
import beans.DetalleVentaDTO;
import service.ClienteService;
import service.ProductoService;

@WebServlet("/ServletVenta")
public class ServletVenta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    VentaService v = new VentaService();
    ClienteService c = new ClienteService();
    ProductoService p = new ProductoService();

    public ServletVenta() {
    	
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        String xtipo = request.getParameter("tipo");
        if (xtipo.equals("listar")) {
            listar(request, response);
        } else if (xtipo.equals("listarDatosCli")) {
            listarCliente(request, response);
        } else if (xtipo.equals("listarDatosPro")) {
            listarProducto(request, response);
        } else if (xtipo.equals("buscar")) {
            buscar(request, response);
        } else if (xtipo.equals("buscarVen")) {
            buscarVen(request, response);
        } else if (xtipo.equals("eliminar")) {
            eliminar(request, response);
        } else if (xtipo.equals("eliminarDet")) {
            eliminarDet(request, response);
        } else if (xtipo.equals("detalle")) {
            verDetalle(request, response);
        } else if (xtipo.equals("registrarVenta")) {
            registrarVenta(request, response);
        } else if (xtipo.equals("registrarDetalleVenta")) {
            registrarDetalleVenta(request, response);
        } else if (xtipo.equals("actualizarVen")) {
			actualizarVen(request, response);
		}        
    }
    
    private void eliminarDet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codDet = Integer.parseInt(request.getParameter("codDet"));
        v.eliminaDetalleVenta(codDet);
        
        // Obtener el código de venta para volver a la página de detalle con la información actualizada
        String codVentaParam = request.getParameter("codVenta");
        if (codVentaParam != null && !codVentaParam.isEmpty()) {
            int codVenta = Integer.parseInt(codVentaParam);
            List<DetalleVentaDTO> detalleVenta = v.obtenerDetalleVenta(codVenta);
            request.setAttribute("detalleVenta", detalleVenta);
            request.getRequestDispatcher("detalleVenta.jsp").forward(request, response);
        } else {
            // Si no hay código de venta, redirigir a la página de error
            response.sendRedirect("error.jsp");
        }
    }


	private void actualizarVen(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codStr = request.getParameter("txt_cod");
        if (codStr.isEmpty()) {
            request.setAttribute("msj", "El código es requerido");
            request.getRequestDispatcher("actualizarVenta.jsp").forward(request, response);
            return;
        }

        int codVen;
        try {
            codVen = Integer.parseInt(codStr);
        } catch (NumberFormatException e) {
            request.setAttribute("msj", "El código de venta no es válido");
            request.getRequestDispatcher("actualizarVenta.jsp").forward(request, response);
            return;
        }

        String codCliStr = request.getParameter("txt_codCli");
        String fech = request.getParameter("txt_fech");

        // Validar el formato de fecha (YYYY-MM-DD)
        if (!fech.matches("\\d{4}-\\d{2}-\\d{2}")) {
            String mensajeError = "El formato de fecha es incorrecto. Debe ser AAAA-MM-DD.";
            request.setAttribute("msj", mensajeError);
            request.getRequestDispatcher("ServletVenta?tipo=listarDatosCli").forward(request, response);
            return;
        }

        int codCli;
        try {
            codCli = Integer.parseInt(codCliStr);
        } catch (NumberFormatException e) {
            String mensajeError = "Escoge un Cliente por favor";
            request.setAttribute("msj", mensajeError);
            request.getRequestDispatcher("ServletVenta?tipo=listarDatosCli").forward(request, response);
            return;
        }

        VentaDTO obj = new VentaDTO();
        obj.setCodVenta(codVen);
        obj.setCod_cli(codCli);
        obj.setFechaVenta(fech);
        v.actualizaVenta(obj);
        listar(request, response);
    }


    private void registrarDetalleVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int codVen, codPro, cant;
    	double pre;
    	String codVenStr = request.getParameter("txt_codVen");
    	String codProStr = request.getParameter("txt_codPro");
    	String cantStr = request.getParameter("txt_cant");
    	String codPreStr = request.getParameter("txt_pre");
    	
    	if (codVenStr.isEmpty()) {
            request.setAttribute("msj", "El codigo de la venta es requerido");
            request.getRequestDispatcher("registrarDetalleVenta.jsp").forward(request, response);
            return;
        }
    	
    	// Validar que los campos no esten vacios
	    if (codVenStr == null || codProStr == null || cantStr == null || codPreStr == null ||
	    	    codVenStr.isEmpty() || codProStr.isEmpty() || cantStr.isEmpty() || codPreStr.isEmpty()) {
	        String mensajeError = "Todos los campos son obligatorios";
	        request.setAttribute("msj", mensajeError);
	        request.getRequestDispatcher("ServletVenta?tipo=listarDatosPro").forward(request, response);
	        return;
	    }
	    // Validar que los campos numericos contengan valores validos
	    try {
	    	codVen = Integer.parseInt(codVenStr);
	    	codPro = Integer.parseInt(codProStr);
	    	cant = Integer.parseInt(cantStr);
	    	pre = Double.parseDouble(codPreStr);
	    } catch (NumberFormatException e) {
	        String mensajeError = "Porfavor inserte valores correctos";
	        request.setAttribute("msj", mensajeError);
	        request.getRequestDispatcher("ServletVenta?tipo=listarDatosPro").forward(request, response);
	        return;
	    }
	    
    	DetalleVentaDTO obj = new DetalleVentaDTO();
	    obj.setCodVenta(codVen);
	    obj.setCod_pro(codPro);
	    obj.setCantidad(cant);
	    obj.setPre_producto(pre);
	    v.registraDetalleVenta(obj);
	    listar(request, response); 	
    	
	}

	private void registrarVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int codCli;
	    String fech = request.getParameter("txt_fech");
	    String codCliStr = request.getParameter("txt_codCli");

	    // Validar el formato de fecha (YYYY-MM-DD)
	    if (!fech.matches("\\d{4}-\\d{2}-\\d{2}")) {
	        String mensajeError = "El formato de fecha es incorrecto. Debe ser AAAA-MM-DD.";
	        request.setAttribute("msj", mensajeError);
	        request.getRequestDispatcher("ServletVenta?tipo=listarDatosCli").forward(request, response);
	        return;
	    }
	    try {
	        codCli = Integer.parseInt(codCliStr);
	    } catch (NumberFormatException e) {
	        String mensajeError = "Escoge un Cliente por favor";
	        request.setAttribute("msj", mensajeError);
	        request.getRequestDispatcher("ServletVenta?tipo=listarDatosCli").forward(request, response);
	        return;
	    }
	    VentaDTO obj = new VentaDTO();
	    obj.setCod_cli(codCli);
	    obj.setFechaVenta(fech);
	    v.registraVenta(obj);
	    listar(request, response);
	}

	private void verDetalle(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String codVentaParam = request.getParameter("codVenta");

        if (codVentaParam != null && !codVentaParam.isEmpty()) {
            int codVenta = Integer.parseInt(codVentaParam);
            List<DetalleVentaDTO> detalleVenta = v.obtenerDetalleVenta(codVenta);
            request.setAttribute("detalleVenta", detalleVenta);
            request.getRequestDispatcher("detalleVenta.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private void listarCliente(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setAttribute("listaDatosCli", c.listaCliente());
        request.getRequestDispatcher("registrarVenta.jsp").forward(request, response);
    }
    private void listarProducto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        request.setAttribute("listaDatosPro", p.listaProducto());
        request.getRequestDispatcher("registrarDetalleVenta.jsp").forward(request, response);
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<VentaDTO> listaVentas = v.listaVenta();
        
        for (VentaDTO venta : listaVentas) {
            int codVenta = venta.getCodVenta();
            double totalPorVenta = v.calcularTotalPorVenta(codVenta);
            venta.setTotalVenta(totalPorVenta);
        }

        request.setAttribute("data", listaVentas);
        request.getRequestDispatcher("listarVenta.jsp").forward(request, response);
    }


    private void buscar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        int cod = Integer.parseInt(request.getParameter("cod"));
        request.setAttribute("Venta", v.buscaVenta(cod));
        request.getRequestDispatcher("ServletVenta?tipo=listarDatosPro").forward(request, response);
    }
    
    private void buscarVen(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        int cod = Integer.parseInt(request.getParameter("cod"));
        request.setAttribute("Venta", v.buscaVenta(cod));
        request.setAttribute("listaDatosCli", c.listaCliente());
        request.getRequestDispatcher("actualizarVenta.jsp").forward(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cod = Integer.parseInt(request.getParameter("cod"));
        v.eliminaVenta(cod);
        listar(request, response);
    }
}