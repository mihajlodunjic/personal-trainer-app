/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Client;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pc
 */
public class ClientTableModel extends AbstractTableModel {

    private LinkedList<Client> list = new LinkedList<>();
    private String[] columns = {"Ime", "Prezime", "Pol", "Teretana"};

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
        Client c = list.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                c.getName();
            case 1 ->
                c.getLastName();
            case 2 ->
                c.getGender().getSerbianName();
            case 3 ->
                c.getGym().getName() + " - " + c.getGym().getAddress();
            default ->
                "";
        };
    }
    
    public void add(Client c){
        int idx=list.size();
        list.add(c);
        fireTableRowsInserted(idx, idx);
    }
    
    public void clear() {
        int size = list.size();
        if (size > 0) {
            list.clear();
            fireTableRowsDeleted(0, size - 1);
        }
    }
    
    public LinkedList<Client> getItems(){
        return list;
    }
    
    public void removeAt(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
    }

}
