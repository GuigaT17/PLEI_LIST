package strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pleilist.app.facade.dto.Curador;
import pleilist.app.facade.dto.biblioteca.BibliotecaPrivada;
import pleilist.app.facade.dto.biblioteca.BibliotecaPublica;
import pleilist.app.facade.dto.video.Video;

public class RandomVideosStrategy implements ICreatePlaylistStrategy {
	private Random r = new Random();
	
	@Override
	public List<Video> escolherVideos(int q, Curador c, BibliotecaPublica bp){
		List<Video> listaVideos = c.videosDisponiveis(bp.getListVideo());
		List<Video> listaReturn = new ArrayList<>();
		for(int i = 0; i < q && !listaVideos.isEmpty(); i++) {
			int p = r.nextInt(listaVideos.size());
			listaReturn.add(listaVideos.get(p));
			listaVideos.remove(p);
		}
		return listaReturn;
	}
}
