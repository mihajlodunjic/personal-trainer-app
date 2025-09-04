/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.WorkoutRecord;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pc
 */
public class WorkoutRecordTableModel extends AbstractTableModel {

    private LinkedList<WorkoutRecord> list = new LinkedList<>();
    private String columns[] = {"Datum", "Vreme pocetka", "Vreme zavrsetka", "Trajanje", "Intenzitet"};

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
        WorkoutRecord wr = list.get(rowIndex);
        return switch (columnIndex) {
            case 0 ->
                wr.getWorkoutDate();
            case 1 ->
                wr.getStartTime();
            case 2 ->
                wr.getEndTime();
            case 3 ->
                wr.getDuration();
            case 4 ->
                wr.getAvgIntensity().getSerbianName();
            default ->
                "";
        };
    }

    public void removeAt(int row) {
        list.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void add(WorkoutRecord wr) {
        int idx = list.size();
        list.add(wr);
        fireTableRowsInserted(idx, idx);
    }

    public void clear() {
        int size = list.size();
        if (size > 0) {
            list.clear();
            fireTableRowsDeleted(0, size - 1);
        }
    }

    public void setList(LinkedList<WorkoutRecord> list) {
        this.list = list;
        fireTableDataChanged();
        
    }

    public LinkedList<WorkoutRecord> getList() {
        return list;
    }
    
    
}
