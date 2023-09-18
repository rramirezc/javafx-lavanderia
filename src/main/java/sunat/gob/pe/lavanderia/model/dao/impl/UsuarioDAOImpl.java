package sunat.gob.pe.lavanderia.model.dao.impl;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import sunat.gob.pe.lavanderia.model.dao.IUsuarioDao;
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

}
