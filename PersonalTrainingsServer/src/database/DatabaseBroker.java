package database;
import domain.Trainer;
import abstractClass.DefaultDomainObject;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("conn unsuccesful");
            throw new RuntimeException(e);
        }
    }
    public  static Connection getConnection(){
        return connection;
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
    
    public static boolean updateRow(DefaultDomainObject ddo){
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
            System.out.println(query);
            rs=st.executeQuery(query);
            boolean signal=rs.next();
            
            
            if(!signal){
                System.out.println("signal je false");
                return false;
            }
            System.out.println(rs.getInt(1)+" "+rs.getString(2));
            System.out.println("Pokusamvam da postavim atribute...");
            if(ddo.setAttributes(rs)){
                System.out.println("onaj if setatributes u brokeru je true");
                rs.close();
                st.close();
                return true;
            }
            else{
                System.out.println("...neuspesno");
                rs.close();
                st.close();
                return false;
            }
        }
        catch(SQLException esql){
            throw new RuntimeException(esql);
        }
        
    }
   
   public static LinkedList<DefaultDomainObject> select(DefaultDomainObject ddo) throws Exception{
       String query="SELECT * "
                  + "FROM "+ddo.returnClassName()+" "+ddo.alias()+" "
                  + ddo.join()
                  + " WHERE "+ddo.returnSearchCondition();
       System.out.println(query);
       st=connection.createStatement();
       ResultSet rs=st.executeQuery(query);
       return ddo.returnList(rs);
        
   }
   
//    public static void main(String[] args) {
//        try {
//            connect();
//            System.out.println(select(new Trainer()));
//        } catch (Exception ex) {
//            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
