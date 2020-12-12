/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.dto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import pleilist.app.facade.dto.video.Video;

public class Item {
	private Video video;
	private Duration duracao;
	private Playlist playlist;
	
	/**
	 * Construtor
	 * @param v = Video; 
	 * @param p = Playlist
	 */
	public Item(Video v, Playlist p) {
		super();
		this.video = v;
		this.playlist = p;
	}
	/**
	 * Construtor
	 * @param v = Video; 
	 * @param d + duracao; 
	 * @param p = Playlist
	 */
	public Item(Video v, Duration d, Playlist p) {
		super();
		this.video = v;
		this.duracao = d;
		this.playlist = p;
	}

	public Item() {

	}
	/**
	 * Metodo que cria uma nova entrada com dados do Item
	 * @param inicio = segundos em que a Entrada comeca na sua Playlist
	 * @return Entrada com dados do Item e seu inicio em segundos na sua Playlist
	 */
	public Entrada toEntrada(long inicio) {
		String titulo = this.video.getTitulo();
		String end = this.video.getEndereco();
		String cod = this.video.getCodigo();
		TipoPrivacidade priv = this.video.getPrivacidade();
		List<String> tags = new ArrayList<>();
		for(HashTag h : this.video.getListaTags()) {
			tags.add(h.getNome());
		}
		return new Entrada(titulo, end, cod, priv, tags, this.duracao, inicio);
	}
	
	/**
	 * Metodo que devolve a duracao do Item
	 * @return Duration com duracao do Item
	 */
	public Duration getDuracao() {
		return this.duracao;
	}
	
	/**
	 * Metodo que adiciona duracao ao item
	 * @param duration = Duration a colocar ao item
	 */
	public void setDuracao(Duration duration) {
		this.duracao = duration;									//1.1. indicaDuracao()
	}
	
	/**
	 * Metodo que pega a Playlist que o item faz parte
	 * @return Playlist do item
	 */
	public Playlist getPlaylist() {
		return this.playlist;
	}
	
	/**
	 * Metodo que pega o Video que o item faz parte
	 * @return Video do item
	 */
	public Video getVideo() {
		return this.video;
	}

}
