package model;

public class Express extends Train{
    public Express(String name) {
        super(name, 50);
        setTime_between_stops(3);
        setStop_duration(0.1);
    }

    public Express(String name, double state) {
        super(name, state);
        setTime_between_stops(3);
        setStop_duration(0.1);
    }

    @Override
    public double calculate_travel_time(int travel_length) {
        double hours = travel_length / 80.0;
        int stop_count = (int)(hours / getTime_between_stops());
        return hours + stop_count * getStop_duration();
    }

    @Override
    public String getType() {
        return "Экспресс";
    }
}
