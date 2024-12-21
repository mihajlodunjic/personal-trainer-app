/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package utils.cache;
import java.sql.ResultSet;
/**
 *
 * @author pc
 */
public interface ObjectFactory<T> {
    T create(ResultSet rs);
}
