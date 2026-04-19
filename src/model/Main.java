package model;
import view.MainFrame;

import controller.Controller;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame("Транспортная компания");
        TransportTable model = new TransportTable();
        try {
            TransportBD.readDB(model);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            return;
        }
        catch (SQLException e) {
            System.out.println("SQL exception");
            return;
        }

        Controller controller = new Controller();
        controller.execute(mainFrame, model);
    }
}