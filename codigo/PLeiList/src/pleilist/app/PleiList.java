package pleilist.app;

import java.util.Optional;

import pleilist.app.facade.Sessao;
import pleilist.app.facade.dto.Utilizador;
import pleilist.app.facade.dto.biblioteca.BibliotecaPublica;
import pleilist.app.facade.dto.catalogos.CatalogoHashTags;
import pleilist.app.facade.dto.catalogos.CatalogoPlaylist;
import pleilist.app.facade.dto.catalogos.CatalogoUtilizadores;
import pleilist.app.facade.handlers.AdicionarVideoHandler;
import pleilist.app.facade.handlers.CriarPlayListHandler;
import pleilist.app.facade.handlers.CriarPlaylistAutomaticoHandler;
import pleilist.app.facade.handlers.RegistarUtilizadorHandler;
import pleilist.app.facade.handlers.VerPlaylistHandler;

/**
 * Esta é a classe do sistema.
 */
public class PleiList {
	private CatalogoUtilizadores catUti;
	private CatalogoHashTags catalogTags;
	private CatalogoPlaylist catalogPlaylist;
	private BibliotecaPublica bibpub;
	
	public PleiList() {
		this.catUti = new CatalogoUtilizadores();
		this.catalogTags = new CatalogoHashTags();
		this.catalogPlaylist = new CatalogoPlaylist();
		this.bibpub = new BibliotecaPublica();
	}
	
	public RegistarUtilizadorHandler getRegistarUtilizadorHandler() {
		return new RegistarUtilizadorHandler(this.catUti);
	}
	
	/**
	 * Returns an optional Session representing the authenticated user.
	 * @param username
	 * @param password
	 * @return
	 */
	public Optional<Sessao> autenticar(String username, String password) {
		if(this.catUti.verificarExistencia(username, password)) {
			Optional<Utilizador> u = catUti.getUtilizador(username, password);
			return Optional.of(new Sessao(u));
		}
		return Optional.empty(); // TODO Autenticação
	}

	public AdicionarVideoHandler getAdicionarVideoHandler(Sessao s) {
		return new AdicionarVideoHandler(s, this.catalogTags, this.bibpub); // TODO
	}

	public CriarPlayListHandler getCriarPlayListHandler(Sessao s) {
		return new CriarPlayListHandler(s, this.catalogPlaylist, this.catalogTags, this.bibpub); // TODO
	}

	public VerPlaylistHandler getVerPlaylistHandler(Sessao s) {
		return new VerPlaylistHandler(s, this.catalogTags, this.catalogPlaylist); // TODO
	}
	
	public CriarPlaylistAutomaticoHandler getCriarPlaylistAutomaticoHandler(Sessao s) {
		return new CriarPlaylistAutomaticoHandler(s, bibpub, catalogTags, catalogPlaylist);
	}
	
}
