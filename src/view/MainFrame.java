package view;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;


public class MainFrame extends JFrame {
    private final JTable table = new JTable();
    private final JButton saveButton = new JButton("Сохранить");
    private final JButton loadButton = new JButton("Открыть");
    private final JButton removeButton = new JButton("Удалить");
    private final JButton addButton = new JButton("Добавить");
    private final JButton findButton = new JButton("Найти");
    private final JCheckBox carCheckBox = new JCheckBox("Машина");
    private final JCheckBox trainCheckBox = new JCheckBox("Поезд");
    private final JCheckBox expressCheckBox = new JCheckBox("Экспресс");
    private final JTextField maxStateTextFieldFilter = new JTextField(15);
    private final JTextField minStateTextFieldFilter = new JTextField(15);
    private final JTextField filterTextField = new JTextField(15);

    public JTextField getFilterTextField() {
        return filterTextField;
    }

    public JTextField getMaxStateTextFieldFilter() {
        return maxStateTextFieldFilter;
    }

    public JTextField getMinStateTextFieldFilter() {
        return minStateTextFieldFilter;
    }

    public JCheckBox getCarCheckBox() {
        return carCheckBox;
    }

    public JCheckBox getTrainCheckBox() {
        return trainCheckBox;
    }

    public JCheckBox getExpressCheckBox() {
        return expressCheckBox;
    }

    public JButton getFindButton() {
        return findButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTable getTable() {
        return table;
    }

    public MainFrame(String s) {
        super(s);
        ImageIcon ico = new ImageIcon("D:\\education\\объектно ориентированное программирование\\лабораторная работа №5\\gui\\src\\ico.png");
        setIconImage(ico.getImage());
        setSize(1280, 720);
        setMinimumSize(new Dimension(960, 540));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        table.setRowHeight(30);
        JScrollPane pane = new JScrollPane(table);
        mainPanel.add(pane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        mainPanel.add(buttonsPanel, BorderLayout.NORTH);

        buttonsPanel.add(saveButton);
        buttonsPanel.add(loadButton);

        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(findButton);

        JPanel filterPanel = new JPanel();
        filterPanel.setPreferredSize(new Dimension(110, 600));
        mainPanel.add(filterPanel, BorderLayout.WEST);
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        JLabel filterLabel = new JLabel("Фильтры");
        filterLabel.setHorizontalAlignment(CENTER);
        filterPanel.add(filterLabel);
        JLabel typeFilterLabel = new JLabel("Тип");
        typeFilterLabel.setHorizontalAlignment(CENTER);
        filterPanel.add(typeFilterLabel);


        carCheckBox.setSelected(true);
        filterPanel.add(carCheckBox);

        trainCheckBox.setSelected(true);
        filterPanel.add(trainCheckBox);

        expressCheckBox.setSelected(true);
        filterPanel.add(expressCheckBox);

        JLabel nameFilterLabel = new JLabel("Имя");
        filterPanel.add(nameFilterLabel);

        filterTextField.setMaximumSize(new Dimension(180, 30));
        filterPanel.add(filterTextField);

        JLabel maxStateFilterLabel = new JLabel("Макс. состояние");
        filterPanel.add(maxStateFilterLabel);

        maxStateTextFieldFilter.setMaximumSize(new Dimension(180, 30));
        filterPanel.add(maxStateTextFieldFilter);
        JLabel minStateFilterLabel = new JLabel("Мин. состояние");
        filterPanel.add(minStateFilterLabel);

        minStateTextFieldFilter.setMaximumSize(new Dimension(180, 30));
        filterPanel.add(minStateTextFieldFilter);

        this.setVisible(true);
    }
}