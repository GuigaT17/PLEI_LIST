package strategy;

import java.util.List;


import pleilist.app.facade.dto.Curador;
import pleilist.app.facade.dto.biblioteca.BibliotecaPublica;
import pleilist.app.facade.dto.video.Video;

public interface ICreatePlaylistStrategy {
	
	/*
	 * Metodo que escolhe q videos dada uma lista de videos privadas e a biblioteca publica
	 * @param q = numero de videos a escolher
	 * @param listaPriv = lista de videos disponiveis na biblioteca privada do curador
	 * @return Lista de videos escolhidos 
	 */
	public List<Video> escolherVideos(int q, Curador c, BibliotecaPublica bp) ;
}
