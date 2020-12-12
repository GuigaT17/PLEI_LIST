/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.dto.video;

import java.util.ArrayList;
import java.util.List;

import pleilist.app.facade.dto.Item;
import pleilist.app.facade.dto.Playlist;

public class Stream extends Video{

	
	
	/**
	 * Construtor sem parametros
	 */
	public Stream() {
		super();
	}
	
	/**
	 * Construtor
	 * @param codigo = codigo da Stream, 
	 * @param titulo = titulo da Stream, 
	 * @param endereco = endereco da Stream
	 * @throws Exception 
	 */
    public Stream (String codigo, String titulo, String endereco) throws Exception {
        super(codigo, titulo, endereco);
    }


	@Override
	public Item criarItem(Playlist p) {
		Item i = new Item(this, p);
		this.listaItem.add(i);
		addObserver(p);
		return i;
	}
	
	@Override
	public String toString() {
		return "Stream: " + this.getCodigo() + ", com o endereco: " + this.getEndereco();
	}

}