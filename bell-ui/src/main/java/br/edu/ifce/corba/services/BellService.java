package br.edu.ifce.corba.services;

import br.edu.ifce.corba.view.BellView;
import br.edu.ifce.ppd.corba.interfaces.bell.BellPOA;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.IOException;

import static br.edu.ifce.corba.helper.Assets.alarmPath;
import static sun.audio.AudioPlayer.player;

/**
 * Created by andrecoelho on 4/16/16.
 */
public class BellService extends BellPOA {

    private Boolean running = false;
    private AudioStream alarm;
    private Thread alarmThread;

    private BellView bellView;

    public BellService(BellView bellView) {
        this.bellView = bellView;
    }

    @Override
    public void startDing() {
        running = true;
        alarmThread = new Thread(() -> {
            try {
                startAlarm();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                System.out.println("Alarm stopped");
            }
        });

        alarmThread.start();
        bellView.startDing();
    }

    @Override
    public void stopDing() {
        if (running) {
            running = false;
            alarmThread.interrupt();
            player.stop(alarm);
            bellView.stopDinging();
        }
    }

    private void startAlarm() throws IOException, InterruptedException {
        do {
            alarm = new AudioStream(new FileInputStream(alarmPath()));
            player.start(alarm);
            Thread.sleep(23000);
        } while (running);
    }
}
