package sunat.gob.pe.lavanderia.model.dao;

import sunat.gob.pe.lavanderia.model.entities.Usuario;

public interface IUsuarioDao {
  Usuario login (String user, String password);
  boolean cambiarClave (String email, String newPasswod, String estado);
  Usuario obtenerUsuarioById (String usuario);
  Usuario obtenerUsuarioByEmail (String email);
  boolean cambiarClaveById (String usuario, String newPasswod, String estado);
}
