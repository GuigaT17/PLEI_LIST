package strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pleilist.app.facade.dto.Curador;
import pleilist.app.facade.dto.biblioteca.BibliotecaPublica;
import pleilist.app.facade.dto.video.Video;

public class ChainedVideosStrategy implements ICreatePlaylistStrategy{
	private Random r = new Random();
	
	@Override
	public List<Video> escolherVideos(int q, Curador c, BibliotecaPublica bp){
		List<Video> ret = new ArrayList<>();
		List<Video> listaVideo = c.videosDisponiveis(bp.getListVideo());
		if(!listaVideo.isEmpty()) {
			Video v = listaVideo.get(r.nextInt(listaVideo.size()));
			ret.add(v);
			listaVideo.remove(v);
		}
		for(int i = 1; i < q && !listaVideo.isEmpty(); i++) {
			listaVideo = c.videosRelacionadosCom(ret.get(i - 1), bp.getListVideo());
			if(listaVideo.isEmpty()) {
				return ret;
			}
			Video v = listaVideo.get(r.nextInt(listaVideo.size()));
			ret.add(v);
			listaVideo.remove(v);
		}
		return ret;
	}

}
