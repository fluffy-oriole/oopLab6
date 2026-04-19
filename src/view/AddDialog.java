package view;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;

public class AddDialog extends JDialog {
    private final JButton dialogButton = new JButton("Добавить");
    private final JComboBox<String> box = new JComboBox<>();
    private final JTextField transportNameToAdd = new JTextField(15);

    public JComboBox<String> getBox() {
        return box;
    }

    public JTextField getTransportNameToAdd() {
        return transportNameToAdd;
    }

    public JButton getDialogButton() {
        return dialogButton;
    }

    public AddDialog(JFrame parentFrame, String s) {
        super(parentFrame, s);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        JPanel elementsPanel = new JPanel();
        add(elementsPanel);
        elementsPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        elementsPanel.setLayout(new GridLayout(5, 1, 0, 20));

        JLabel label = new JLabel("Тип");
        label.setHorizontalAlignment(CENTER);
        elementsPanel.add(label);

        box.addItem("Машина");
        box.addItem("Поезд");
        box.addItem("Экспресс");
        elementsPanel.add(box, BorderLayout.NORTH);

        label = new JLabel("Название");
        label.setHorizontalAlignment(CENTER);
        elementsPanel.add(label);
        elementsPanel.add(transportNameToAdd, BorderLayout.CENTER);

        elementsPanel.add(dialogButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
