/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.dto;

import java.util.ArrayList;
import java.util.List;

import pleilist.app.facade.dto.video.Video;

public class HashTag {	
	private String nome;
	private List<Video> listaVideosTag;
	
	/**
	 * Construtor
	 * @param nome = nome da HashTag
	 */
	public HashTag(String nome) {
		this.nome = nome;
		this.listaVideosTag = new ArrayList<>();
	}
	
	/**
	 * Adiciona uma nova relacao entre um video e esta hashtag
	 * @param v = Video
	 */
	public void addVideo(Video v) {
		this.listaVideosTag.add(v);
	}

	/**
	 * Retorna o nome da HashTag
	 * @return String com o nome da HashTag
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Coloca um nome na HashTag
	 * @param nome = novo nome da HashTag
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna a lista de Videos relacionados com essa HashTag
	 * @return Lista de videos relacionados com a hashtag
	 */
	public List<Video> getListaVideosTag() {
		return listaVideosTag;
	}

	/**
	 * Coloca uma lista de videos relacionada com a hashTag
	 * @param listaVideosTag nova lista a relacionar com sua lista de relacoes
	 */
	public void setListaVideosTag(List<Video> listaVideosTag) {
		this.listaVideosTag = listaVideosTag;
	}

	/**
	 * Devolve uma lista de Pair com codigo dos videos relacionados com a HashTag
	 * @return uma lista de Pair Titulo, Código 
	 */
	public List<Pair<String, String>> obterVideos() {
		List<Pair<String, String>> ls = new ArrayList<>();					//1.2.1. obterVideosComHashtag()
		for(Video v : this.listaVideosTag) {								//1.2.2. obterVideosComHashtag()
			String codigoVideo = v.getCodigo();								//1.2.3. obterVideosComHashtag()
			String nomeVideo = v.getTitulo();								//1.2.4. obterVideosComHashtag()
			Pair<String, String> par = new Pair<>(nomeVideo, codigoVideo);	//1.2.5. obterVideosComHashtag()
			ls.add(par);													//1.2.6. obterVideosComHashtag()
		}
		return ls;
	}

	/**
	 * Retorna a lista de Playlist que possui pelo menos um video relacionado com essa hashtag
	 * @return List de Playlist
	 */
	public List<Playlist> getList() {
		List<Playlist> ls = new ArrayList<>();
		for(Video v : this.listaVideosTag) {
			for(Item i : v.getListaItem()) {
				ls.add(i.getPlaylist());
			}
		}
		return ls;
	}
}
