package database;

import abstractClass.DefaultDomainObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

/**
 *
 * @author pc
 */
public class DatabaseBroker {

    private static Connection connection = null;
    private static Statement st = null;
    private static DatabaseBroker instance;
    
    private DatabaseBroker(){
        
    }
    public static DatabaseBroker getInstance(){
        if(instance==null)
            instance = new DatabaseBroker();
        return instance;
    }
    public void connect() {
    Properties properties = new Properties();
    try (InputStream input = new FileInputStream("src/config/db.properties")) {
        
        properties.load(input);

        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        String port = properties.getProperty("db.port");
        String host = properties.getProperty("db.host");
        String dbName = properties.getProperty("db.name");

        
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        connection = DriverManager.getConnection(url, user, password);

        System.out.println("conn succesful");
        connection.setAutoCommit(false);
    } catch (SQLException e) {
        System.out.println("conn unsuccesful");
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException("Problem sa učitavanjem property fajla", e);
    }
}

    public Connection getConnection() {
        return connection;
    }

    public boolean insertRow(DefaultDomainObject ddo) {
        String query;
        try {
            st = connection.createStatement();
            query = "INSERT INTO " + ddo.returnClassName() + " " + ddo.returnInsertColumns() + " VALUES(" + ddo.returnAttrValues() + ")";
            st.executeUpdate(query);
            st.close();
        } catch (SQLException esql) {
            esql.printStackTrace();
            return false;
        }
        return true;
    }

    public int insertRowReturnKey(DefaultDomainObject ddo) throws SQLException {
        String sql = "INSERT INTO " + ddo.returnClassName() + " "
                + ddo.returnInsertColumns() + " VALUES(" + ddo.returnAttrValues() + ")";
        try (Statement st = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new SQLException("Nije vraćen generisani ključ za: " + ddo.returnClassName());
            }
        }
    }

    public boolean deleteRow(DefaultDomainObject ddo) {
        String query = "DELETE FROM " + ddo.returnClassName()
                + " WHERE " + ddo.returnSearchCondition();
        try (Statement st = connection.createStatement()) {
            int affected = st.executeUpdate(query);
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRow(DefaultDomainObject ddo) {
        String query;
        try {
            st = connection.createStatement();
            query = "UPDATE " + ddo.returnClassName()
                    + " SET " + ddo.setAttrValues()
                    + "  WHERE " + ddo.returnSearchCondition();
            System.out.println(query);
            st.executeUpdate(query);
            st.close();
        } catch (SQLException esql) {
            throw new RuntimeException(esql);
        }

        return true;
    }

    public boolean doesExist(DefaultDomainObject ddo) {
        String query;
        ResultSet rows;
        try {
            st = connection.createStatement();
            query = "SELECT *"
                    + " FROM " + ddo.returnClassName()
                    + " WHERE " + ddo.returnSearchCondition();
            System.out.println(query);
            rows = st.executeQuery(query);
            boolean signal = rows.next();
            rows.close();
            st.close();
            return signal;
        } catch (SQLException esql) {
            throw new RuntimeException(esql);
        }
    }

    public boolean findRowAndReturn(DefaultDomainObject ddo) {
        ResultSet rs;
        String query;
        try {
            st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            query = "SELECT *"
                    + " FROM " + ddo.returnClassName()
                    + " WHERE " + ddo.returnSearchCondition();
            System.out.println(query);
            rs = st.executeQuery(query);
            boolean signal = rs.next();

            if (!signal) {
                System.out.println("signal je false");
                return false;
            }
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
            System.out.println("Pokusamvam da postavim atribute...");
            if (ddo.setAttributes(rs)) {
                System.out.println("onaj if setatributes u brokeru je true");
                rs.close();
                st.close();
                return true;
            } else {
                System.out.println("...neuspesno");
                rs.close();
                st.close();
                return false;
            }
        } catch (SQLException esql) {
            throw new RuntimeException(esql);
        }

    }

    public LinkedList<DefaultDomainObject> select(DefaultDomainObject ddo) throws Exception {
        String query = "SELECT " + ddo.columns() + " "
                + "FROM " + ddo.returnClassName() + " " + ddo.alias() + " "
                + ddo.join()
                + " WHERE " + ddo.returnSearchCondition()
                + (ddo.orderBy().isEmpty() ? "" : " ORDER BY " + ddo.orderBy());

        System.out.println(query);

        try (Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); ResultSet rs = st.executeQuery(query)) {
            return ddo.returnList(rs);
        }
    }

}
