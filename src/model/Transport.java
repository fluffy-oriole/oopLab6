package model;

public abstract class Transport {
    private final String name;
    private double state;

    public Transport(String name, double state){
        this.name = name;
        this.state = state;
    }

    public abstract double calculate_travel_time(int travel_length);

    public double calculate_state(int travel_length){
        state -= travel_length * 0.01;
        return state;
    }

    public abstract String getType();
    public double getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}
