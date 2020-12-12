/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.handlers;

import exceptions.JaExisteUtilizadorException;
import pleilist.app.facade.dto.Curador;
import pleilist.app.facade.dto.catalogos.CatalogoUtilizadores;

public class RegistarUtilizadorHandler {
	
    private CatalogoUtilizadores catUtil;  
    
    public RegistarUtilizadorHandler(CatalogoUtilizadores catUti) {
        this.catUtil = catUti;  
    }
    
	/**
	 * Regista um utilizador normal.
	 * @param Username
	 * @param Password
	 * @ensures existe um utilizador com esse username
	 */
	public void registarUtilizador(String username, String password) {
	    try {
			catUtil.registaUtilizador(username, password);
		} catch (JaExisteUtilizadorException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Regista um utilizador com nível de curador.
	 * @param Username
	 * @param Password
	 * @ensures existe um utilizador com esse username, com nível de curador
	 */
	public void registarCurador(String string, String string2) {
	    try {
			catUtil.registraCurador(string, string2);
		} catch (JaExisteUtilizadorException e) {
			e.printStackTrace();
		}
	}
}
