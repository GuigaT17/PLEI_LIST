package pleilist.main;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import exceptions.CodigoPlaylistNotFound404Exception;
import pleilist.app.PleiList;
import pleilist.app.facade.Sessao;
import pleilist.app.facade.dto.Entrada;
import pleilist.app.facade.dto.Item;
import pleilist.app.facade.dto.Pair;
import pleilist.app.facade.dto.Playlist;
import pleilist.app.facade.dto.catalogos.CatalogoPlaylist;
import pleilist.app.facade.dto.video.Stream;
import pleilist.app.facade.handlers.AdicionarVideoHandler;
import pleilist.app.facade.handlers.CriarPlayListHandler;
import pleilist.app.facade.handlers.CriarPlaylistAutomaticoHandler;
import pleilist.app.facade.handlers.RegistarUtilizadorHandler;
import pleilist.app.facade.handlers.VerPlaylistHandler;
import strategy.ICreatePlaylistStrategy;

public class ClienteExemplo {
	public static void main(String[] args) {
		PleiList p = new PleiList();
		
		RegistarUtilizadorHandler regHandler = p.getRegistarUtilizadorHandler();
		
		regHandler.registarUtilizador("Felismina", "hortadafcul");
		regHandler.registarCurador("Silvino", "bardoc2");
		regHandler.registarCurador("Maribel", "torredotombo");
		
		Optional<Sessao> talvezSessao = p.autenticar("Silvino", "bardoc2");
		
		talvezSessao.ifPresent( (Sessao s) -> {
			
			AdicionarVideoHandler avh = p.getAdicionarVideoHandler(s);
			avh.iniciarAdicionar();
			avh.definirComoClip(true); // Vamos dizer que é um clip.
			avh.indicaVideo("OOP in 7 Minutes", "https://www.youtube.com/watch?v=pTB0EiLXUC8");
			avh.indicaDuracao(Duration.ofSeconds(7 * 60 + 33));
			
			for (String tag : "oop objects polymorphism".split(" ")) {
				avh.indicaTag(tag);
			}
			
			String codigo = avh.defineComoPublico(false);
			System.out.println("O Silvino criou o video " + codigo);
		});
		
		Optional<Sessao> talvezOutraSessao = p.autenticar("Maribel", "torredotombo");
		talvezOutraSessao.ifPresent( (Sessao s) -> {
			
			AdicionarVideoHandler avh = p.getAdicionarVideoHandler(s);
			avh.iniciarAdicionar();
			avh.definirComoClip(false); // Vamos dizer que é um stream.
			avh.indicaVideo("RTP1", "https://www.rtp.pt/play/direto/rtp1");
			// Não indica duração! É um Stream!
			
			for (String tag : "portugues actualidade noticias praca_da_alegria".split(" ")) {
				avh.indicaTag(tag);
			}
			
			String codigo = avh.defineComoPublico(true);
			System.out.println("A Maribel criou o video " + codigo);
		});
		
		talvezSessao.ifPresent( (Sessao s) -> {
			
			CriarPlayListHandler cph = p.getCriarPlayListHandler(s);
			cph.iniciaCriacao("Playlist de Domingo");
			
			for (String tag : "oop portugues".split(" ")) {
				List<Entrada> entradasActuaisDaPlaylist = cph.obterEntradasActuais();
				System.out.println("..........");
				entradasActuaisDaPlaylist.stream().forEach(System.out::println);
				System.out.println("..........");
				
				List<Pair<String, String>> videos = cph.obterVideosComHashtag(tag);
				boolean eStream;
				try {
					eStream = cph.indicarCodigo(videos.get(0).getSecond());
					if (eStream) {
						cph.indicaDuracao(Duration.ofMinutes(30));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			
		});
		
		Optional<Sessao> talvezSessaoUtilizador = p.autenticar("Felismina", "hortadafcul");
		
		talvezSessaoUtilizador.ifPresent( (Sessao s) -> {
			
			VerPlaylistHandler vph = p.getVerPlaylistHandler(s);
			
			List<Playlist> resultados = null;
			
			resultados = vph.procurarPorTag("oop");
			
			try {
				Duration duracaoTotal = vph.escolhePlaylist(resultados.get(0).getCodigo());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
				try {
					Pair<String, Duration> video = vph.verProximo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			vph.classificar(3);
			
		});
		System.out.println("======");
		Optional<Sessao> talvezSessaoCPAH = p.autenticar("Silvino", "bardoc2");
		
		talvezSessaoCPAH.ifPresent( (Sessao s) -> {
			
			CriarPlaylistAutomaticoHandler cpah = p.getCriarPlaylistAutomaticoHandler(s);
			
			List<ICreatePlaylistStrategy> resultados = null;
			
			resultados = cpah.obterListaCriterios("novaPlaylist");
			String cod = cpah.indicaStrategy(resultados.get(1), 4);
			CatalogoPlaylist catPlay = cpah.getCatPlay();
			try {
				Playlist p1 = catPlay.getPlay(cod);
				for(Item i : p1.getListaItem()) {
					if(!((i.getVideo()) instanceof Stream)) {
						System.out.println(i.getVideo().toString());
					}else{
						System.out.println(i.getVideo().toString() + " e duracao: " + i.getDuracao().getSeconds() + " segundos.");
					}
				}
			} catch (CodigoPlaylistNotFound404Exception e) {
				e.printStackTrace();
			}
			
			
		});
		
	}
}
