package service;

import java.util.List;

import beans.PersonalDTO;
import dao.DAOFactory;
import interfaces.PersonalDAO;
import utils.Constantes;

public class PersonalService {

	DAOFactory fabrica = DAOFactory.getDAOFactory(Constantes.ORIGEN_DE_DATOS_MYSQL);
	PersonalDAO objPer = fabrica.getPersonal();
	
	public List<PersonalDTO> listaPersonal() {
		return objPer.listarPersonal();
	}
	
	public PersonalDTO buscaPersonal(int codPer) {
		return objPer.buscarPersonal(codPer);
	}

	public int registraPersonal(PersonalDTO obj) {
		return objPer.registrarPersonal(obj);
	}

	public int actualizaPersonal(PersonalDTO obj) {
		return objPer.actualizarPersonal(obj);
	}

	public int eliminaPersonal(int codPer) {
		return objPer.eliminarPersonal(codPer);
	}
	public PersonalDTO iniciaSesion(String login) {
        return objPer.iniciarSesion(login);
    }
}
