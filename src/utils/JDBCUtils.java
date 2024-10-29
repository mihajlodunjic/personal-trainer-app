/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import domain.Trener;
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
    
    public static boolean verifyLogin(String username, String password) {
        String query = "SELECT lozinka FROM trener WHERE korisnickoime = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPassword = resultSet.getString("lozinka");
                return storedPassword.equals(password);
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Trener getTrenerByUsername(String username) {
        Trener trener = null;
        String query = "SELECT * FROM trener WHERE korisnickoIme = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                trener=new Trener();
                int id = resultSet.getInt(1);
                String korisnickoIme=resultSet.getString(2);
                String lozinka=resultSet.getString(3);
                String ime=resultSet.getString(4);
                String prezime=resultSet.getString(5);
                trener.setIdTrener(id);
                trener.setKorisnickoIme(korisnickoIme);
                trener.setLozinka(lozinka);
                trener.setIme(ime);
                trener.setPrezime(prezime);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trener;
    }
    
    public static boolean insertIntoTrener(String korisnickoIme,String lozinka,String ime, String prezime){
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
            return true;
        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Korisnik sa "+korisnickoIme+" već postoji!","Greška",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
