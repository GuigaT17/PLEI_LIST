package pleilist.app.facade.dto.video;

import java.util.ArrayList;
import java.util.List;

import pleilist.app.facade.dto.Observer;

//import observer.Observer;

public abstract class Observable {
	private List<Observer> obs = new ArrayList<>();
	
	public void addObserver(Observer o) {
		obs.add(o);
	}
	
	public void notifyObservers() {
		for (Observer o : obs) {
			o.update(this);
		}
	}
}
