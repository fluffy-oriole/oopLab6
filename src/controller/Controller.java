package controller;
import model.*;

import view.AddDialog;
import view.DeleteDialog;
import view.FindDialog;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Objects;

public class Controller {
    public void execute(MainFrame mainFrame, TransportTable model) {
        mainFrame.getTable().setModel(model);
        mainFrame.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileManager.save_data(mainFrame);
            }
        });
        mainFrame.getLoadButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileManager.load_data(mainFrame, model);
            }
        });

        mainFrame.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDialog dialog = new AddDialog(mainFrame, "Добавить транспорт");
                dialog.getDialogButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!Objects.equals(dialog.getTransportNameToAdd().getText(), "")) {
                            Transport transportToAdd = null;
                            String type = "";
                            if (dialog.getBox().getSelectedItem() != null) {
                                type = dialog.getBox().getSelectedItem().toString();
                            }
                            switch (type) {
                                case "Машина":
                                    transportToAdd = new Car(dialog.getTransportNameToAdd().getText()); break;
                                case "Поезд":
                                    transportToAdd = new Train(dialog.getTransportNameToAdd().getText()); break;
                                case "Экспресс":
                                    transportToAdd = new Express(dialog.getTransportNameToAdd().getText()); break;
                                default:
                                    break;
                            }
                            try {
                                if (transportToAdd != null) {
                                    TransportBD.writeInDB(transportToAdd, model);
                                    dialog.dispose();
                                }
                            }
                            catch (SQLException | ClassNotFoundException ex) {
                                System.out.println(ex.getMessage());
                                return;
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(dialog, ex.getMessage());
                            }

                        }
                        else {
                            JOptionPane.showMessageDialog(dialog, "Название не может быть пустым", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });

        mainFrame.getRemoveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteDialog dialog = new DeleteDialog();
                dialog.getDeleteButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String input = dialog.getDeleteTextField().getText();
                        if (!Objects.equals(input, "")){
                            try {
                                TransportBD.deleteFromDB(input, model);
                            } catch (SQLException | ClassNotFoundException ex) {
                                JOptionPane.showMessageDialog(dialog, ex.getMessage());
                            }
                            dialog.dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(dialog, "Поле не должно быть пустым");
                        }
                    }
                });
            }
        });

        mainFrame.getFindButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindDialog dialog = new FindDialog();
                dialog.getAcceptButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nameToFind = dialog.getTransportToFindTextField().getText();
                        if (Objects.equals(nameToFind, "")) {
                            JOptionPane.showMessageDialog(dialog, "Поле не может быть пустым");
                        }
                        else {
                            dialog.setFoundTransport(Transport_company.find_transport(nameToFind));
                            if (dialog.getFoundTransport() != null) {
                                dialog.getFoundTransportType().setText(dialog.getFoundTransport().getType());
                                dialog.getFoundTransportName().setText(dialog.getFoundTransport().getName());
                                dialog.getFoundTransportState().setText(Double.toString(dialog.getFoundTransport().getState()));
                                dialog.getCards().show(dialog.getContentPane(), "TRAVEL");
                            }
                            else {
                                JOptionPane.showMessageDialog(dialog, "Транспорт не найден");
                            }
                        }
                    }
                });

                dialog.getCloseButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });

                dialog.getMakeTravelButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String travelLength = dialog.getTravelLengthInput().getText();
                        if (Objects.equals(travelLength, "")){
                            JOptionPane.showMessageDialog(dialog, "Поле не может быть пустым");
                        }
                        else {
                            try {
                                int km = Integer.parseInt(travelLength);
                                double travelTime = dialog.getFoundTransport().calculate_travel_time(km);
                                double calculatedState = dialog.getFoundTransport().calculate_state(km);
                                if (dialog.getFoundTransport() != null && calculatedState >= 0) {
                                    dialog.getTime().setText(Long.toString( Math.round(travelTime * 10) / 10));
                                    dialog.getNewState().setText(Long.toString( Math.round(calculatedState * 10) / 10));

                                    dialog.getCards().show(dialog.getContentPane(), "TRAVEL-RESULT");
                                    TransportBD.updateStateInDB(dialog.getFoundTransport().getName(), calculatedState, model);
                                }
                                else {
                                    JOptionPane.showMessageDialog(dialog, "Состояние транспорта не позволяет" +
                                            "совершить такую поездку");
                                }
                            }
                            catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(dialog, "В поле должны быть только числа");
                            } catch (ClassNotFoundException ex) {
                                JOptionPane.showMessageDialog(dialog, "Class not found");
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(dialog, ex.getMessage());
                            }
                        }
                    }
                });
            }
        });

        class TypeFilterActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransportTable.setTransportTypeFilter(new boolean[] {mainFrame.getCarCheckBox().isSelected(), mainFrame.getTrainCheckBox().isSelected(), mainFrame.getExpressCheckBox().isSelected()});
                model.changeTable();
            }
        }

        class TextFieldsFilter implements KeyListener {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String filterName = mainFrame.getFilterTextField().getText();
                TransportTable.setNameFilter(filterName);

                String maxFilter = mainFrame.getMaxStateTextFieldFilter().getText();
                try {
                    if (!Objects.equals(maxFilter, "")) {
                        TransportTable.setMaxStateFilter(Double.parseDouble(maxFilter));
                    } else {
                        TransportTable.setMaxStateFilter(100);
                    }
                }
                catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(mainFrame,"Можно вводить только цифры и .");
                    mainFrame.getMaxStateTextFieldFilter().setText("");
                }
                try {
                    String minFilter = mainFrame.getMinStateTextFieldFilter().getText();
                    if (!Objects.equals(minFilter, "")) {
                        TransportTable.setMinStateFilter(Double.parseDouble(minFilter));
                    }
                    else {
                        TransportTable.setMinStateFilter(0);
                    }
                }
                catch (NumberFormatException exc) {
                    JOptionPane.showMessageDialog(mainFrame,"Можно вводить только цифры и .");
                    mainFrame.getMinStateTextFieldFilter().setText("");
                }

                model.changeTable();
            }
        }

        mainFrame.getCarCheckBox().addActionListener(new TypeFilterActionListener());
        mainFrame.getTrainCheckBox().addActionListener(new TypeFilterActionListener());
        mainFrame.getExpressCheckBox().addActionListener(new TypeFilterActionListener());

        mainFrame.getMaxStateTextFieldFilter().addKeyListener(new TextFieldsFilter());
        mainFrame.getMinStateTextFieldFilter().addKeyListener(new TextFieldsFilter());

        mainFrame.getFilterTextField().addKeyListener(new TextFieldsFilter());
    }
}