package br.edu.ifce.corba.view;

import javax.swing.*;

import static br.edu.ifce.corba.helper.Assets.*;
import static br.edu.ifce.corba.helper.Assets.bell;

/**
 * Created by andrecoelho on 4/17/16.
 */
public class BellView extends JFrame {

    private JLabel bell;

    public BellView() {
        init();
    }

    private void init() {
        bell = new JLabel(bell());
        add(bell);

        setTitle("Alarme");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
    }

    public void startDing() {
        bell.setIcon(bellRinging());
    }

    public void stopDinging() {
        bell.setIcon(bell());
    }

    public void start() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
