/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.dto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import exceptions.VideoNotFound404Exception;
import pleilist.app.facade.dto.video.Observable;

public class Playlist implements Observer{
	private String nome;
	private String codigo;
	private double classificacao = 0;
	private List<Item> itens;
	private Item atual;
	
	/**
	 * Construtor
	 * @param nome designacao da playlist
	 * @param q codigo da playlist
	 */
	public Playlist(String nome, int q) {
		super();
		this.nome = nome;
		this.codigo = "" + q;
		this.itens = new ArrayList<Item>();
	}
	
	/**
	 * Construtor
	 * @param nome designacao da playlist
	 * @param q codigo da playlist
	 * @param pontuacao pontuacao da playlist
	 */
	public Playlist(String nome, String codigo, double pontuacao) {
		super();
		this.nome = nome;
		this.codigo = codigo;
		this.classificacao = pontuacao;
		this.itens = new ArrayList<>();
	}
	
	/**
	 * Metodo que devolve o nome da playlist corrente
	 * @return uma String que corresponde ao nome da playlist
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Metodo que devolve o codigo da playlist
	 * @return uma String que corresponde ao codigo da playlist
	 */
	public String getCodigo() {
		return codigo;
	}
	

	
	/**
	 * Metodo que verifica os itens da playlist e para cada item cria uma Entrada, com inicio num determinado instante em segundos
	 * @return uma lista de Entrada 
	 */
	public List<Entrada> constituicaoPlaylist() {
		long atual = 0;
		List<Entrada> ls = new ArrayList<>(); 						//1.1 obterEntradasActuais()
		if(!this.itens.isEmpty()) {
			for(Item item : this.itens) { 							//1.2 obterEntradasActuais()
				Entrada e = item.toEntrada(atual);
				atual += (item.getDuracao().getSeconds());
				ls.add(e);  										//1.3 obterEntradasActuais()
			}	
		}
		return ls;
	}

	/**
	 * Metodo que realiza a adicao de um item na lista de Item da playlist
	 * @param atual item a adicionar
	 */
	public void adicionarEmPlaylist(Item atual) {
		itens.add(atual);											//3.1 indicarCodigo()
		
	}
	/**
	 * Metodo que permite obter a duracao duma playlist em segundos
	 * @return duracao em segundos
	 */
	public Duration getDuracao() {
		long d = 0;
		for(Item i : this.itens) {									//1.2.1. 
			d += i.getDuracao().getSeconds();						//1.2.2.
		}
		return Duration.ofSeconds(d);
	}


	public Item getItemAtual() {
		return this.atual;
	}
	
	/**
	 * Metodo que permite obter o proximo item da lista de Item da playlist corrente
	 * @param atual2 item atual
	 * @return proximo item da playlist
	 * @throws Exception
	 */
	public Item getNextItem(Item atual2) throws VideoNotFound404Exception{
		int index = 0;
		Item lastVideo = this.itens.get(this.itens.size() - 1);
		if(this.itens.isEmpty()) {
			throw new VideoNotFound404Exception("A playlist " + this.nome + " esta vazia");
		}
		if(atual2.equals(lastVideo)) {
			throw new VideoNotFound404Exception("A playlist " + this.nome + " nao tem mais videos");
		}
		if(atual2.getVideo() == null) {
			return this.itens.get(0);
		} else {
			for(Item i : this.itens) {
				if(atual2.equals(this.itens.get(index))) {
					return this.itens.get(index+1);
				}
				index++;
			}
			return null;
		}
		
	}
	
	/**
	 * Metodo que devolve a classificacao da playlist
	 * @return valor decimal que corresponde ah pontuacao da playlist
	 */
	public double getClassificacao() {
		return this.classificacao;
	}

	@Override
	public void update(Observable o) {
		int q = 0;
		for(Item i : this.itens) {
			this.classificacao += i.getVideo().getClassificacao();
			q++;
		}
		this.classificacao /= q;
	}
	
	public List<Item> getListaItem(){
		return itens;
	}
}
