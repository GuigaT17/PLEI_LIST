/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */
package pleilist.app.facade.dto.catalogos;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.CodigoPlaylistNotFound404Exception;
import pleilist.app.facade.dto.Playlist;
import pleilist.app.facade.dto.video.CodigoVideo;

public class CatalogoPlaylist {
	private int count;
	private Map<String, Playlist> playlists;

	/**
	 * Construtor
	 */
	public CatalogoPlaylist() {
		this.count=0;
		this.playlists = new HashMap<>();
	}

	/**
	 * Cria uma nova Playlist com o nome e um novo codigo
	 * @param nome = nome da Playlist
	 * @return Playlist criada com nome e um codigo gerado
	 */
	public Playlist criarPlaylist(String nome) {
		this.count++;
		return new Playlist(nome, count);   //1.1 iniciarCricao()
	}
	
	/**
	 * Metodo que permite adicionar uma playlist ao catalogo de playlists
	 * @param corrente playlist corrente
	 */
	public void addPlaylist(Playlist corrente) {
		this.playlists.put(corrente.getCodigo(), corrente);

	}

	/**
	 * Metodo que devolve a duracao duma playlist 
	 * @param codigo codigo da playlist 
	 * @return duracao em segundos
	 * @throws Exception 
	 */
	public Duration getDuracao(String codigo) throws CodigoPlaylistNotFound404Exception {
		Playlist p = this.playlists.get(codigo);		//1.1.
		if(p == null) {
			throw new CodigoPlaylistNotFound404Exception("Nao existem playlist com este codigo: " + codigo);
		}
		return p.getDuracao();							//1.2.
	}

	/**
	 * Metodo que devolve uma playlist 
	 * @param codigo codigo da playlist
	 * @return uma playlist
	 * @throws Exception 
	 */
	public Playlist getPlay(String codigo) throws CodigoPlaylistNotFound404Exception {

		Playlist res = null;
		Collection <Playlist> cp = playlists.values();
		if (cp != null) {
			for(Playlist p : cp) {
				if(p.getCodigo().equals(codigo))
					res=p;
			}
		}
		if(res == null) {
			throw new CodigoPlaylistNotFound404Exception("Nao existem playlist com este codigo: " + codigo);
		}
		return res;
	}



}
