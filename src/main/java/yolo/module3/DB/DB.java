package yolo.module3.DB;

import java.sql.*;

public class DB {

    private static Connection connection;

    static {
        try {
            Class.forName(DBConfig.DRIVER_NAME);
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver class not found");
        }
    }

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD);
        return connection;
    }

    public static void closeConnection( Connection con ) throws SQLException
    {
        if ( con != null )
        {
            con.close();
        }
    }

    public static void closePrepaerdStatement(PreparedStatement stmt ) throws SQLException
    {
        if ( stmt != null )
        {
            stmt.close();
        }
    }

    public static void closeResultSet(ResultSet rs) throws SQLException
    {
        if ( rs != null )
        {
            rs.close();
        }
    }

    public static void closePSandCon(PreparedStatement ps, Connection con) {
        try {
            closePrepaerdStatement(ps);
            closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void closeAll(ResultSet rs, PreparedStatement ps, Connection con) {
        try {
            closeResultSet(rs);
            closePrepaerdStatement(ps);
            closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
