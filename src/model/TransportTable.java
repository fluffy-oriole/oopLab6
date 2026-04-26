package model;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransportTable extends AbstractTableModel {
    private static final ArrayList<Transport> filteredTransports = new ArrayList<>();
    private static boolean[] transportTypeFilter = new boolean[] {true, true, true};
    private static double maxStateFilter = 100;
    private static double minStateFilter = 0;
    private static String nameFilter = "";

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
        filterTransport(transportTypeFilter, maxStateFilter, minStateFilter);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return filteredTransports.size();
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
                return filteredTransports.get(rowIndex).getType();
            case 2:
                return filteredTransports.get(rowIndex).getName();
            case 3:
                return filteredTransports.get(rowIndex).getState();
            default:
                return "-";
        }
    }

    public void addTransport(Transport transportToAdd) {
        Transport_company.add_transport(transportToAdd);
        this.changeTable();
    }


    public void filterTransport(boolean[] types, double maxStateFilter, double minStateFilter) {
        filteredTransports.clear();
        for (Transport transport : Transport_company.getTransports()) {
            if (transport.getState() <= maxStateFilter && transport.getState() >= minStateFilter
                    && transport.getName().contains(nameFilter)) {
                if (transport.getType().equals("Машина") && types[0]) {
                    filteredTransports.add(transport);
                }
                if (transport.getType().equals("Поезд") && types[1]) {
                    filteredTransports.add(transport);
                }
                if (transport.getType().equals("Экспресс") && types[2]) {
                    filteredTransports.add(transport);
                }
            }
        }
    }
}

