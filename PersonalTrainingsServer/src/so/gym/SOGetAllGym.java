/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.gym;

import abstractClass.DefaultDomainObject;
import database.DatabaseBroker;
import domain.Gym;
import java.util.LinkedList;
import so.abstractso.AbstractSO;

/**
 *
 * @author pc
 */
public class SOGetAllGym extends AbstractSO {

    private LinkedList<Gym> list;

    public LinkedList<Gym> getList() {
        return list;
    }

    @Override
    protected void validate(DefaultDomainObject ddo) throws Exception {
        if (!(ddo instanceof Gym)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Gym.");
        }

    }

    @Override
    protected void execute(DefaultDomainObject ddo) throws Exception {
        Gym c = (Gym) ddo;        
        Gym probe = new Gym();    

        StringBuilder cond = new StringBuilder("1");

        if (c.getIdGym() > 0) {
            cond = new StringBuilder("g.idGym=" + c.getIdGym());
        } else {

            if (c.getName() != null && !c.getName().trim().isEmpty()) {
                cond.append(" AND LOWER(g.g_name) LIKE LOWER('%")
                        .append(esc(c.getName().trim())).append("%')");
            }

            if (c.getAddress() != null && !c.getAddress().trim().isEmpty()) {
                cond.append(" AND LOWER(g.address) LIKE LOWER('%")
                        .append(esc(c.getAddress().trim())).append("%')");
            }
    
            if (c.getEquipmentLevel() != null) {
                cond.append(" AND g.equipmentLevel='")
                        .append(c.getEquipmentLevel().getSerbianName()).append("'");
            }
            //ignorise razmake
            if (c.getMobilePhone() != null && !c.getMobilePhone().trim().isEmpty()) {
                String phone = esc(c.getMobilePhone().trim());
                cond.append(" AND REPLACE(g.g_mobilePhone,' ','') LIKE REPLACE('%")
                        .append(phone).append("%',' ','')");
            }
            if (c.getEmail() != null && !c.getEmail().trim().isEmpty()) {
                cond.append(" AND LOWER(g.email) LIKE LOWER('%")
                        .append(esc(c.getEmail().trim())).append("%')");
            }
        }

        probe.setSearchCondition(cond.toString());

        list = (LinkedList<Gym>) (LinkedList<?>) DatabaseBroker.getInstance().select(probe);
        
    }

    private static String esc(String s) {
        return s.replace("'", "''");
    }
}
