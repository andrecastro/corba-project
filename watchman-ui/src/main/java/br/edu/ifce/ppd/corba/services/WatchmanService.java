package br.edu.ifce.ppd.corba.services;

import br.edu.ifce.ppd.corba.interfaces.watchman.WatchmanPOA;
import br.edu.ifce.ppd.corba.view.WatchmanView;

/**
 * Created by andrecoelho on 4/16/16.
 */
public class WatchmanService extends WatchmanPOA {

    private Integer numberOfPeopleInTheMuseum;
    private WatchmanView watchmanView;

    public WatchmanService(WatchmanView watchmanView) {
        this.watchmanView = watchmanView;
        this.numberOfPeopleInTheMuseum = 0;
    }

    @Override
    public int getNumberOfPeople() {
        return numberOfPeopleInTheMuseum;
    }

    public void incrementNumberOfPeople() {
        numberOfPeopleInTheMuseum++;
        watchmanView.updateDisplay(numberOfPeopleInTheMuseum);
    }

    public void decrementNumberOfPeople() {
        numberOfPeopleInTheMuseum--;
        watchmanView.updateDisplay(numberOfPeopleInTheMuseum);
    }
}
