package pleilist.app.facade.dto;

import pleilist.app.facade.dto.video.Observable;

public interface Observer {
	void update(Observable o);
}
