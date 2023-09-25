package sunat.gob.pe.lavanderia.model.dao.impl;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sunat.gob.pe.lavanderia.model.dao.IUsuarioDao;
import sunat.gob.pe.lavanderia.model.entities.Usuario;
import sunat.gob.pe.lavanderia.model.util.ConnectionPoolMySQL;

public class UsuarioDAOImpl implements IUsuarioDao {

  @Override
  public boolean login(String user, String password) {

    Connection connection = null;
    PreparedStatement pst;
    ResultSet rs;
    boolean state = false;

    try {

      connection = ConnectionPoolMySQL.getInstance().getConnection();

      if (connection != null) {

        String sql = "SELECT * FROM USUARIO WHERE BINARY usuario=? AND password=AES_ENCRYPT(?, 'keyLavadanderia')";
        System.out.println("SQL: " + sql);

        pst = connection.prepareStatement(sql);
        pst.setString(1, user);
        pst.setString(2, password);
        System.out.println("SQL1: " + pst.toString());
        rs = pst.executeQuery();

        if (rs.next()) {
          state = true;
        }

      } else {
        JOptionPane.showMessageDialog(null, "Hubo un error al conectarse con la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
      }

    } catch (HeadlessException | SQLException ex) {
      JOptionPane.showMessageDialog(null, "Hubo un error de ejecución, posibles errores:\n"
              + ex.getMessage());
    } finally {

      try {
        if (connection != null) {
          ConnectionPoolMySQL.getInstance().closeConnection(connection);
        }
      } catch (SQLException ex) {
        System.err.println(ex.getMessage());
      }

    }

    return state;

  }

  @Override
  public boolean cambiarClave(String email, String newPasswod) {
    Connection connection = null;
    PreparedStatement pst = null;
    int rs;
    boolean state = false;

    try {

      connection = ConnectionPoolMySQL.getInstance().getConnection();

      if (connection != null) {

        String sql = "UPDATE USUARIO SET password = aes_encrypt(? ,'keyLavadanderia') WHERE email  = ? ";
        System.out.println("SQL: " + sql);

        pst = connection.prepareStatement(sql);
        pst.setString(1, newPasswod);
        pst.setString(2, email);
        System.out.println("SQL1: " + pst.toString());
        rs = pst.executeUpdate();
        System.out.println("Resultado: " + rs);
        if (rs > 0) {
          state = true;
        }
      } else {
        JOptionPane.showMessageDialog(null, "Hubo un error al conectarse con la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
      }

    } catch (HeadlessException | SQLException ex) {
      JOptionPane.showMessageDialog(null, "Hubo un error de ejecución, posibles errores:\n"
              + ex.getMessage());
    } finally {

      try {
        if (connection != null) {
          ConnectionPoolMySQL.getInstance().closeConnection(connection);
        }
        if (pst != null) {
          pst.close();
        }
      } catch (SQLException ex) {
        System.err.println(ex.getMessage());
      }

    }

    return state;
  }

  @Override
  public Usuario obtenerUsuarioById(String usuario) {
    Connection connection = null;
    PreparedStatement pst;
    ResultSet rs;
    Usuario usuarioBean = null;

    try {

      connection = ConnectionPoolMySQL.getInstance().getConnection();

      if (connection != null) {

        String sql = "SELECT * FROM USUARIO WHERE usuario=? ";
        System.out.println("SQL: " + sql);

        pst = connection.prepareStatement(sql);
        pst.setString(1, usuario);
        System.out.println("SQL1: " + pst.toString());
        rs = pst.executeQuery();

        if (rs.next()) {
          usuarioBean = new Usuario();
          usuarioBean.setIdUsuario(rs.getLong(1));
          usuarioBean.setNombres(rs.getString(2));
          usuarioBean.setApellidos(rs.getString(3));
          usuarioBean.setUsuario(rs.getString(4));
          usuarioBean.setPassword("");
          usuarioBean.setTelefono(rs.getString(6));
          usuarioBean.setEmail(rs.getString(7));
        }

      } else {
        JOptionPane.showMessageDialog(null, "Hubo un error al conectarse con la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
      }

    } catch (HeadlessException | SQLException ex) {
      JOptionPane.showMessageDialog(null, "Hubo un error de ejecución, posibles errores:\n"
              + ex.getMessage());
    } finally {

      try {
        if (connection != null) {
          ConnectionPoolMySQL.getInstance().closeConnection(connection);
        }
      } catch (SQLException ex) {
        System.err.println(ex.getMessage());
      }

    }

    return usuarioBean;
  }

  @Override
  public Usuario obtenerUsuarioByEmail(String email) {
    Connection connection = null;
    PreparedStatement pst;
    ResultSet rs;
    Usuario usuarioBean = null;

    try {

      connection = ConnectionPoolMySQL.getInstance().getConnection();

      if (connection != null) {

        String sql = "SELECT * FROM USUARIO WHERE email=? ";
        System.out.println("SQL: " + sql);

        pst = connection.prepareStatement(sql);
        pst.setString(1, email);
        System.out.println("SQL1: " + pst.toString());
        rs = pst.executeQuery();

        if (rs.next()) {
          usuarioBean = new Usuario();
          usuarioBean.setIdUsuario(rs.getLong(1));
          usuarioBean.setNombres(rs.getString(2));
          usuarioBean.setApellidos(rs.getString(3));
          usuarioBean.setUsuario(rs.getString(4));
          usuarioBean.setPassword("");
          usuarioBean.setTelefono(rs.getString(6));
          usuarioBean.setEmail(rs.getString(7));
        }

      } else {
        JOptionPane.showMessageDialog(null, "Hubo un error al conectarse con la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
      }

    } catch (HeadlessException | SQLException ex) {
      JOptionPane.showMessageDialog(null, "Hubo un error de ejecución, posibles errores:\n"
              + ex.getMessage());
    } finally {

      try {
        if (connection != null) {
          ConnectionPoolMySQL.getInstance().closeConnection(connection);
        }
      } catch (SQLException ex) {
        System.err.println(ex.getMessage());
      }

    }

    return usuarioBean;
  }

}
