package pleilist.app.facade.handlers;

import java.time.Duration;
import java.util.List;


import pleilist.app.facade.Sessao;
import pleilist.app.facade.dto.Curador;
import pleilist.app.facade.dto.Item;
import pleilist.app.facade.dto.Playlist;
import pleilist.app.facade.dto.biblioteca.BibliotecaPrivada;
import pleilist.app.facade.dto.biblioteca.BibliotecaPublica;
import pleilist.app.facade.dto.catalogos.CatalogoHashTags;
import pleilist.app.facade.dto.catalogos.CatalogoPlaylist;
import pleilist.app.facade.dto.video.CodigoVideo;
import pleilist.app.facade.dto.video.Stream;
import pleilist.app.facade.dto.video.Video;
import strategy.ICreatePlaylistStrategy;
import strategy.RandomVideosStrategy;
import utils.Configuration;


public class CriarPlaylistAutomaticoHandler {
	private Sessao s;
	private BibliotecaPublica bPublica;
	private Curador curador;
	private CatalogoPlaylist catPlaylist;
	private CatalogoHashTags catHashTag;
	private BibliotecaPrivada bPrivada;
	private Playlist corrente;
	private ICreatePlaylistStrategy defaultObject = new RandomVideosStrategy();
	
	private final static Duration DURACAO_DEFAULT_STREAM = Duration.ofSeconds(300);
	
	/*
	 * Construtor
	 */
	public CriarPlaylistAutomaticoHandler(Sessao s, BibliotecaPublica bPub, CatalogoHashTags catHash, CatalogoPlaylist catPlay) {
		this.s = s;
		this.curador = (Curador) s.getUtilizador();
		this.bPrivada = this.curador.getBibliotPrivada();
		this.bPublica = bPub;
		this.catHashTag = catHash;
		this.catPlaylist = catPlay;
	}
	
	/**
	 * Metodo que cria a nova playlist e devolve uma lista das estrategias configuradas para criar uma playlist automatica
	 * @param nomePlaylist = nome da playlist a criar
	 * @return Uma lista de strategy ja configuradas 
	 */
	public List<ICreatePlaylistStrategy> obterListaCriterios (String nomePlaylist){
		int cod = CodigoVideo.getInstance().getCodNum();
		this.corrente = new Playlist(nomePlaylist, cod);
		catPlaylist.addPlaylist(corrente);
		Configuration c = Configuration.getInstance();
		return c.getInstances("strategyCreatePlaylist", defaultObject);
	}
	
	/**
	 * Metodo que adiciona q videos de acordo com a strategy a playlist
	 * @return codigo da playlist
	 */
	public String indicaStrategy(ICreatePlaylistStrategy strategy, int q) {
		List<Video> ls = strategy.escolherVideos(q, curador, bPublica);
		
		for(Video v : ls) {
		
			Item i = v.criarItem(corrente);
			if(v instanceof Stream) {
				i.setDuracao(DURACAO_DEFAULT_STREAM);
			}
			corrente.adicionarEmPlaylist(i);
		}
		return corrente.getCodigo();
	}
	
	public CatalogoPlaylist getCatPlay() {
		return catPlaylist;
	}
}

