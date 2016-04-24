package br.edu.ifce.ppd.corba.view;

import br.edu.ifce.ppd.corba.helper.Assets;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.BorderFactory.createTitledBorder;
import static javax.swing.border.TitledBorder.CENTER;

/**
 * Created by andrecoelho on 4/16/16.
 */
public class TimeModeView extends JPanel {

    private JLabel nightEye;
    private JLabel dayEye;
    private JToggleButton timeMode;

    private TitledBorder border;
    private Border dayBorder;
    private Border nightBorder;

    private GridBagConstraints constraints;

    private WatchmanView parent;

    public TimeModeView(WatchmanView watchmanView) {
        init(watchmanView);
    }

    private void init(WatchmanView watchmanView) {
        parent = watchmanView;
        nightEye = new JLabel(Assets.horusEyeNight());
        dayEye = new JLabel(Assets.horusEyeDay());

        timeMode =  new JToggleButton("Mudar para noite");
        timeMode.addActionListener(a -> changeTimeMode());

        Font titleFont = new Font("Monospaced", Font.BOLD, 30);
        dayBorder = createLineBorder(Color.BLACK);
        nightBorder = createLineBorder(Color.WHITE);
        border = createTitledBorder(dayBorder, "Modo Dia", CENTER, CENTER, titleFont);

        setBorder(border);
        setPreferredSize(new Dimension(500, 260));
        setLayout(new GridBagLayout());

        constraints = new GridBagConstraints();

        setButtonConstraints();
        add(timeMode, constraints);

        setEyeConstraints();
        add(dayEye, constraints);
    }

    public Boolean isNight() {
        return timeMode.isSelected();
    }

    private void setEyeConstraints() {
        constraints.weightx = dayEye.getAlignmentX();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
    }

    private void setButtonConstraints() {
        constraints.weightx = timeMode.getWidth();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
    }

    private void changeToNight() {
        parent.notifyChangeToNight();

        border.setTitle("Modo Noite");
        border.setTitleColor(Color.WHITE);
        border.setBorder(nightBorder);

        timeMode.setText("Mudar para dia");

        remove(dayEye);
        setEyeConstraints();
        add(nightEye, constraints);
        repaint();
        revalidate();
    }

    private void changeToDay() {
        parent.notifyChangeToDay();

        border.setTitle("Modo Dia");
        border.setTitleColor(Color.BLACK);
        border.setBorder(dayBorder);

        timeMode.setText("Mudar para noite");

        remove(nightEye);
        setEyeConstraints();
        add(dayEye, constraints);
        repaint();
        revalidate();
    }

    private void changeTimeMode() {
        if (timeMode.isSelected()) {
            changeToNight();
        } else {
            changeToDay();
        }

        repaint();
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (timeMode.isSelected()) {
            g.drawImage(Assets.skyNight().getImage(), 0, 0, null);
        } else {
            g.drawImage(Assets.skyDay().getImage(), 0, 0, null);
        }
    }
}
