/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.dto;

import java.util.ArrayList;
import java.util.List;

import exceptions.CodigoVideoNotFound404Exception;
import pleilist.app.facade.dto.biblioteca.BibliotecaPrivada;
import pleilist.app.facade.dto.biblioteca.BibliotecaPublica;
import pleilist.app.facade.dto.video.Video;

public class Curador extends Utilizador{
    private BibliotecaPrivada bibliPrivada;
   // private BibliotecaPublica bibliPublica;
    
    /**
     * Construtor
     * @param username
     * @param password
     */
    public Curador(String username, String password) {
        super(username, password);
        this.bibliPrivada = new BibliotecaPrivada();
       // this.bibliPublica = bPublic;
    }
    
    /**
     * Procura um Video na sua bibliotecaPessoal
     * @param codigo = codigo do video a procurar
     * @return Video se encontrar o video null caso contrario
     */
	public Video procuraVideo(String codigo) {
		Video v = this.bibliPrivada.procuraVideo(codigo);	//1.1. indicarCodigo()
		return v;
	}

	/**
	 * Metodo devolve a biblioteca privada de um curador
	 * @return uma BibliotecaPrivada
	 */
	public BibliotecaPrivada getBibliotPrivada() {	
		return this.bibliPrivada;
	}
	
	/*
	 * Metodo que retorna uma lista de todos os videos disponiveis para este curador
	 * @return lista dos videos na biblioteca privada e da bibliotecapublica
	 */
	public List<Video> videosDisponiveis(List<Video> ls){
		ls.addAll(bibliPrivada.getListVideo());
		return ls;
	}
	
	public List<Video> videosRelacionadosCom(Video v, List<Video> listaPublica){
		List<Video> ret = new ArrayList<>();
		List<Video> disp = videosDisponiveis(listaPublica);
		for(int i = 0; i < disp.size(); i++) {
			Video atual = disp.get(i);
			boolean tem = false;
			for(int g = 0; g < atual.getListaTags().size() && !tem; g++) {
				HashTag h = atual.getListaTags().get(g);
				if(v.getListaTags().contains(h)) {
					tem = true;
					ret.add(atual);
				}
			}
		}
		return ret;
	}
}