package br.edu.ifce.corba.main;

import br.edu.ifce.corba.view.GateView;
import br.edu.ifce.ppd.corba.interfaces.watchman.Watchman;
import br.edu.ifce.ppd.corba.interfaces.watchman.WatchmanHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.UserException;

import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * Created by andrecoelho on 4/16/16.
 */
public class App {

    public static void main(String... args) {
        try {
            startApp(args);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage() +
                    "\n\n ---- Inicialize o serviço de nomes e o Guarda e tente novamente ----", "Erro", ERROR_MESSAGE);
        }
    }

    private static void startApp(String[] args) throws UserException {
        ORB orb = ORB.init(args, null);

        Client client = new Client(orb);
        Watchman watchman = WatchmanHelper.narrow(client.resolve(new String[]{"SERVICES", "WATCHMAN", "REF"}));

        new GateView(watchman);
    }
}
