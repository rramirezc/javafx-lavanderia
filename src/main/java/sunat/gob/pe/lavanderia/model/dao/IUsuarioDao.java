package sunat.gob.pe.lavanderia.model.dao;

import sunat.gob.pe.lavanderia.model.entities.Usuario;

public interface IUsuarioDao {
  boolean login (String user, String password);
  boolean cambiarClave (String email, String newPasswod);
  Usuario obtenerUsuarioById (String usuario);
  Usuario obtenerUsuarioByEmail (String email);
}
