/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */


package pleilist.app.facade.dto.video;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import adapters.VerificarEnderecoFactory;
import exceptions.EnderecoInvalidoException;
import pleilist.app.facade.dto.Classificacao;
import pleilist.app.facade.dto.HashTag;
import pleilist.app.facade.dto.Item;
import pleilist.app.facade.dto.Playlist;
import pleilist.app.facade.dto.TipoPrivacidade;

public abstract class Video extends Observable{
    
    protected String codigo;
    protected String titulo;
    protected String endereco;
    protected TipoPrivacidade privacidade;
    protected int numVisualizacoes; 
    protected double mediaClassificacoes;
    protected List<HashTag> listaTags = new ArrayList<>();
    protected List<Item> listaItem = new ArrayList<>();
    protected VerificarEnderecoFactory verificador = VerificarEnderecoFactory.getInstance();
    
    /**
	 * Construtor
	 */
    public Video() {
    }
    
    /**
	  * Construtor 
     * @throws Exception 
	  */
    public Video(String codigo, String titulo, String endereco) throws Exception{
    	if(!verificador.verificaEndereco(endereco)) {
    		throw new Exception("nao existe endereco");
    	}
    	this.codigo = codigo;
        this.titulo = titulo;
        this.endereco = endereco;
        this.numVisualizacoes = 0;    
        this.mediaClassificacoes = 0;
    }

    
    /**
	  * Metodo que devolve o codigo do video
	  * @return devolve uma String correspondente ao codigo do video
	  */
	public String getCodigo() {
        return codigo;
    }

	/**
	  * Metodo que altera o codigo do video
	  * @param codigo corresponde ao novo valor do codigo do video
	  */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
	  * Metodo que devolve o titulo do video
	  * @return devolve uma String correspondente ao titulo do video
	  */
    public String getTitulo() {
        return titulo;
    }

    /**
	  * Metodo que altera o titulo do video
	  * @param titulo corresponde ao novo valor do titulo do video
	  */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
	  * Metodo que devolve o endereco url do video
	  * @return endereco url do video em formato String
	  */
    public String getEndereco() {
        return endereco;
    }

    /**
	  * Metodo que altera o endereco do video
	  * @param endereco corresponde ao novo valor do endereco do video
     * @throws Exception 
	  */
    public void setEndereco(String endereco) throws EnderecoInvalidoException {
        if(!verificador.verificaEndereco(endereco)) {
        	throw new EnderecoInvalidoException("Endereco enviado invalido!");
        }
    	this.endereco = endereco;
    }

    /**
	  * Metodo que retorna o tipo de privacidade do video
	  * @return retorna PUBLICO se o tipo de privacidade for publica ou PRIVADO se o tipo de privacidade for privada
	  */
    public TipoPrivacidade getPrivacidade() {
        return privacidade;
    }

    /**
	  * Metodo altera o tipo de privacidade 
	  * @param privacidade corresponde ao novo valor do privacidade do video
	  */
    public void setPrivacidade(TipoPrivacidade privacidade) {
        this.privacidade = privacidade;
    }

    /**
	  * Metodo que devolve o numero de visualizacoes do video
	  * @return valor inteiro correspondente ao numero de visualizacoes do video
	  */
    public int getNumVisualizacoes() {
        return numVisualizacoes;
    }

    /**
	  * Metodo que incrementa o numero de visualizacoes
	  */
    public void setNumVisualizacoes() {
        this.numVisualizacoes++;
    }

    /**
	  * Metodo que devolve uma lista de HashTag associada ao video
	  * @return lista de HashTag
	  */
    public List<HashTag> getListaTags() {
        return this.listaTags;
    }
    /**
	  * Metodo que devolve uma lista de Item, associados ao video
	  * @return lista de Item
	  */
    public List<Item> getListaItem(){
    	return this.listaItem;
    }
    /**
	  * Metodo que altera a lista de HashTag
	  * @param listaTags corresponde ah nova lista de HashTag
	  */
    public void setListaTags(List<HashTag> listaTags) {
        this.listaTags = listaTags;
    }

    /**
	  * Metodo que verifica e adiciona uma HashTag na lista de HashTags associada ao video
	  * @param tag
	  */
    public void adicionaTag (HashTag tag) {
		if(!this.listaTags.contains(tag)) {
    		this.listaTags.add(tag);
    	}
    }
    /**
	  * Metodo que cria uma instancia de Item e adiciona o mesmo ah lista de Item da playlist corrente
	  * @param p playlist corrente
	  * @return retorna o novo Item criado
	  */
	public abstract Item criarItem(Playlist p);
	
	/**
	  * Metodo que altera a media de classificacoes do video
	  * @param classificacao
	  */
	public void setClassificacao(Classificacao classificacao) {
		int valor = classificacao.getValorClassif();
		this.mediaClassificacoes += valor;
		this.mediaClassificacoes /= this.numVisualizacoes;
		notifyObservers();
	}
	
	public double getClassificacao() {
		return this.mediaClassificacoes;
	}
   
	
	public boolean melhor(Video v) {
		return this.mediaClassificacoes >= v.getClassificacao();
	}
}