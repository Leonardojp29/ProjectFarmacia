package service;

import java.util.List;


import beans.ClienteDTO;
import dao.DAOFactory;
import interfaces.ClienteDAO;
import utils.Constantes;

public class ClienteService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGEN_DE_DATOS_MYSQL);
	ClienteDAO objCli = fabrica.getCliente();
	
	public List<ClienteDTO> listaCliente() {
		return objCli.listarCliente();
	}
	
	public ClienteDTO buscaCliente(int codCli) {
		return objCli.buscarCliente(codCli);
	}

	public int registraCliente(ClienteDTO obj) {
		return objCli.registrarCliente(obj);
	}

	public int actualizaCliente(ClienteDTO obj) {
		return objCli.actualizarCliente(obj);
	}

	public int eliminaCliente(int codCli) {
		return objCli.eliminarCliente(codCli);
	}
	public List<ClienteDTO> buscaClientePorNombre(String nombre) {
        return objCli.buscarClientePorNombre(nombre);
    }
}
