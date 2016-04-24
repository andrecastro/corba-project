package br.edu.ifce.ppd.corba.view;

import java.awt.*;

import javax.swing.*;

import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.BorderFactory.createTitledBorder;
import static javax.swing.border.TitledBorder.CENTER;

public class DisplayView extends JPanel {

    private JLabel displayLabel;

    public DisplayView() {
        init();
    }

    private void init() {
        displayLabel = new JLabel(" 0 ");
        displayLabel.setFont(new Font("Monospaced", Font.BOLD, 100));

        Font titleFont = new Font("Monospaced", Font.BOLD, 30);
        setBorder(createTitledBorder(createLineBorder(Color.BLACK), "Número de Pessoas", CENTER, CENTER, titleFont));
        setPreferredSize(new Dimension(500, 180));
        add(displayLabel);
    }

    public void updateWith(int number) {
        updateDisplay(number);
        repaint();
        revalidate();
    }

    private void updateDisplay(int number) {
        displayLabel.setText(" " + String.valueOf(number) + " ");
    }
}
