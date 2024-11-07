package utils;
import domain.Trainer;
import interfaces.DefaultDomainObject;
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
public class JDBCUtils {
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
            query="INSERT INTO "+ddo.returnClassName()+" VALUES("+ddo.returnAttrValues()+")";
            st.executeUpdate(query);
            st.close();
        }
        catch(SQLException esql){
            return false;
        }
        return true;
    }
    public static boolean deleteRow(DefaultDomainObject ddo){
        String query;
        try{
            st=connection.createStatement();
            query="DELETE * FROM "+ddo.returnClassName()+"WHERE "+ddo.returnSearchCondition();
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
                  "WHERE "+ddo.returnSearchCondition();
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
                  "FROM "+ddo.returnClassName()+
                  "WHERE "+ddo.returnSearchCondition();
            rows=st.executeQuery(query);
            boolean signal=rows.next();
            rows.close();
            st.close();
            return signal;
        }
        catch(SQLException esql){
            return false;
        }
    }
    public static boolean findRowAndReturn(DefaultDomainObject ddo){
        ResultSet rs;
        String nameOfObj;
        int numOfItems;
        String query;
        try{
            st=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            query="SELECT *"+
                  "FROM "+ddo.returnClassName()+
                  "WHERE "+ddo.returnSearchCondition();
            rs=st.executeQuery(query);
            boolean signal=rs.next();
            if(!signal){
                return false;
            }
            if(ddo.setAttributes(rs)){
                //to do
            }
        }
        catch(SQLException esql){
            return false;
        }
        
        
        
        
        return true;
    }
    public static boolean verifyLogin(String username, String password) {
        String query = "SELECT lozinka FROM trener WHERE korisnickoime = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("lozinka");
                statement.close();
                return storedPassword.equals(password);
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Trainer getTrainerByUsername(String username) {
        Trainer trener = null;
        String query = "SELECT * FROM trener WHERE korisnickoIme = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                trener=new Trainer();
                int id = resultSet.getInt(1);
                String korisnickoIme=resultSet.getString(2);
                String lozinka=resultSet.getString(3);
                String ime=resultSet.getString(4);
                String prezime=resultSet.getString(5);
                trener.setIdTrаiner(id);
                trener.setUserName(korisnickoIme);
                trener.setPassword(lozinka);
                trener.setName(ime);
                trener.setLastName(prezime);

            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trener;
    }
    
    public static boolean insertIntoTrainer(String korisnickoIme,String lozinka,String ime, String prezime){
        String query = "insert into trener (korisnickoIme, lozinka, ime, prezime) values (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            statement.setString(1, korisnickoIme);
            statement.setString(2, lozinka);
            statement.setString(3, ime);
            statement.setString(4, prezime);
            statement.executeUpdate();
            connection.commit();
            statement.close();

            return true;
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Korisnik sa "+korisnickoIme+" već postoji!","Greška",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static void updateTrainer(Trainer trainer){
        String query="update trener set korisnickoIme=?, lozinka=?, ime=?, prezime=? where idTrener=?";
        try{
            if(getTrainerByUsername(trainer.getUserName())!=null){
                throw new RuntimeException("Korisnik sa tim korisničkim imenom već postoji!");
            }
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,trainer.getUserName());
            statement.setString(2, trainer.getPassword());
            statement.setString(3, trainer.getName());
            statement.setString(4, trainer.getLastName());
            statement.setInt(5, trainer.getIdTrаiner());
            statement.executeUpdate();
            connection.commit();
            statement.close();
        }
        catch(Exception e){
            throw new RuntimeException("Nije moguće izmeniti podatke:\n"+e.getMessage());
        }
    }
    
}
