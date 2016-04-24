package br.edu.ifce.ppd.corba.view;

import br.edu.ifce.ppd.corba.interfaces.bell.Bell;

import javax.swing.*;
import java.awt.*;

/**
 * Created by andrecoelho on 4/16/16.
 */
public class WatchmanView extends JFrame {

    private Integer numberOfPeopleInTheMuseum;

    private Bell bell;
    private DisplayView displayView;
    private TimeModeView timeModeView;

    public WatchmanView(Bell bell) {
        this.bell = bell;
        init();
    }

    private void init() {
        numberOfPeopleInTheMuseum = 0;
        displayView =  new DisplayView();
        timeModeView = new TimeModeView(this);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(displayView);
        add(timeModeView);

        setTitle("Vigia Horus");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 460));
        setResizable(false);
    }

    public void start() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateDisplay(int numberOfPeople) {
        displayView.updateWith(numberOfPeople);

        if (timeModeView.isNight()) {
            if (numberOfPeople == 0) {
                bell.stopDing();
            } else if (numberOfPeopleInTheMuseum == 0) {
                bell.startDing();
            }
        }

        numberOfPeopleInTheMuseum = numberOfPeople;
    }

    public void notifyChangeToNight() {
        if (numberOfPeopleInTheMuseum != 0) {
            bell.startDing();
        }
    }

    public void notifyChangeToDay() {
        bell.stopDing();
    }
}
