/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.dto.catalogos; 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exceptions.JaExisteUtilizadorException;
import pleilist.app.facade.dto.Curador;
import pleilist.app.facade.dto.Utilizador;
import pleilist.app.facade.dto.video.CodigoVideo;

public class CatalogoUtilizadores {

	private List<Utilizador> listaUtilizadores;
	
	/**
	 * Construtor
	 */
	public CatalogoUtilizadores() {
		listaUtilizadores = new ArrayList<>();
	}
	
	/**
	 * Metodo que verifica a existencia de um utilizador na lista de utilizadores do catalogo
	 * @param username
	 * @param password
	 * @return true, se o username e a password tiverem alguma correspondencia com os valores de
	 * algum utilizador da lista, false c.c.
	 */
	public boolean verificarExistencia (String username, String password){
		for(Utilizador u: listaUtilizadores) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password))
				return true;
		}
		return false;
	}
	
	/**
	 * Metodo que cria um novo utilizador e adiciona-o ah lista de utilizadores do catalogo 
	 * @param username
	 * @param password
	 * @throws Exception sse ja existir um utilizador com esse username
	 */
	public void registaUtilizador (String username, String password) throws JaExisteUtilizadorException {
		if(!verificarExistencia(username,password)){
			Utilizador ut = new Utilizador(username, password);
			listaUtilizadores.add(ut);
			return;
		}
		throw new JaExisteUtilizadorException("Ja existe utilizador com esse username");
	}
	
	/**
	 * Metodo que devolve um utilizador com username e senha, caso haja alguma correspondencia com outro
	 * utilizador da lista de utilizadores
	 * @param username
	 * @param senha password
	 * @return um Utilizador 
	 */
	public Optional<Utilizador> getUtilizador(String username, String senha) {
		for(Utilizador u : this.listaUtilizadores) {
			if(u.getPassword().equals(senha) && u.getUsername().equals(username)) {
				return Optional.of(u);
			}
		}
		return Optional.empty();
	}
	
	/**
	 * Metodo que cria um novo curador e adiciona-o ah lista de utilizadores do catalogo 
	 * @param string = username
	 * @param string2 = password
	 * @throws Exception sse ja existir um curador com esse username
	 */
	public void registraCurador(String string, String string2) throws JaExisteUtilizadorException {
		if(!verificarExistencia(string, string2)) {
			Curador c = new Curador(string, string2);
			listaUtilizadores.add(c);
			return;
		}
		throw new JaExisteUtilizadorException("Ja existe um curador com esse username");
		
	}
}
