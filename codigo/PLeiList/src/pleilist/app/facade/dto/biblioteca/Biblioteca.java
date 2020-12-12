/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.dto.biblioteca;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.CodigoVideoNotFound404Exception;
import pleilist.app.facade.dto.video.CodigoVideo;
import pleilist.app.facade.dto.video.Video;

public abstract class Biblioteca {
	protected Map<String, Video> videos;
	//protected static int counter = 1;  Fase 3

	
	/**
	 * Construtor
	 */
	public Biblioteca () {  
		this.videos = new HashMap<>();
	}
	
	/**
	 * Metodo que adiciona um video ao HashMap da biblioteca<Codigo, Video>
	 * @param v = Video a adicionar
	 */
	public void adicionaVideo(Video v) {
		this.videos.put(v.getCodigo(), v);	
	}
	
	/**
	 * Retorna um video com um codigo da biblioteca, null se nao tiver o video
	 * @param codigo = codigo do video a procurar
	 * @return Video sse existir um video no seu Hashmap com key = codigo, null caso contrario
	 */
	public Video procuraVideo(String codigo) {
		Video v = this.videos.get(codigo);
		return v;				//1.1.1. indicarCodigo() //1.2.1 indicarCodigo()
	}
	
	/*
	 * Pega os valores do map, e transforma-o em uma lista
	 * @return uma lista com os values do map.videos
	 */
	public List<Video> getListVideo(){
		Collection c = this.videos.values();
		List<Video> ls = new ArrayList<>();
		ls.addAll(c);
		return ls;
	}
}
