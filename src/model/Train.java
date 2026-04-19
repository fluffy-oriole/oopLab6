package model;

public class Train extends Transport {
    private int time_between_stops = 1;
    private double stop_duration = 0.5;


    public Train(String name, int state) {
        super(name, state);
    }

    @Override
    public double calculate_travel_time(int travel_length) {
        double hours = travel_length / 80.0;
        int stop_count = (int)(hours / time_between_stops);
        int possible_delay = 1;
        return hours + stop_count * stop_duration + possible_delay;
    }

    @Override
    public String getType() {
        return "Поезд";
    }

    public void setTime_between_stops(int time_between_stops) {
        this.time_between_stops = time_between_stops;
    }

    public void setStop_duration(double stop_duration) {
        this.stop_duration = stop_duration;
    }

    public int getTime_between_stops() {
        return time_between_stops;
    }

    public double getStop_duration() {
        return stop_duration;
    }
}
