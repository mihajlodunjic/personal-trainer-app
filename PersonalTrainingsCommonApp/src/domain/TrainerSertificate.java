package domain;

import abstractClass.DefaultDomainObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class TrainerSertificate extends DefaultDomainObject {

    private Trainer trainer;
    private Sertificate sertificate;

    public TrainerSertificate() {
        this.trainer = new Trainer();
        this.sertificate = new Sertificate();
    }

    public TrainerSertificate(Trainer trainer, Sertificate sertificate) {
        this.trainer = trainer;
        this.sertificate = sertificate;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Sertificate getSertificate() {
        return sertificate;
    }

    public void setSertificate(Sertificate sertificate) {
        this.sertificate = sertificate;
    }

    @Override
    public String returnClassName() {
        return "trainersertificate";
    }

    @Override
    public String alias() {
        return " ts ";
    }

    //join i trainer i sertificate da bismo napunili prikazne podatke
    @Override
    public String join() {
        return " JOIN trainer t ON t.idTrainer = ts.idTrainer "
                + " JOIN sertificate s ON s.idSertificate = ts.idSertificate ";
    }

    @Override
    public String returnInsertColumns() {
        return "(idTrainer, idSertificate)";
    }

    @Override
    public String returnAttrValues() {
        return trainer.getIdTrаiner() + "," + sertificate.getIdSertificate();
    }

    @Override
    public String setAttrValues() {
        return "idTrainer=" + trainer.getIdTrаiner() + ", idSertificate=" + sertificate.getIdSertificate();
    }

    @Override
    public boolean setAttributes(ResultSet rs) {
        try {
            if (trainer == null) {
                trainer = new Trainer();
            }
            if (sertificate == null) {
                sertificate = new Sertificate();
            }

            trainer.setIdTrаiner(rs.getInt("idTrainer"));
            sertificate.setIdSertificate(rs.getInt("idSertificate"));

            
            try {
                trainer.setUserName(rs.getString("userName"));
            } catch (SQLException ignore) {
            }
            try {
                trainer.setName(rs.getString("t_name"));
            } catch (SQLException ignore) {
            }
            try {
                trainer.setLastName(rs.getString("t_lastName"));
            } catch (SQLException ignore) {
            }

            try {
                sertificate.setName(rs.getString("s_name"));
            } catch (SQLException ignore) {
            }
            try {
                sertificate.setPublisher(rs.getString("publisher"));
            } catch (SQLException ignore) {
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public LinkedList<DefaultDomainObject> returnList(ResultSet rs) throws Exception {
        LinkedList<DefaultDomainObject> list = new LinkedList<>();
        while (rs.next()) {
            Trainer t = new Trainer();
            t.setIdTrаiner(rs.getInt("idTrainer"));
            try {
                t.setUserName(rs.getString("userName"));
            } catch (SQLException ignore) {
            }
            try {
                t.setName(rs.getString("t_name"));
            } catch (SQLException ignore) {
            }
            try {
                t.setLastName(rs.getString("t_lastName"));
            } catch (SQLException ignore) {
            }

            Sertificate s = new Sertificate();
            s.setIdSertificate(rs.getInt("idSertificate"));
            try {
                s.setName(rs.getString("s_name"));
            } catch (SQLException ignore) {
            }
            try {
                s.setPublisher(rs.getString("publisher"));
            } catch (SQLException ignore) {
            }

            list.add(new TrainerSertificate(t, s));
        }
        rs.close();
        return list;
    }
}
