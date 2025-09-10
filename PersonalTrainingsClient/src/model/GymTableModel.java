/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Gym;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pc
 */
public class GymTableModel extends AbstractTableModel{
    private LinkedList<Gym> list=new LinkedList<>();
    private String[] columns={"Naziv", "Adresa","Opremljenost"};
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Gym g = list.get(rowIndex);
        return switch (columnIndex) {
            case 0-> g.getName();
            case 1->g.getAddress();
            case 2->g.getEquipmentLevel().getSerbianName();
            default ->
                "";
        };
    }
    
    public void add(Gym g){
        int idx=list.size();
        list.add(g);
        fireTableRowsInserted(idx, idx);
    }
    
    public void clear() {
        int size = list.size();
        if (size > 0) {
            list.clear();
            fireTableRowsDeleted(0, size - 1);
        }
    }
    
    public LinkedList<Gym> getItems(){
        return list;
    }
    
    public void removeAt(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
}
