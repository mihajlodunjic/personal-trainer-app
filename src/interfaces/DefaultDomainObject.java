/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.sql.*;

/**
 *
 * @author pc
 */
public interface DefaultDomainObject {
    public String returnAttrValues();
    public String returnClassName();
    public String returnSearchCondition();
    public String setAttrValues();
    public boolean setAttributes(ResultSet rs);
}
