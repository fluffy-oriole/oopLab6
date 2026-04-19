package model;

import java.sql.*;

public class TransportBD {
    public static Connection transportBD;
    public static Statement stab;
    public static ResultSet result;

    public void connectToBD() throws ClassNotFoundException, SQLException  {
        Class.forName("org.sqlite.JDBC");
        transportBD = DriverManager.getConnection("jdbc:sqlite:dataTransport.db");
    }
    public static void readDB(TransportTable model) throws ClassNotFoundException, SQLException
    {
        stab = transportBD.createStatement();
        result = stab.executeQuery("SELECT * FROM transports as t JOIN transport_types as types ON t.type = types.definition");
        while(result.next())
        {
            String type = result.getString("value");
            Transport transportToAdd = null;
            switch (type) {
                case "Машина":
                    transportToAdd = new Car(result.getString("name"), result.getInt("state")); break;
                case "Поезд":
                    transportToAdd = new Train(result.getString("name"), result.getInt("state")); break;
                case "Экспресс":
                    transportToAdd = new Express(result.getString("name"), result.getInt("state")); break;
            }
            if (transportToAdd != null) {
                model.addTransport(transportToAdd);
            }
        }
    }
    public static void closeConnection(){

    }
}
