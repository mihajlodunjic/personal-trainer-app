package database;
import domain.Trainer;
import abstractClass.DefaultDomainObject;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.swing.JOptionPane;
/**
 *
 * @author pc
 */
public class DatabaseBroker {
    private static Connection connection = null;
    private static Statement st=null;
    public static void connect() {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal_trainings", properties);
            System.out.println("conn succesful");
        } catch (SQLException e) {
            System.out.println("conn unsuccesful");
            throw new RuntimeException(e);
        }
    }
    public static boolean insertRow(DefaultDomainObject ddo){
        String query;
        try{
            st=connection.createStatement();
            query="INSERT INTO "+ddo.returnClassName()+" "+ddo.returnInsertColumns()+" VALUES("+ddo.returnAttrValues()+")";
            st.executeUpdate(query);
            st.close();
        }
        catch(SQLException esql){
            esql.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean deleteRow(DefaultDomainObject ddo){
        String query;
        try{
            st=connection.createStatement();
            query="DELETE * FROM "+ddo.returnClassName()+" WHERE "+ddo.returnSearchCondition();
            st.executeUpdate(query);
            st.close();
        }
        catch(SQLException esql){
            return false;
        }
        return true;
    }
    
    public static boolean changeRow(DefaultDomainObject ddo){
        String query;
        try{
            st=connection.createStatement();
            query="UPDATE "+ddo.returnClassName()+
                  " SET "+ddo.setAttrValues()+
                  " WHERE "+ddo.returnSearchCondition();
            st.executeUpdate(query);
            st.close();
        }
        catch(SQLException esql){
            return false;
        }
        
        return true;
    }
    public static boolean doesExist(DefaultDomainObject ddo){
        String query;
        ResultSet rows;
        try{
            st=connection.createStatement();
            query="SELECT *"+
                  " FROM "+ddo.returnClassName()+
                  " WHERE "+ddo.returnSearchCondition();
            System.out.println(query);
            rows=st.executeQuery(query);
            boolean signal=rows.next();
            rows.close();
            st.close();
            return signal;
        }
        catch(SQLException esql){
            throw new RuntimeException(esql);
        }
    }
    
    public static boolean findAndReturn(DefaultDomainObject ddo) {
        ResultSet rs;
        Statement st;
        try {
            st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM " + ddo.returnClassName() + " WHERE " + ddo.returnSearchCondition();
            rs = st.executeQuery(query);
            System.out.println("Query - find: " + query);

            if (!rs.next()) {
                System.out.println("No record found in database.");
                return false;
            }

            if (!ddo.setAttributes(rs)) {
                System.out.println("Failed to populate main object.");
                return false;
            }

            for (int i = 0; i < ddo.getNumberOfRelatedObjects(); i++) {
                DefaultDomainObject relatedObject = ddo.getRelatedObject(i);
                if (relatedObject == null) {
                    System.out.println("Related object does not exist.");
                    return false;
                }

                String relatedQuery = "SELECT * FROM " + relatedObject.returnClassName()
                        + " WHERE " + ddo.returnsearchConditionForRelatedObjects();
                ResultSet relatedRs = st.executeQuery(relatedQuery);

                int count = 0;
                while (relatedRs.next()) {
                    if (!ddo.populateRelatedObject(relatedRs, count, i)) {
                        System.out.println("Failed to populate related object.");
                        return false;
                    }
                    count++;
                }
                relatedRs.close();
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Error reading record from database: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean findRowAndReturn(DefaultDomainObject ddo){
        ResultSet rs;
        String nameOfConObj;
        int numOfItems;
        String query;
        try{
            st=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            query="SELECT *"+
                  " FROM "+ddo.returnClassName()+
                  " WHERE "+ddo.returnSearchCondition();
            rs=st.executeQuery(query);
            boolean signal=rs.next();
            rs.close();
            st.close();
            if(!signal){
                return false;
            }
            if(ddo.setAttributes(rs)){
                return true;
            }
        }
        catch(SQLException esql){
            throw new RuntimeException(esql);
        }
        
        
        
        
        return true;
    }
   
   
   
   
   
   
   
   
}
