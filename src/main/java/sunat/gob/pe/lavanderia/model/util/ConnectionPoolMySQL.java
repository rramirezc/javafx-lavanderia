package sunat.gob.pe.lavanderia.model.util;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPoolMySQL {

  private final String DB = "bdlavanderia";
  private final String URL = "jdbc:mysql://localhost:3306/" + DB + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
  private final String USER = "root";
  private final String PASS = "_Mysql1982";

  private static ConnectionPoolMySQL dataSource;
  private BasicDataSource basicDataSource = null;

  private ConnectionPoolMySQL() {

    basicDataSource = new BasicDataSource();
    basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    basicDataSource.setUsername(USER);
    basicDataSource.setPassword(PASS);
    basicDataSource.setUrl(URL);

    basicDataSource.setMinIdle(5);
    basicDataSource.setMaxIdle(20);
    basicDataSource.setMaxTotal(50);
    basicDataSource.setMaxWaitMillis(-1);

  }

  public static ConnectionPoolMySQL getInstance() {
    if (dataSource == null) {
      dataSource = new ConnectionPoolMySQL();
      return dataSource;
    } else {
      return dataSource;
    }
  }

  public Connection getConnection() throws SQLException {
    return this.basicDataSource.getConnection();
  }

  public void closeConnection(Connection connection) throws SQLException {
    connection.close();
  }

}
