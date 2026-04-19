package model;

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

    public static Transport add_transport(int object, String name){
        if (object == 1){
            Car new_car = new Car(name);
            cars.add(new_car);
            return new_car;
        }
        else if (object == 2){
            Train new_train = new Train(name, 100);
            trains.add(new_train);
            return new_train;
        }
        else if (object == 3){
            Express new_express = new Express(name);
            expresses.add(new_express);
            return new_express;
        }
        return null;
    }
    public static void add_transport(Transport transport){
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

    public static void remove_transport(Transport transport) {
        if (transport != null) {
            try {
                if (Objects.equals(transport.getType(), "Машина")){
                    cars.remove((Car)transport);
                }
                else if (Objects.equals(transport.getType(), "Поезд")){
                    trains.remove((Train)transport);
                }
                else {
                    expresses.remove((Express)transport);
                }
            }
            catch (InputMismatchException e){
                System.out.println("Значение должно быть числом");
            }
            catch (IndexOutOfBoundsException e){
                System.out.print("Транспортного средства с таки индексом нет");
            }
            catch (Exception e){
                System.out.println("Произошла неизвестная ошибка");
            }
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
