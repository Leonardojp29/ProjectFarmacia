package interfaces;
import java.util.List;

import beans.PersonalDTO;

public interface PersonalDAO {
	
    public List<PersonalDTO> listarPersonal();
    public PersonalDTO buscarPersonal(int codPer);
    public int registrarPersonal(PersonalDTO personal);
    public int actualizarPersonal(PersonalDTO personal);
    public int eliminarPersonal(int codPer);
	public PersonalDTO iniciarSesion(String login);
}

