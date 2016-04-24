package br.edu.ifce.corba.view;

import br.edu.ifce.corba.helper.Assets;
import br.edu.ifce.ppd.corba.interfaces.watchman.Watchman;

import javax.swing.*;
import java.awt.*;

/**
 * Created by andrecoelho on 4/17/16.
 */
public class GateView extends JFrame {

    private Watchman watchman;
    private Integer currentNumber;

    private JLabel gateImage;

    private JLabel numberOfPeopleLabel;
    private JTextField number;
    private JButton incrementButton;
    private JButton decrementButton;

    public GateView(Watchman watchman) {
        this.watchman = watchman;
        this.init();
    }

    private void init() {
        currentNumber = watchman.getNumberOfPeople();

        gateImage = new JLabel(Assets.gate());
        gateImage.setAlignmentX(CENTER_ALIGNMENT);

        numberOfPeopleLabel = new JLabel("Numero de pessoas");

        incrementButton =  new JButton("+");
        incrementButton.addActionListener(a -> incrementNumber());

        number = new JTextField(currentNumber.toString());
        number.setEditable(false);
        number.setPreferredSize(new Dimension(50, 20));

        decrementButton =  new JButton("-");
        decrementButton.addActionListener(a -> decrementNumber());

        JPanel controlRow = new JPanel();
        controlRow.setLayout(new FlowLayout());
        controlRow.add(numberOfPeopleLabel);
        controlRow.add(incrementButton);
        controlRow.add(number);
        controlRow.add(decrementButton);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(controlRow);
        add(gateImage);

        setTitle("Portao");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void incrementNumber() {
        currentNumber++;
        number.setText(currentNumber.toString());
        watchman.incrementNumberOfPeople();
    }

    private void decrementNumber() {
        if (currentNumber > 0) {
            currentNumber--;
            number.setText(currentNumber.toString());
            watchman.decrementNumberOfPeople();
        }
    }
}
