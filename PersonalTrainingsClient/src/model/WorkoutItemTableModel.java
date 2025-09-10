/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.WorkoutItem;
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pc
 */
public class WorkoutItemTableModel extends AbstractTableModel{
    private LinkedList<WorkoutItem> list=new LinkedList<>();
    private String[] columns={"Naziv","Kategorija","Broj serija","Kilaža","Intenzitet"};
    
    
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
        WorkoutItem wi=list.get(rowIndex);
        return switch(columnIndex){
            case 0->wi.getActivity().getName();
            case 1->wi.getActivity().getCategory().getSerbianName();
            case 2->wi.getNumOfSeries();
            case 3->wi.getMass();
            case 4->wi.getIntensity().getSerbianName();
            default->null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    public void add(WorkoutItem wi){
        int idx=list.size();
        list.add(wi);
        fireTableRowsInserted(idx, idx);
    }
    
    public LinkedList<WorkoutItem> getItems(){
        return list;
    }
    
    public void removeAt(int row){
        list.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
    public void refreshData(){
        fireTableDataChanged();
    }
    
}
