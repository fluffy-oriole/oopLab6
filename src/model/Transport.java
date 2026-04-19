package model;

public abstract class Transport implements RouteCalculable, Displayable {
    private final String name;
    private double state;

    public Transport(String name, int state){
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

    public void setState(double state) {
        this.state = state;
    }

    public void display() {
        System.out.println("Название: " + getName());
        System.out.println("Состояние: " + getState());
    }
}
