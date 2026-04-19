package model;

public class Car extends Transport{
    public Car(String name) {
        super(name, 100);
    }
    public Car(String name, double state){
        super(name, state);
    }

    @Override
    public double calculate_travel_time(int travel_length) {
        return (double)travel_length / 60;
    }

    @Override
    public String getType() {
        return "Машина";
    }
}
