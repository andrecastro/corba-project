package br.edu.ifce.ppd.corba.main;

import br.edu.ifce.ppd.corba.interfaces.bell.Bell;
import br.edu.ifce.ppd.corba.interfaces.bell.BellHelper;
import br.edu.ifce.ppd.corba.services.WatchmanService;
import br.edu.ifce.ppd.corba.view.WatchmanView;
import org.omg.CORBA.ORB;
import org.omg.CORBA.UserException;

import javax.swing.*;

import java.rmi.RemoteException;

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
                    "\n\n ---- Inicialize o serviço de nomes e o Sino e tente novamente ----", "Erro", ERROR_MESSAGE);
        }
    }

    private static void startApp(String[] args) throws UserException {
        ORB orb = ORB.init(args, null);

        Client client = new Client(orb);
        Bell bell = BellHelper.narrow(client.resolve(new String[]{"SERVICES", "BELL", "REF"}));

        WatchmanView watchmanView = new WatchmanView(bell);
        WatchmanService watchmanService = new WatchmanService(watchmanView);

        Server server = new Server(orb);
        server.register(watchmanService, new String[]{"SERVICES", "WATCHMAN"}, "REF");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                server.deregister(new String[]{"SERVICES", "WATCHMAN", "REF"});
            } catch (UserException e) {
                e.printStackTrace();
            }
        }));


        watchmanView.start();
        server.start();
    }
}
