
import form.RegLogForm;
import javax.swing.JOptionPane;
import utils.JDBCUtils;

public class MainProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            JDBCUtils.connect();
            RegLogForm rlf=new RegLogForm();
            rlf.setLocationRelativeTo(null);
            rlf.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Neupešno povezivanje sa bazom!","Greška",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
}
