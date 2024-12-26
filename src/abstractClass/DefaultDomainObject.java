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
    protected String searchConditionForRelatedObjects;

    public abstract String returnAttrValues();
    public abstract String returnClassName();
    public abstract String setAttrValues();
    public abstract String returnInsertColumns();
    public abstract boolean setAttributes(ResultSet rs);
    public void setSearchCondition(String searchCondition){
        this.searchCondition=searchCondition;
    }
    public void setSearchConditionForRelatedObjects(String searchConditionForRelatedObjects){
        this.searchConditionForRelatedObjects=searchConditionForRelatedObjects;
    }
    public String returnSearchCondition(){
        return searchCondition;
    }
    public String returnsearchConditionForRelatedObjects(){
        return searchConditionForRelatedObjects;
    }
    
    public abstract int getNumberOfRelatedObjects();
    public abstract DefaultDomainObject getRelatedObject(int index);
    public abstract boolean populateRelatedObject(ResultSet rs, int rowIndex, int relatedObjectIndex);
    
    
}
