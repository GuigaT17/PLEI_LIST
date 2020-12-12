/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */


package pleilist.app.facade.handlers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import exceptions.CodigoVideoNotFound404Exception;
import exceptions.TagVideoNotFound404Exception;
import pleilist.app.facade.Sessao;
import pleilist.app.facade.dto.Curador;
import pleilist.app.facade.dto.Entrada;
import pleilist.app.facade.dto.Item;
import pleilist.app.facade.dto.Pair;
import pleilist.app.facade.dto.Playlist;
import pleilist.app.facade.dto.biblioteca.BibliotecaPublica;
import pleilist.app.facade.dto.catalogos.CatalogoHashTags;
import pleilist.app.facade.dto.catalogos.CatalogoPlaylist;
import pleilist.app.facade.dto.video.Clip;
import pleilist.app.facade.dto.video.Video;

public class CriarPlayListHandler {
	private Sessao s;
	private CatalogoPlaylist catPlaylist;
	private CatalogoHashTags catHashTag;
	private Playlist corrente;
	private Curador curador;
	private BibliotecaPublica bibliPublica;
	private Item atual;
	
	
	/**
	 * Construtor
	 * @param s = sessao; 
	 * @param catPlay = catalogoPlaylist; 
	 * @param catTag = CatalogoHashTags; 
	 * @param bibPub = BibliotecaPublica
	 */
	public CriarPlayListHandler(Sessao s, CatalogoPlaylist catPlay, CatalogoHashTags catTag, BibliotecaPublica bibPub) {
		this.s = s;
		this.catPlaylist = catPlay;
		this.catHashTag = catTag;
		this.bibliPublica = bibPub;
		this.curador = (Curador) s.getUtilizador();
	}

	/**
	 * Inicia a cricao de uma nova Playlist
	 * @param nomePlaylist = nome da Playlist
	 */
	public void iniciaCriacao(String nomePlaylist) {
		this.corrente = catPlaylist.criarPlaylist(nomePlaylist);   			//1. iniciarCricao()
		this.catPlaylist.addPlaylist(this.corrente);
	}

	/**
	 * Retorna as entradas da Playlist
	 * @requires this.corrente != null
	 * @return Lista de Entradas da Playlist
	 */
	public List<Entrada> obterEntradasActuais() {
		return this.corrente.constituicaoPlaylist(); 						//1. obterEntradasActuais()
	}

	/**
	 * Devolve a lista de vídeos com uma determinada hashtag.
	 * @param tag hashTag de vídeos a procurar
	 * @return uma lista de pares Titulo, Código
	 */
	public List<Pair<String, String>> obterVideosComHashtag(String tag) {
		List<Pair<String, String>> ls = new ArrayList<>();
		try {
			return catHashTag.obterVideosComHashtag(tag);					//1. obterVideosComHashtag()
		} catch (TagVideoNotFound404Exception e) {
			e.printStackTrace();
		}						
		return ls;
	}

	/**
	 * Seleciona o vídeo a adicionar com um determinado código.
	 * @param codigo = codigo do video que esta a procurar
	 * @return se o vídeo é uma stream ou não
	 * @throws Exception 
	 */
	public boolean indicarCodigo(String codigo) throws CodigoVideoNotFound404Exception {
		Video v = this.curador.procuraVideo(codigo); 						//1. indicarCodigo()
		if(v == null) {
			v = this.bibliPublica.procuraVideo(codigo);						//1.2. indicarCodigo()
		}
		if(v == null) {
			throw new CodigoVideoNotFound404Exception("Nao existe video com este codigo: " + codigo);
		} else {
			this.atual = v.criarItem(this.corrente);						//2. indicarCodigo()
		}
		if(v instanceof pleilist.app.facade.dto.video.Clip) {
			this.corrente.adicionarEmPlaylist(this.atual);					//3. indicarCodigo()
		}
		return (v instanceof pleilist.app.facade.dto.video.Stream);		
	}

	/**
	 * Indica a duracao da Stream
	 * @requires this.atual != null
	 * @param duration = duracao do Item da Stream
	 */
	public void indicaDuracao(Duration duration) {
		this.atual.setDuracao(duration);									//1.indicaDuracao()
		this.corrente.adicionarEmPlaylist(this.atual);	
	}
	
}
