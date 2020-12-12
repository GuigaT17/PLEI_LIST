/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.dto;

import java.util.ArrayList;
import java.util.List;

import pleilist.app.facade.dto.video.Video;

public class Utilizador{
	
	protected String username;
	protected String password;
	protected List<Classificacao> classificacao;
	
	/**
	 * Construtor
	 * @param username username do utilizador
	 * @param password password do utilizador
	 */
	public Utilizador(String username, String password) {
		this.classificacao = new ArrayList<>();
		this.username = username;
		this.password = password;
	}
	/**
	 * Metodo que devolve o username do utilizador
	 * @return uma String correspondente ao username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Metodo que altera o username do utilizador
	 * @param username corresponde ao novo username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Metodo que devolve a password do utilizador
	 * @return uma String que corresponde ah password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Metodo que altera a password do utilizador
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Metodo que realiza a classificacao do video corrente e altera a media das classificacoes do video
	 * @param estrelas valor inteiro 
	 * @param vCorrente video corrente
	 */
	public void classificaVideo(int estrelas, Video vCorrente) {
		Classificacao c = new Classificacao(estrelas, vCorrente, this);
		this.classificacao.add(c);
		vCorrente.setClassificacao(c);
	}
	
	
}