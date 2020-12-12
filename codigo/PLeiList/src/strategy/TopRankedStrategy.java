package strategy;

import java.util.ArrayList;
import java.util.List;

import pleilist.app.facade.dto.Curador;
import pleilist.app.facade.dto.biblioteca.BibliotecaPublica;
import pleilist.app.facade.dto.video.Video;

public class TopRankedStrategy implements ICreatePlaylistStrategy{

	@Override
	public List<Video> escolherVideos(int q, Curador c, BibliotecaPublica bp){
		List<Video> ret = new ArrayList<>();
		List<Video> listaVideo = c.videosDisponiveis(bp.getListVideo());
		for(int i = 0; i < q && !listaVideo.isEmpty(); i++) {
			Video v = listaVideo.get(0);
			for(int g = 1; g < listaVideo.size() - 1; g++) {
				if(!v.melhor(listaVideo.get(g))){
					v = listaVideo.get(g);
				}
			}
			ret.add(v);
			listaVideo.remove(v);
		}
		return ret;
	}

}
