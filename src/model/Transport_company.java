package model;

import java.util.ArrayList;
import java.util.Objects;

public class Transport_company {
    static private final ArrayList<Transport> transports = new ArrayList<>();

    public static ArrayList<Transport> getTransports() {
        return transports;
    }

    public static void add_transport(Transport transport) {
        if (Objects.equals(transport.getType(), "Машина")){
            Car car = (Car)transport;
            transports.add(car);
        }
        else if (Objects.equals(transport.getType(), "Поезд")){
            Train train = (Train)transport;
            transports.add(train);
        }
        else if (Objects.equals(transport.getType(), "Экспресс")){
            Express express = (Express)transport;
            transports.add(express);
        }
    }

    public static Transport find_transport(String name) {
        for (Transport t : transports) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
        }
    }
