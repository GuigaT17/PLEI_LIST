
/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.dto.video;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import pleilist.app.facade.dto.Item;
import pleilist.app.facade.dto.Playlist;

public class Clip extends Video {



	private Duration duracao;

    
	/**
	 * Construtor sem parametros
	 */
    public Clip () {
    	super();
    }
   
    /**
     * Construtor
     * @param codigo = codigo do clipe
     * @param titulo = titulo do clipe 
     * @param end = endereco do clipe
     * @throws Exception 
     */
    public Clip(String codigo, String titulo, String end) throws Exception {
    	super(codigo, titulo, end);
    }
    
    /**
     * Construtor
     * @param codigo = codigo do clipe
     * @param titulo = titulo do clipe
     * @param endereco = endereco do clipe 
     * @param duracao = duracao do clipe
     * @throws Exception 
     */
    public Clip(String codigo, String titulo, String endereco, Duration duracao) throws Exception {     
        super(codigo, titulo, endereco);
        this.duracao = duracao; 
    }
    
    /**
     * Metodo que atualiza a duracao do video
     * @param d = Duration novo do clipe
     */
    public void setDuration(Duration d) {
    	this.duracao = d;
    }
    
    /**
     * Metodo que pega a duracao do Clipe
     * @return Duration que eh a this.duracao
     */
    public Duration getDuracao() {
        return duracao;
    }
    
    
    @Override
    public Item criarItem(Playlist p) {
		Item i = new Item(this, this.duracao, p);			//2.1 indicarCodigo()
    	this.listaItem.add(i);
    	addObserver(p);
		return i;
    }
	@Override
	public String toString() {
		return "Clip: " + this.getCodigo() + ", com o endereco: " + this.getEndereco() + " e duracao: " + this.duracao.getSeconds() + " segundos.";
	}
}