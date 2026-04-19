package model;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransportTable extends AbstractTableModel {
    private static final ArrayList<Transport> allTransport = new ArrayList<>();
    private static boolean[] transportTypeFilter = new boolean[] {true, true, true};
    private static double maxStateFilter = 100;
    private static double minStateFilter = 0;
    private static String nameFilter = "";

    public static ArrayList<Transport> getAllTransport() {
        return allTransport;
    }

    public static void setNameFilter(String nameFilter) {
        TransportTable.nameFilter = nameFilter;
    }

    public static void setMaxStateFilter(double maxStateFilter) {
        TransportTable.maxStateFilter = maxStateFilter;
    }

    public static void setMinStateFilter(double minStateFilter) {
        TransportTable.minStateFilter = minStateFilter;
    }

    public static void setTransportTypeFilter(boolean[] transportTypeFilter) {
        TransportTable.transportTypeFilter = transportTypeFilter;
    }

    public void changeTable() {
        allTransport.clear();
        allTransport.addAll(Transport_company.getCars());
        allTransport.addAll(Transport_company.getTrains());
        allTransport.addAll(Transport_company.getExpresses());
        filterTransport(transportTypeFilter, maxStateFilter, minStateFilter);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return allTransport.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "№";
            case 1:
                return "Тип";
            case 2:
                return "Имя";
            case 3:
                return "Состояние";
            default: return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        switch (columnIndex) {
            case 0:
                return rowIndex+1;
            case 1:
                return allTransport.get(rowIndex).getType();
            case 2:
                return allTransport.get(rowIndex).getName();
            case 3:
                return allTransport.get(rowIndex).getState();
            default:
                return "-";
        }
    }

    public void addTransport(Transport transportToAdd) {
        Transport_company.add_transport(transportToAdd);
        this.changeTable();
    }


    public void filterTransport(boolean[] types, double maxStateFilter, double minStateFilter) {
        ArrayList<Transport> filteredList = new ArrayList<>();
        for (Transport transport : allTransport) {
            if (transport.getState() <= maxStateFilter && transport.getState() >= minStateFilter
                    && transport.getName().contains(nameFilter)) {
                if (transport.getType().equals("Машина") && types[0]) {
                    filteredList.add(transport);
                }
                if (transport.getType().equals("Поезд") && types[1]) {
                    filteredList.add(transport);
                }
                if (transport.getType().equals("Экспресс") && types[2]) {
                    filteredList.add(transport);
                }
            }
        }
        allTransport.clear();
        allTransport.addAll(filteredList);
    }
}

