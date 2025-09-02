
import form.LogForm;
import javax.swing.JOptionPane;
import logic.ClientController;

public class MainProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ClientController.getInstance();
            LogForm rlf=new LogForm();
            rlf.setLocationRelativeTo(null);
            rlf.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Neupešno povezivanje sa serverom!","Greška",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
}
