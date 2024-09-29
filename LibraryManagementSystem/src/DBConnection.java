import java.sql.*;
public class DBConnection  {
    private static final String url = "jdbc:mysql://localhost:3306/LibraryDB";
    private static final String user = "root";
    private static final String password = "prantudatabase73@";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null)
        {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void beginTransaction(Connection conn) {
        if (conn !=null)
        {
            try {
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void commitTransaction(Connection conn) {
        if (conn != null)
        {
            try {
                conn.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void rollbackTransaction(Connection conn) {
        if (conn != null)
        {
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}



