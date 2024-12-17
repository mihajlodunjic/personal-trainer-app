
import form.RegDialog;
import form.RegLogForm;
import javax.swing.JOptionPane;
import utils.DatabaseBroker;

public class MainProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            DatabaseBroker.connect();
            RegLogForm rlf=new RegLogForm();
            rlf.setLocationRelativeTo(null);
            rlf.setVisible(true);
//            RegDialog rd=new RegDialog(null, false);
//            rd.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Neupešno povezivanje sa bazom!","Greška",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
}
