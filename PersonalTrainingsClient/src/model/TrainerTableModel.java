/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.Client;
import domain.Trainer;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pc
 */
public class TrainerTableModel extends AbstractTableModel {

    private LinkedList<Trainer> list = new LinkedList<>();
    private String[] columns = {"Ime", "Prezime"};

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
        Trainer t = list.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                t.getName();
            case 1 ->
                t.getLastName();

            default ->
                "";
        };
    }

    public void add(Trainer t) {
        int idx = list.size();
        list.add(t);
        fireTableRowsInserted(idx, idx);
    }

    public void clear() {
        int size = list.size();
        if (size > 0) {
            list.clear();
            fireTableRowsDeleted(0, size - 1);
        }
    }

    public LinkedList<Trainer> getItems() {
        return list;
    }

    public void removeAt(int row) {
        list.remove(row);
        fireTableRowsDeleted(row, row);
    }

}
