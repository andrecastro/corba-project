package br.edu.ifce.ppd.corba.helper;

import javax.swing.*;

/**
 * Created by andrecoelho on 4/16/16.
 */
public class Assets {

    private static ImageIcon horusEyeNight;
    private static ImageIcon horusEyeDay;
    private static ImageIcon skyDay;
    private static ImageIcon skyNight;

    static {
        horusEyeNight = new ImageIcon(Assets.class.getClassLoader().getResource("horus-eye.png"));
        horusEyeDay = new ImageIcon(Assets.class.getClassLoader().getResource("horus-eye-2.png"));
        skyDay = new ImageIcon(Assets.class.getClassLoader().getResource("sky-day.jpg"));
        skyNight = new ImageIcon(Assets.class.getClassLoader().getResource("sky-night.png"));
    }

    public static ImageIcon horusEyeNight() {
        return horusEyeNight;
    }

    public static ImageIcon horusEyeDay() {
        return horusEyeDay;
    }

    public static ImageIcon skyDay() {
        return skyDay;
    }

    public static ImageIcon skyNight() {
        return skyNight;
    }
}
