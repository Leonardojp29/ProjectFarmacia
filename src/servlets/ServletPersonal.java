package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.PersonalDTO;
import service.PersonalService;

@WebServlet("/ServletPersonal")
public class ServletPersonal extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PersonalService p = new PersonalService();

    public ServletPersonal() {
        super();
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String xtipo = request.getParameter("tipo");

        if (xtipo.equals("iniciarSesion"))
			iniciarSesion(request, response);
		 else if (xtipo.equals("cerrarSesion"))
			cerrarSesion(request, response);    	
         else if (xtipo.equals("listar")) 
            listar(request, response);
         else if (xtipo.equals("buscar")) 
            buscar(request, response);
         else if (xtipo.equals("registrar")) 
            registrar(request, response);
         else if (xtipo.equals("actualizar")) 
            actualizar(request, response);
         else if (xtipo.equals("eliminar")) 
            eliminar(request, response);
        
    }
    
    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(false);
		if (sesion != null) {
			sesion.invalidate();
		}		
		request.setAttribute("msg", "");
		request.getRequestDispatcher("inicioSesion.jsp").forward(request, response);
	}

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cod = Integer.parseInt(request.getParameter("cod"));
        String clavePersonal = request.getParameter("txt_clave_personal");

        PersonalDTO personal = p.buscaPersonal(cod);

        if (personal != null && personal.getClave().equals(clavePersonal)) {
            p.eliminaPersonal(cod);
            request.setAttribute("errorMsg", "Eliminación exitosa");
            RequestDispatcher rd = request.getRequestDispatcher("ServletPersonal?tipo=listar");
            rd.forward(request, response);
        } else {
            request.setAttribute("errorMsg", "La clave del personal es incorrecta");
            RequestDispatcher rd = request.getRequestDispatcher("ServletPersonal?tipo=listar");
            rd.forward(request, response);
        }
    }



    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codStr = request.getParameter("txt_cod");
        if (codStr.isEmpty()) {
            request.setAttribute("msg", "El código es requerido");
            request.getRequestDispatcher("actualizarPersonal.jsp").forward(request, response);
            return;
        }

        int cod = Integer.parseInt(codStr);
        String nom = request.getParameter("txt_nom_per");
        String ape = request.getParameter("txt_ape_per");
        String dni = request.getParameter("txt_dni");
        String login = request.getParameter("txt_login");
        String clave = request.getParameter("txt_clave");
        String claveActual = request.getParameter("txt_clave_actual");

        // Verificar si alg�n campo requerido est� vac�o
        if (nom.isEmpty() || ape.isEmpty() || dni.isEmpty() || login.isEmpty() || clave.isEmpty() || claveActual.isEmpty()) {
            request.setAttribute("msg", "Todos los campos son requeridos");
            request.getRequestDispatcher("actualizarPersonal.jsp").forward(request, response);
            return;
        }

        // Verificar si la clave actual ingresada coincide con la clave almacenada en la base de datos
        PersonalDTO personalActual = p.buscaPersonal(cod);
        if (!personalActual.getClave().equals(claveActual)) {
            request.setAttribute("msg", "La clave actual proporcionada es incorrecta");
            request.getRequestDispatcher("actualizarPersonal.jsp").forward(request, response);
            return;
        }
     // Verificar si el DNI no tiene 8 digitos
        if (dni.length() != 8) {
            request.setAttribute("msg", "El DNI debe contener exactamente 8 dígitos");
            request.getRequestDispatcher("actualizarPersonal.jsp").forward(request, response);
            return;
        }

        PersonalDTO obj = new PersonalDTO();
        obj.setCod_per(cod);
        obj.setNom_per(nom);
        obj.setApe_per(ape);
        obj.setDni(dni);
        obj.setLogin(login);
        obj.setClave(clave);

        p.actualizaPersonal(obj);
        listar(request, response);
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom, ape, dni, login, clave;
        nom = request.getParameter("txt_nom_per");
        ape = request.getParameter("txt_ape_per");
        dni = request.getParameter("txt_dni");
        login = request.getParameter("txt_login");
        clave = request.getParameter("txt_clave");

        // Verificar si falta alg�n dato requerido
        if (nom.isEmpty() || ape.isEmpty() || dni.isEmpty() || login.isEmpty() || clave.isEmpty()) {
            request.setAttribute("msj", "Todos los campos son requeridos");
            request.getRequestDispatcher("registrarPersonal.jsp").forward(request, response);
            return;
        }
        // Verificar si el DNI no tiene 8 digitos
        if (dni.length() != 8) {
            request.setAttribute("msj", "El DNI debe contener exactamente 8 dígitos");
            request.getRequestDispatcher("registrarPersonal.jsp").forward(request, response);
            return;
        }

        PersonalDTO obj = new PersonalDTO();
        obj.setNom_per(nom);
        obj.setApe_per(ape);
        obj.setDni(dni);
        obj.setLogin(login);
        obj.setClave(clave);

        p.registraPersonal(obj);
        response.sendRedirect("inicioSesion.jsp");
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cod = Integer.parseInt(request.getParameter("cod"));
        request.setAttribute("Personal", p.buscaPersonal(cod));
        request.getRequestDispatcher("actualizarPersonal.jsp").forward(request, response);
        
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("data", p.listaPersonal());
        request.getRequestDispatcher("listarPersonal.jsp").forward(request, response);
    }
    
    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String xlogin = request.getParameter("txt_login");
		String xpass = request.getParameter("txt_clave");
		PersonalDTO obj = p.iniciaSesion(xlogin);
		if (obj != null) {
			if (obj.getClave().equals(xpass)) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("datos", obj);
				request.getRequestDispatcher("menu.jsp").forward(request, response);
			}
			else {
				request.setAttribute("msg", "Contraseña incorrecta");
				request.getRequestDispatcher("inicioSesion.jsp").forward(request, response);
			}
		}
		else {
			request.setAttribute("msg", "Usuario no existente");
			request.getRequestDispatcher("inicioSesion.jsp").forward(request, response);
		}
	}
}