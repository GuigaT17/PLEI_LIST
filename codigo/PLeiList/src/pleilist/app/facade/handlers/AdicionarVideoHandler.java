/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.handlers;

import pleilist.app.facade.dto.Curador;
import pleilist.app.facade.dto.Entrada;
import pleilist.app.facade.dto.HashTag;
import pleilist.app.facade.dto.TipoPrivacidade;
import pleilist.app.facade.dto.Utilizador;
import pleilist.app.facade.dto.biblioteca.BibliotecaPrivada;
import pleilist.app.facade.dto.biblioteca.BibliotecaPublica;
import pleilist.app.facade.dto.catalogos.CatalogoHashTags;
import pleilist.app.facade.dto.video.Clip;
import pleilist.app.facade.dto.video.CodigoVideo;
import pleilist.app.facade.dto.video.Stream;
import pleilist.app.facade.dto.video.Video;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import exceptions.EnderecoInvalidoException;
import pleilist.app.facade.Sessao;

public class AdicionarVideoHandler {
	
	private Sessao s;
	private Video v;
	private Curador c;
	private CatalogoHashTags catalogoHTags;  
	private BibliotecaPublica bibpub;   
	private BibliotecaPrivada bibpri;
	
	private CodigoVideo cv = CodigoVideo.getInstance();
	
	public AdicionarVideoHandler(Sessao s, CatalogoHashTags ct, BibliotecaPublica bp) {
		this.s=s;
		this.catalogoHTags = ct;
		this.bibpub = bp;
		this.c = (Curador) this.s.getUtilizador();
		this.bibpri = c.getBibliotPrivada();
		
	}

	public void iniciarAdicionar() {
	  
	}

	/**
	 * Metodo que define o tipo de Video como Clip ou Stream 
	 * @param isClip 
	 */
	public void definirComoClip(boolean isClip) {
	    if(isClip) {
	        this.v = new Clip(); 
	    }else {
	    	this.v = new Stream();
	    }
	}

	/**
	 * Metodo que indica o titulo e o endereco url do video
	 * @param title
	 * @param address endereco url 
	 * @throws Exception 
	 */
	public void indicaVideo(String title, String address) {
		this.v.setTitulo(title);
		try {
			this.v.setEndereco(address);
		} catch (EnderecoInvalidoException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo que indica a duracao dum video do tipo Clip
	 * @param duration
	 */
	public void indicaDuracao(Duration duration) {
		if(v instanceof Clip) {
			((Clip) v).setDuration(duration);
		}
		
	}

	/**
	 * Metodo que permite adicionar uma tag ao video corrente
	 * @param tag
	 */
	public void indicaTag(String tag) {
		HashTag t = catalogoHTags.adicionarTag(tag);
		v.adicionaTag(t);
		t.addVideo(v);
	}

	/**
	 * Termina a adição do vídeo.
	 * @param ePublico indica se o vídeo é publico ou privado
	 * @return Codigo do vídeo criado
	 */
	public String defineComoPublico(boolean ePublico) {	
		int codNum;
		String cod;	
		if (ePublico) {	
			v.setPrivacidade(TipoPrivacidade.PUBLICO);
			//codNum = bibpub.getCodNum();
			codNum = cv.getCodNum();
			v.setCodigo("" + codNum);
			bibpub.adicionaVideo(v);
			return v.getCodigo();	
		} else {	
			v.setPrivacidade(TipoPrivacidade.PRIVADO);
			//codNum = bibpri.getCodNum();
			codNum = cv.getCodNum();
			v.setCodigo("" + codNum);
			this.bibpri.adicionaVideo(v);	
			return v.getCodigo();
		}
	}
}
