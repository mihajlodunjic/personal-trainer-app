/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package abstractClass;

import java.io.Serializable;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import utils.cache.ObjectFactory;
/**
 *
 * @author pc
 */
public abstract class DefaultDomainObject implements Serializable{
    protected String searchCondition;
     // Generički cache
    private static final Map<Class<? extends DefaultDomainObject>, Map<Integer, DefaultDomainObject>> cache = new HashMap<>();

    public abstract String returnAttrValues();
    public abstract String returnClassName();
    public abstract String setAttrValues();
    public abstract String returnInsertColumns();
    public abstract boolean setAttributes(ResultSet rs);
    public String returnSearchCondition(){
        return searchCondition;
    }
    
    public abstract int getNumberOfRelatedObjects();
    public abstract DefaultDomainObject getRelatedObject(int index);
    public abstract boolean populateRelatedObject(ResultSet rs, int rowIndex, int relatedObjectIndex);
    
    // Cache metode
    public static <T extends DefaultDomainObject> T getFromCache(Class<T> clazz, int id) {
        Map<Integer, DefaultDomainObject> classCache = cache.get(clazz);
        if (classCache != null) {
            return clazz.cast(classCache.get(id));
        }
        return null;
    }

    public static <T extends DefaultDomainObject> void addToCache(Class<T> clazz, int id, T object) {
        cache.computeIfAbsent(clazz, k -> new HashMap<>()).put(id, object);
    }

    public static <T extends DefaultDomainObject> T getOrCreateFromCache(Class<T> clazz, int id, ResultSet rs, ObjectFactory<T> factory) {
        T object = getFromCache(clazz, id);
        if (object == null) {
            object = factory.create(rs);
            addToCache(clazz, id, object);
        }
        return object;
    }
    
}
