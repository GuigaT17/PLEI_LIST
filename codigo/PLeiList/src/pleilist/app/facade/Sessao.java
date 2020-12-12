/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade;

import java.util.Optional;

import pleilist.app.facade.dto.Curador;
import pleilist.app.facade.dto.Utilizador;
import pleilist.app.facade.dto.catalogos.CatalogoUtilizadores;

public class Sessao {
	private Optional<Utilizador> utilizador;
	/**
	 * Construtor
	 */
	public Sessao() {
		
	}
	/**
	 * Construtor
	 * @param u Utilizador
	 */
	public Sessao(Optional<Utilizador> u) {
		this.utilizador = u;
	}
	/**
	 * Metodo que devolve o utilizador da sessao corrente 
	 * @return um Utilizador
	 */
	public Utilizador getUtilizador() {
		return this.utilizador.get();
	}

}
