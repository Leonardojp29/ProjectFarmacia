package interfaces;

import java.util.List;
import beans.ClienteDTO;


public interface ClienteDAO {
	
    public List<ClienteDTO> listarCliente();
    public ClienteDTO buscarCliente(int codCli);
    public List<ClienteDTO> buscarClientePorNombre(String nombre);
    public int registrarCliente(ClienteDTO cliente);
    public int actualizarCliente(ClienteDTO cliente);
    public int eliminarCliente(int codCli);
	
}

