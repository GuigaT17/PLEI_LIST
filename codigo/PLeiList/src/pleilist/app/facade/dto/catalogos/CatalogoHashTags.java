/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.dto.catalogos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.TagPlaylistNotFound404Exception;
import exceptions.TagVideoNotFound404Exception;
import pleilist.app.facade.dto.HashTag;
import pleilist.app.facade.dto.Pair;
import pleilist.app.facade.dto.Playlist;
import pleilist.app.facade.dto.video.CodigoVideo;

public class CatalogoHashTags {
	
	private Map<String, HashTag> listaHashTags;
	
	/**
	 * Construtor
	 */
	public CatalogoHashTags() {
		listaHashTags = new HashMap<>();
	}

	/**
	 * Adiciona uma nova HashTag ao catalogo, se ja existir devolve a HashTag existente
	 * @param tag = nome da HashTag
	 * @return HashTag adicionada ou existente
	 */
	public HashTag adicionarTag (String tag) {
		HashTag novaTag = new HashTag(tag);
		if (!this.listaHashTags.containsKey(tag)) {
			this.listaHashTags.put(tag, novaTag);
			return novaTag;
		} else {
			return this.listaHashTags.get(tag);
		}
		
	}

	/**
	 * Devolve a lista de vídeos com uma determinada hashtag.
	 * @param tag hashTag de vídeos a procurar
	 * @return uma lista de pares Titulo, Código
	 */
	public List<Pair<String, String>> obterVideosComHashtag(String tag) throws TagVideoNotFound404Exception{
		HashTag h = this.listaHashTags.get(tag);		//1.1 obterVideosComHashtag()
		if(h == null) {
			throw new TagVideoNotFound404Exception("Nao existe esta Hashtag: " + tag);
		}
		return h.obterVideos();							//1.2 obterVideos()
	}

	/**
	 * Metodo que pega as Playlist relacionadas com uma String tag
	 * @param tag = nome da HashTag
	 * @return Lista de Playlist relacionadas com o nome da HashTag = tag
	 */
	public List<Playlist> getPlaylists(String tag) throws TagPlaylistNotFound404Exception{
		List<Playlist> ls = null; 
		HashTag h = this.listaHashTags.get(tag);
		
		if (h == null)
			throw new TagPlaylistNotFound404Exception("Nao existe esta Hashtag: " + h);
		ls = h.getList();
		
		return ls;
	}
	

}
