package model;

import javax.swing.*;
import java.awt.*;
import java.io.*;

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
                writer.write(Integer.toString(Transport_company.getCars().size()) + '\n');
                for (int i = 0; i < Transport_company.getCars().size(); i++) {
                    writer.write(Transport_company.getCars().get(i).getName() + ' '
                            + Transport_company.getCars().get(i).getState() + '\n');
                }
                writer.write(Integer.toString(Transport_company.getTrains().size()) +'\n');
                for (int i = 0; i < Transport_company.getTrains().size(); i++) {
                    writer.write(Transport_company.getTrains().get(i).getName() + ' ' +
                            Transport_company.getTrains().get(i).getState() + '\n');
                }
                writer.write(Integer.toString(Transport_company.getExpresses().size()) + '\n');
                for (int i = 0; i < Transport_company.getExpresses().size(); i++) {
                    writer.write(Transport_company.getExpresses().get(i).getName() + ' ' +
                            Transport_company.getExpresses().get(i).getState() + '\n');
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
                    Transport current_transport;
                    String line;
                    for (int i = 0; i < size; i++) {
                        line = buffReader.readLine().trim();
                        current_transport = Transport_company.add_transport(1, line.split(" ")[0]);
                        if (current_transport != null){
                            current_transport.setState((Double.parseDouble(line.split(" ")[1])));
                        }
                        else {
                            JOptionPane.showMessageDialog(parentFrame, "Поврежденный или неправильный файл");
                            return;
                        }
                    }
                    size = Integer.parseInt(buffReader.readLine().trim());
                    for (int i = 0; i < size; i++) {
                        line = buffReader.readLine().trim();
                        current_transport = Transport_company.add_transport(2, line.split(" ")[0]);
                        if (current_transport != null){
                            current_transport.setState((Double.parseDouble(line.split(" ")[1])));
                        }
                        else {
                            JOptionPane.showMessageDialog(parentFrame, "Поврежденный или неправильный файл");
                            return;
                        }
                    }
                    size = Integer.parseInt(buffReader.readLine().trim());
                    for (int i = 0; i < size; i++) {
                        line = buffReader.readLine().trim();
                        current_transport = Transport_company.add_transport(3, line.split(" ")[0]);
                        if (current_transport != null){
                            current_transport.setState((Double.parseDouble(line.split(" ")[1])));
                        }
                        else {
                            JOptionPane.showMessageDialog(parentFrame, "Поврежденный или неправильный файл");
                            return;
                        }
                    }
                    model.changeTable();}
                catch (IOException e) {
                    JOptionPane.showMessageDialog(parentFrame, "Ошибка ввода: " + e.getMessage());
                }
            }
            catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(parentFrame, "Поврежденный или неправильный файл");
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(parentFrame, "Произошла неизвестная ошибка");
            }
        }
    }
}
