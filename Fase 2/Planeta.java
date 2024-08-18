
import java.util.List;
import java.util.ArrayList;

/**
 * Os objetos desta classe representam planetas com propriedades.
 * 
 * @author Grupo 36
 * @author Luís Ferreirinha Nº51127
 * @author Rui Pereira Nº51623
 * @author Diogo Ferreira Nº53330
 *
 */
public class Planeta {
	String nome;
	// Lista com as propriedades que o planeta contem
	List<Propriedade> props;

	/**
	 * Inicializa um planeta com um nome dado e com as propriedades dadas na lista
	 * props.
	 * 
	 * @param nome  String nome do planeta
	 * @param props Lista com as propriedades do planeta
	 * @requires nome != null, props != null
	 */
	public Planeta(String nome, List<Propriedade> props) {
		this.nome = nome;
		this.props = new ArrayList<Propriedade>(props);
	}

	/**
	 * Devolve o nome do planeta
	 * 
	 * @return String com o nome do planeta
	 */
	public String nome() {
		return this.nome;
	}

	/**
	 * Verifica se o planeta tem a propriedade p
	 * 
	 * @param p Propriedade a verificar
	 * @return true se o planeta tiver a propriedade
	 */
	public boolean temPropriedade(Propriedade p) {
		return this.props.contains(p);
	}

	/**
	 * Verfica se o planeta tem todas as propriedades referidas na lista props
	 * 
	 * @param props Lista com as propriedades a verificar
	 * @requires props != null
	 * @return true se o planta verificar todas as propriedades
	 */
	public boolean temTodas(List<Propriedade> props) {
		return this.props.containsAll(props);
	}

	/**
	 * Devolve a representacao textual deste planeta
	 * 
	 * @return String contendo a representacao textual do planeta
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.nome + ": ");

		for (Propriedade p : this.props) {
			sb.append(p.name() + " ");
		}

		return sb.toString();
	}
}
