package model;

public interface RouteCalculable {
    double calculate_travel_time(int travel_length);
    double calculate_state(int travel_length);
    String getName();
}
