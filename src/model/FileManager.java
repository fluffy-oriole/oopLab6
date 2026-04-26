package model;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.SQLException;

public class FileManager {

    public static void save_data(Frame parentFrame) {
        FileDialog dialog = new FileDialog(parentFrame, "сохранение", FileDialog.SAVE);
        dialog.setSize(600, 400);
        dialog.setFile("transport_company.txt");
        dialog.setVisible(true);
        String directory = dialog.getDirectory();
        String name = dialog.getFile();
        String path;

        if (name != null) {
            if (!name.contains(".txt")) {
                name += ".txt";
            }
            path = directory + name;
        }
        else {
            path = directory + "transports.txt";
        }
        try {
            File file = new File(path);
            try (FileWriter writer = new FileWriter(file)){
                writer.write(Integer.toString(Transport_company.getTransports().size()) + '\n');
                for (Transport t : Transport_company.getTransports()) {
                    writer.write(t.getType() + " " + t.getName() + " " + t.getState() + "\n");
                }
            }
            catch (IOException e) {
                JOptionPane.showMessageDialog(dialog, "Ошибка вывода: " + e.getMessage());
            }
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(dialog, "Произошла неизвестная ошибка");
        }
    }

    public static void load_data(JFrame parentFrame, TransportTable model) {
        try {
            for (Transport t : Transport_company.getTransports()) {
                TransportBD.deleteFromDB(t.getName(), model);
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            return;
        }
        Transport_company.getTransports().clear();

        FileDialog dialog = new FileDialog(parentFrame);
        dialog.setSize(600, 400);
        dialog.setResizable(true);
        dialog.setVisible(true);
        String directory = dialog.getDirectory();
        String name = dialog.getFile();
        String path;
        if (name != null) {
            path = directory + name;
            if (!name.contains(".txt")) {
                JOptionPane.showMessageDialog(parentFrame, "Файл должен иметь расширение .txt");
                return;
            }
            try {
                if (path.isEmpty()) {
                    throw new IllegalArgumentException("Путь не должен быть пустым");
                }
                File file = new File(path);
                try (FileReader reader = new FileReader(file)){
                    BufferedReader buffReader = new BufferedReader(reader);
                    int size = Integer.parseInt(buffReader.readLine().trim());
                    String line;
                    for (int i = 0; i < size; i++) {
                        line = buffReader.readLine().trim();
                        String typeToAdd = line.split(" ")[0];
                        String nameToAdd = line.split(" ")[1];
                        double stateToAdd = Double.parseDouble(line.split(" ")[2]);
                        Transport transportToAdd = switch (typeToAdd) {
                            case "Машина" -> new Car(nameToAdd, stateToAdd);
                            case "Поезд" -> new Train(nameToAdd, stateToAdd);
                            case "Экспресс" -> new Express(nameToAdd, stateToAdd);
                            default -> throw new IllegalArgumentException("Не удалось распознать тип транспорта");
                        };
                        Transport_company.add_transport(transportToAdd);
                    }
                    model.changeTable();}
                catch (IOException e) {
                    JOptionPane.showMessageDialog(parentFrame, "Ошибка ввода: " + e.getMessage());
                }
            }
            catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(parentFrame, "Поврежденный или неправильный файл" + e.getMessage());
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(parentFrame, "Произошла неизвестная ошибка");
            }
        }
    }
}
