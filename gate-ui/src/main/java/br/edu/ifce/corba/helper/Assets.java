package br.edu.ifce.corba.helper;


import javax.swing.*;

/**
 * Created by andrecoelho on 4/16/16.
 */
public class Assets {

    private static ImageIcon gate;

    static {
        gate = new ImageIcon(Assets.class.getClassLoader().getResource("gate.png"));
    }

    public static ImageIcon gate() {
        return gate;
    }
}
