/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.dto;

import pleilist.app.facade.dto.video.Video;

public class Classificacao {
	private Utilizador ut;
	private Video video;
	private int valor;
	
	/**
	 * Construtor
	 * @param valor valor inteiro da classificacao atribuida pelo utilizador ao video
	 * @param v video corrente
	 * @param u utilizador
	 */
	public Classificacao(int valor, Video v, Utilizador u) {
		this. ut = u;
		this.valor = valor;
		this.video = v;
	}
	
	/**
	 * Construtor
	 * @param u = Utilizador; 
	 * @param v = Video; 
	 * @param valor = valor da classificacao
	 */
	public Classificacao(int valor) {
		this.valor = valor;
	}
	
	/**
	 * Metodo que devolve o valor da classificacao
	 * @return valor inteiro
	 */
	public int getValorClassif() {
		return this.valor;
	}
	
}
