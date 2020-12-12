package pleilist.app.facade.dto.video;


public class CodigoVideo {
	
	private static CodigoVideo INSTANCE = null;
	private int counter = 0;
	
	public static CodigoVideo getInstance() {
		if (INSTANCE == null) { 
			 INSTANCE = new CodigoVideo();
		}
		return INSTANCE;
	}
	
	public int getCodNum () {//Fase 4
		this.counter++;
		return this.counter;
	}
}
