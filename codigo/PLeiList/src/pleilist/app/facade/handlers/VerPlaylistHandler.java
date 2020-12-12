/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.handlers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import exceptions.CodigoPlaylistNotFound404Exception;
import exceptions.TagPlaylistNotFound404Exception;
import exceptions.TagVideoNotFound404Exception;
import exceptions.VideoNotFound404Exception;
import pleilist.app.facade.Sessao;
import pleilist.app.facade.dto.Item;
import pleilist.app.facade.dto.Pair;
import pleilist.app.facade.dto.Playlist;
import pleilist.app.facade.dto.Utilizador;
import pleilist.app.facade.dto.catalogos.CatalogoHashTags;
import pleilist.app.facade.dto.catalogos.CatalogoPlaylist;
import pleilist.app.facade.dto.video.Video;

public class VerPlaylistHandler {
private CatalogoHashTags catHashtag;
private CatalogoPlaylist catPlaylist;
private Playlist p;
private Utilizador ut;
private Item atual;
private Sessao s;

	public VerPlaylistHandler(Sessao s, CatalogoHashTags catHash, CatalogoPlaylist catPlay) {
		this.s = s;
		this.catHashtag = catHash;
		this.catPlaylist = catPlay;
		this.ut = s.getUtilizador();
		this.atual = new Item();
	}

	/**
	 * Metodo que permite procurar por playlists que possuem a tag dada
	 * @param tag
	 * @return uma lista de playlists
	 */
	public List<Playlist> procurarPorTag(String tag) {
		
		List<Playlist> ls = null;
		
		try {
			return ls = this.catHashtag.getPlaylists(tag);
		}
		catch(TagPlaylistNotFound404Exception e) {
				e.printStackTrace();
		}
		return ls;
	}

	/**
	 * Metodo devolve a duracao duma playlist a partir do seu codigo
	 * @param codigo codigo da playlist
	 * @return duracao
	 * @throws Exception 
	 */
	public Duration escolhePlaylist(String codigo) throws CodigoPlaylistNotFound404Exception {
		try {
			this.p = catPlaylist.getPlay(codigo);
		} catch (CodigoPlaylistNotFound404Exception e) {
			e.printStackTrace();
		}
		return catPlaylist.getDuracao(codigo);			//1.
	}

	/**
	 * Metodo para saber qual o proximo video da playlist
	 * @return um Pair<endereco url, duracao> do proximo video
	 * @throws Exception
	 */
	public Pair<String, Duration> verProximo() {
		Pair<String, Duration> pr = null;
		try {
			this.atual = p.getNextItem(this.atual);
			String end = this.atual.getVideo().getEndereco();
			Duration d = this.atual.getDuracao();
			
			if (end != null) {
				return pr = new Pair<String, Duration>(end, d);
			}
		} catch (VideoNotFound404Exception e) {
			e.printStackTrace();
		}
		return pr;
	
	}
	

	/**
	 * Metodo que permite a classificacao do video corrente
	 * @param estrelas valor inteiro
	 */
	public void classificar(int estrelas) {
		Video vCorrente = this.atual.getVideo();
		vCorrente.setNumVisualizacoes();
		this.ut.classificaVideo(estrelas, vCorrente);
	}

}
