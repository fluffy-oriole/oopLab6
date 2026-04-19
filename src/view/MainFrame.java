package view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.JTableHeader;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;


public class MainFrame extends JFrame {
    static Color mainColor = new Color(13, 108, 247);
    static Color textColor = new Color(255, 255, 255);
    static Color secondColor = new Color(250, 250, 250);
    
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

        setSize(1280, 720);
        setMinimumSize(new Dimension(960, 540));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(secondColor);
        add(mainPanel);

        table.setRowHeight(30);
        JTableHeader header = table.getTableHeader();
        header.setForeground(Color.BLACK);
        header.setBackground(secondColor);
        table.setSelectionBackground(mainColor);
        table.setSelectionForeground(textColor);

        JScrollPane pane = new JScrollPane(table);
        pane.getViewport().setBackground(secondColor);
        mainPanel.add(pane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(secondColor);
        buttonsPanel.setLayout(new FlowLayout());
        mainPanel.add(buttonsPanel, BorderLayout.NORTH);

        saveButton.setBackground(mainColor);
        saveButton.setForeground(textColor);
        saveButton.setFocusPainted(false);
        saveButton.setBorderPainted(false);

        loadButton.setBackground(mainColor);
        loadButton.setForeground(textColor);
        loadButton.setFocusPainted(false);
        loadButton.setBorderPainted(false);

        addButton.setBackground(mainColor);
        addButton.setForeground(textColor);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);

        removeButton.setBackground(mainColor);
        removeButton.setForeground(textColor);
        removeButton.setBorderPainted(false);
        removeButton.setFocusPainted(false);

        findButton.setBackground(mainColor);
        findButton.setForeground(textColor);
        findButton.setBorderPainted(false);
        findButton.setFocusPainted(false);

        buttonsPanel.add(saveButton);
        buttonsPanel.add(loadButton);


        buttonsPanel.add(addButton);
        buttonsPanel.add(removeButton);
        buttonsPanel.add(findButton);

        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(secondColor);
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
        carCheckBox.setBackground(secondColor);
        filterPanel.add(carCheckBox);


        trainCheckBox.setSelected(true);
        trainCheckBox.setBackground(secondColor);
        filterPanel.add(trainCheckBox);


        expressCheckBox.setSelected(true);
        expressCheckBox.setBackground(secondColor);
        filterPanel.add(expressCheckBox);

        JLabel nameFilterLabel = new JLabel("Имя");
        filterPanel.add(nameFilterLabel);

        filterTextField.setMaximumSize(new Dimension(180, 30));
        filterTextField.setBorder(new BorderUIResource.EtchedBorderUIResource());
        filterPanel.add(filterTextField);

        JLabel maxStateFilterLabel = new JLabel("Макс. состояние");
        filterPanel.add(maxStateFilterLabel);

        maxStateTextFieldFilter.setMaximumSize(new Dimension(180, 30));
        maxStateTextFieldFilter.setBorder(new BorderUIResource.EtchedBorderUIResource());
        filterPanel.add(maxStateTextFieldFilter);
        JLabel minStateFilterLabel = new JLabel("Мин. состояние");
        filterPanel.add(minStateFilterLabel);

        minStateTextFieldFilter.setMaximumSize(new Dimension(180, 30));
        minStateTextFieldFilter.setBorder(new BorderUIResource.EtchedBorderUIResource());
        filterPanel.add(minStateTextFieldFilter);

        this.setVisible(true);
    }
}