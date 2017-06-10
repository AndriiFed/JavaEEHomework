import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCHomework {

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    Class.forName("org.h2.Driver");

    String username = "sa";
    String password = "sa";
    String url = "jdbc:h2:file:~/andriifed";

    try (Connection connection = DriverManager.getConnection(url, username, password)) {
      createInitialRecords(connection);
      System.out.println("INITIAL RECORDS:");
      selectFromTables(connection);

      createNewRecord(connection, "Jane", "password2", UserRole.ROLE_USER);
      createNewRecord(connection, "Jack", "password3", UserRole.ROLE_ADMIN);
      System.out.println();
      System.out.println("ALL RECORDS:");
      selectFromTables(connection);

      updateRecord(connection, "Jack", "pass_jack", UserRole.ROLE_USER);
      updateRecord(connection, "Jane", "pass_jane", UserRole.ROLE_ADMIN);
      System.out.println();
      System.out.println("ALTERED RECORDS:");
      selectFromTables(connection);
    }
  }

  private static void createInitialRecords(Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      connection.setAutoCommit(false);
      statement.addBatch("DELETE FROM public.user_roles;");
      statement.addBatch("DELETE FROM public.users;");
      statement.addBatch("DELETE FROM public.roles;");
      statement.addBatch("ALTER TABLE users ALTER COLUMN id RESTART WITH 1");
      statement.addBatch("ALTER TABLE roles ALTER COLUMN id RESTART WITH 1");
      statement.addBatch("INSERT INTO users(USERNAME, PASSWORD) VALUES('John', 'password');");
      statement.addBatch("INSERT INTO roles(NAME) VALUES('ROLE_USER');");
      statement.addBatch("INSERT INTO roles(NAME, PERMISSIONS) VALUES('ROLE_ADMIN', '0777');");
      statement.addBatch("INSERT INTO user_roles VALUES(1, 2);");
      statement.executeBatch();
      connection.commit();
    }
  }

  private static void createNewRecord(Connection connection, String userName, String userPassword, UserRole userRole) throws SQLException {
    try (PreparedStatement preparedStatement = connection.
            prepareStatement("INSERT INTO public.users(USERNAME, PASSWORD) VALUES(?, ?)")) {
      preparedStatement.setString(1, userName);
      preparedStatement.setString(2, userPassword);
      preparedStatement.executeUpdate();
    }
    try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_roles VALUES(?, ?)")) {
      preparedStatement.setInt(1, getUserId(connection, userName));
      preparedStatement.setInt(2, userRole.getValue());
      preparedStatement.executeUpdate();
    }
  }

  private static void selectFromTables(Connection connection) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(
              "SELECT users.id, users.username, users.password, roles.name AS ROLE\n" +
                      "FROM PUBLIC.users\n" +
                      "  INNER JOIN user_roles ON user_roles.user_id=users.id\n" +
                      "  INNER JOIN roles ON user_roles.role_id=roles.id;");

      System.out.println("ID\tUSER\tPASSWORD\tROLE");
      while (resultSet.next()) {
        System.out.printf("%s\t%s\t%s\t%s\r\n", resultSet.getString(1), resultSet.getString(2),
                resultSet.getString(3), resultSet.getString(4));
      }
    }
  }

  private static void updateRecord(Connection connection, String userName, String userPassword, UserRole userRole) throws SQLException {
    try (PreparedStatement preparedStatement = connection.
            prepareStatement("UPDATE users \n" +
                    "SET password = ?" +
                    "WHERE username = ?;")) {
      preparedStatement.setString(1, userPassword);
      preparedStatement.setString(2, userName);
      preparedStatement.executeUpdate();
    }

    try (PreparedStatement preparedStatement = connection.
            prepareStatement("UPDATE user_roles \n" +
                    "SET role_id = ?" +
                    "WHERE user_id = ?;")) {
      preparedStatement.setInt(1, userRole.getValue());
      preparedStatement.setInt(2, getUserId(connection, userName));
      preparedStatement.executeUpdate();
    }
  }

  private static int getUserId(Connection connection, String userName) throws SQLException {
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(
              "SELECT users.id FROM PUBLIC.users WHERE USERNAME='" + userName + "';");

      if (resultSet.first()) {
        return resultSet.getInt(1);
      }
      return -1;
    }
  }
}
