
import form.RegDialog;
import form.RegLogForm;
import javax.swing.JOptionPane;
import database.DatabaseBroker;
import logic.ClientController;

public class MainProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ClientController.getInstance();
            RegLogForm rlf=new RegLogForm();
            rlf.setLocationRelativeTo(null);
            rlf.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Neupešno povezivanje sa serverom!","Greška",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
}
