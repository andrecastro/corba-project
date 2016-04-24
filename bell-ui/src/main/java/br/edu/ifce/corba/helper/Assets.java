package br.edu.ifce.corba.helper;


import javax.swing.*;

/**
 * Created by andrecoelho on 4/16/16.
 */
public class Assets {

    private static ImageIcon bell;
    private static ImageIcon bellRinging;
    private static String alarmPath;

    static {
        bell = new ImageIcon(Assets.class.getClassLoader().getResource("bell.png"));
        bellRinging = new ImageIcon(Assets.class.getClassLoader().getResource("bell-ringing.png"));
        alarmPath = Assets.class.getClassLoader().getResource("alarm.wav").getPath();
    }

    public static String alarmPath() {
        return alarmPath;
    }

    public static ImageIcon bell() {
        return bell;
    }

    public static ImageIcon bellRinging() {
        return bellRinging;
    }
}
