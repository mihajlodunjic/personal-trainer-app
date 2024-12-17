/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package abstractClass;

import java.io.Serializable;
import java.sql.*;

/**
 *
 * @author pc
 */
public abstract class DefaultDomainObject implements Serializable{
    protected String searchCondition;
    public abstract String returnAttrValues();
    public abstract String returnClassName();
    public abstract String returnSearchCondition();
    public abstract String setAttrValues();
    public abstract String returnInsertColumns();
    public abstract boolean setAttributes(ResultSet rs);
    
    
}
