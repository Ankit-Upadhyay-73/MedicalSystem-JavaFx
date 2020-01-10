package database;
import java.sql.*;
public class DatabaseManager
{
    public static Connection returnConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            Statement st=con.createStatement();
            ResultSet rst=st.executeQuery("select schema_name from information_schema.schemata where schema_name='MedicalSystem'");
            boolean dbexists=false;
            while(rst.next())
            {
                if(rst.getString(1).equals("medicalsystem"))
                {
                    dbexists=true;
                }
            }

            if(dbexists==false)
                st.execute("create database MedicalSystem");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MedicalSystem", "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static Statement returnCreateTable(String sql) throws SQLException {
        Connection con=returnConnection();
        Statement st=con.createStatement();
        st.execute(sql);
        return st;
    }
    public static Statement returnUpdateTable(String sql) throws SQLException {
        Connection  con=returnConnection();
        Statement st=con.createStatement();
        st.executeUpdate(sql);
        return st;
    }
    public static Statement returnDeleteTable(String sql) throws SQLException {
        Connection  con=returnConnection();
        Statement st=con.createStatement();
        st.execute(sql);
        return st;
    }
    public static Statement returnInsertTable(String sql) throws SQLException {
        Connection  con=returnConnection();
        Statement st=con.createStatement();
        st.execute(sql);
        return st;
    }
    public  static void createTables(String TableName ,String sql)
    {
        Connection con=DatabaseManager.returnConnection();
        try {
            DatabaseMetaData dbm= (DatabaseMetaData) con.getMetaData();
            int count = 0;
            ResultSet rst  = dbm.getTables(null,null,TableName,null);
            if(rst.next()) {
            }
            else
            {
                Statement st=con.createStatement();
                st.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
