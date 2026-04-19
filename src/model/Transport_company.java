package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;

public class Transport_company {
    static private final ArrayList<Car> cars = new ArrayList<>();
    static private final ArrayList<Train> trains = new ArrayList<>();
    static private final ArrayList<Express> expresses = new ArrayList<>();

    public static ArrayList<Car> getCars() {
        return cars;
    }

    public static ArrayList<Train> getTrains() {
        return trains;
    }

    public static ArrayList<Express> getExpresses() {
        return expresses;
    }

    public static void add_transport(Transport transport) {
        if (Objects.equals(transport.getType(), "Машина")){
            Car car = (Car)transport;
            cars.add(car);
        }
        else if (Objects.equals(transport.getType(), "Поезд")){
            Train train = (Train)transport;
            trains.add(train);
        }
        else if (Objects.equals(transport.getType(), "Экспресс")){
            Express express = (Express)transport;
            expresses.add(express);
        }
    }

    public static Transport find_transport(String name){
        Car found_car = null;
        double maxState = -1;
        for (Car car : cars) {
            if (car.getName().equals(name) && car.getState() > maxState) {
                maxState = car.getState();
                found_car = car;
            }
        }
        if (maxState != -1) {
            return found_car;
        }

        Train found_train = null;
        for (Train train : trains) {
            if (train.getName().equals(name) && train.getState() > maxState) {
                maxState = train.getState();
                found_train = train;
            }
        }

        if (maxState != -1) {
            return found_train;
        }

        Express found_express = null;
        for (Express express : expresses) {
            if (express.getName().equals(name) && express.getState() > maxState) {
                maxState = express.getState();
                found_express = express;
            }
        }

        if (maxState != -1) {
            return found_express;
        }
        return null;
    }
}
