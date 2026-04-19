package view;

import model.Transport;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;

public class FindDialog extends JDialog {
    private final JButton acceptButton = new JButton("Найти");
    private Transport foundTransport;
    private final JTextField transportToFindTextField = new JTextField();
    private final JLabel foundTransportType = new JLabel("тип");
    private final JLabel foundTransportState = new JLabel("тип");
    private final JLabel foundTransportName = new JLabel("тип");
    private final CardLayout cards = new CardLayout();
    private final JButton closeButton = new JButton("Закрыть");
    private final JButton makeTravelButton = new JButton("Совершить поездку");
    private final JLabel time = new JLabel("Время");
    private final JLabel newState = new JLabel("Состояние");
    private final JTextField travelLengthInput = new JTextField(15);

    public JTextField getTravelLengthInput() {
        return travelLengthInput;
    }

    public JLabel getTime() {
        return time;
    }

    public JLabel getNewState() {
        return newState;
    }

    public JButton getMakeTravelButton() {
        return makeTravelButton;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public CardLayout getCards() {
        return cards;
    }

    public JLabel getFoundTransportType() {
        return foundTransportType;
    }

    public JLabel getFoundTransportState() {
        return foundTransportState;
    }

    public JLabel getFoundTransportName() {
        return foundTransportName;
    }

    public JTextField getTransportToFindTextField() {
        return transportToFindTextField;
    }

    public Transport getFoundTransport() {
        return foundTransport;
    }

    public void setFoundTransport(Transport t) {
        foundTransport = t;
    }

    public JButton getAcceptButton() {
        return acceptButton;
    }


    public FindDialog() {
        // JDialog dialog = new JDialog();
        setSize(400, 300);
        foundTransport = null;


        setLayout(cards);
        JPanel findTransportCard = new JPanel();
        findTransportCard.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        add(findTransportCard, "SEARCH");

        findTransportCard.setLayout(new GridLayout(3, 1, 0, 50));
        JLabel findLabel = new JLabel("Введите имя транспорта для поиска");
        findLabel.setHorizontalAlignment(CENTER);
        findTransportCard.add(findLabel);

        findTransportCard.add(transportToFindTextField);

        findTransportCard.add(acceptButton);

        JPanel foundTransportCard = new JPanel(new BorderLayout());
        foundTransportCard.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        JLabel foundTransportTopText = new JLabel("Найденный транспорт:");
        foundTransportTopText.setHorizontalAlignment(CENTER);
        foundTransportCard.add(foundTransportTopText, BorderLayout.NORTH);
        JPanel foundTransportLabels = new JPanel(new GridLayout(3, 2));

        JLabel type = new JLabel("Тип");
        foundTransportLabels.add(type);

        foundTransportType.setHorizontalAlignment(CENTER);
        foundTransportLabels.add(foundTransportType);

        JLabel name = new JLabel("Название");
        foundTransportLabels.add(name);

        foundTransportName.setHorizontalAlignment(CENTER);
        foundTransportLabels.add(foundTransportName);

        JLabel state = new JLabel("Состояние");
        foundTransportLabels.add(state);

        foundTransportState.setHorizontalAlignment(CENTER);
        foundTransportLabels.add(foundTransportState);




        JPanel foundTransportButtons = new JPanel(new GridLayout(3, 1, 0, 15));
        foundTransportCard.add(foundTransportLabels, BorderLayout.CENTER);
        JLabel inputKmToTravelLabel = new JLabel("Введите растояние поездки (в км)");
        foundTransportButtons.add(inputKmToTravelLabel);
        foundTransportButtons.add(travelLengthInput);

        foundTransportButtons.add(makeTravelButton);

        foundTransportCard.add(foundTransportButtons, BorderLayout.SOUTH);

        foundTransportCard.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        add(foundTransportCard, "TRAVEL");





        JPanel resultTravel = new JPanel(new BorderLayout());
        resultTravel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        resultTravel.add(closeButton, BorderLayout.SOUTH);
        JPanel resultLabels = new JPanel(new GridLayout(2, 2));

        JLabel timeLabel = new JLabel("Время поездки: ");
        resultLabels.add(timeLabel);

        resultLabels.add(time);
        JLabel newStateLabel = new JLabel("Новое состояние: ");
        resultLabels.add(newStateLabel);

        resultLabels.add(newState);
        resultTravel.add(resultLabels, BorderLayout.CENTER);

        add(resultTravel, "TRAVEL-RESULT");

        setVisible(true);
    }
}
