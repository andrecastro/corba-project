package br.edu.ifce.corba.main;

import br.edu.ifce.corba.services.BellService;
import br.edu.ifce.corba.view.BellView;
import org.omg.CORBA.UserException;

import javax.swing.*;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * Created by andrecoelho on 4/17/16.
 */
public class App {

    public static void main(String... args) throws InterruptedException, UserException {
        try {
            startApp(args);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage() +
                    "\n\n ---- Inicialize o serviço de nomes e tente novamente ----", "Erro", ERROR_MESSAGE);
        }
    }

    private static void startApp(String[] args) throws UserException {
        BellView bellView = new BellView();
        BellService bellService  = new BellService(bellView);

        bellService.stopDing();

        Server server = new Server(args);
        server.register(bellService, new String[] { "SERVICES", "BELL" }, "REF");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                server.deregister(new String[]{"SERVICES", "BELL", "REF"});
            } catch (UserException e) {
                e.printStackTrace();
            }
        }));

        bellView.start();
        server.start();
    }
}
