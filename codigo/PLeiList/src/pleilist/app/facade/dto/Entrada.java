/**
 * Projecto DCO - 2018/19
 * @authors Guilherme Teixeira 49021, Ines Goncalves 49493, Rita Carreira 50233 
 */

package pleilist.app.facade.dto;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe existe apenas para a camada dos handlers falar com os clientes. Não é a classe item do modelo de domínio!!!
 *
 */
public class Entrada {

	private String codigo;
	private String titulo;
	private String endereco;
	private TipoPrivacidade privacidade;
	private int numVisualizacoes; 
	private List<String> listaTags = new ArrayList<>();
	private Duration duracao;
	private long inicio;
	private long fim;
    
	/**
	 * Construtor
	 */
    public Entrada(String titulo, String endereco, String codigo, TipoPrivacidade privacidade, List<String> tags, Duration duracao, long inicio) {
        super();    
        this.titulo = titulo;
        this.endereco = endereco;
        this.codigo = codigo;
        this.privacidade = privacidade;
        this.listaTags = tags;
        this.duracao = duracao;
        this.inicio = inicio;
        this.fim = inicio + duracao.toSeconds();
    }

    /**
     * Retorna o titulo dessa entrada
     * @return String com o titulo da entrada
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Retorna o endereco dessa entrada
     * @return String com o endereco da entrada
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Retorna a privacidade dessa entrada
     * @return TipoPrivacidade com a privacidade da entrada
     */
    public TipoPrivacidade getPrivacidade() {
        return privacidade;
    }
    /**
     * Representacao textual de Entrada
     */
    public String toString() {
		return "Titulo: " + titulo + "\nCodigo: " + codigo 
				+ "\nEndereco: " + endereco + "\nPrivacidade: " + privacidade
				+ "\nVisualizacoes: " + numVisualizacoes + "\nDuracao(segundos): " + duracao.toSeconds()
				+ "\nComeca(segundos): " + inicio + "\nTermina(segundos): " + fim;
    }

}
