
import java.util.List;
import java.util.ArrayList;

/**
 * Os objetos desta classe representam sistemas solares formados por planetas.
 * 
 * @author Grupo 36
 * @author Luís Ferreirinha Nº51127
 * @author Rui Pereira Nº51623
 * @author Diogo Ferreira Nº53330
 *
 */
public class SistemaSolar {
	String nome;
	// Matriz com os planetas que definem o SistemaSolar
	Planeta[][] planeta;

	/**
	 * Inicializa um Sistema Solar com um nome dado e uma matriz de planetas.
	 * 
	 * @param nome    Uma String que indica o nome do Sistema Solar
	 * @param planeta Uma Matriz de objetos Planeta que vao definir o Sistema Solar
	 * @requires universoValido(planetas), nome != null
	 */
	public SistemaSolar(String nome, Planeta[][] planeta) {
		this.nome = nome;
		this.planeta = new Planeta[planeta.length][planeta[0].length];

		for (int i = 0; i < planeta.length; i++) {
			for (int j = 0; j < planeta[i].length; j++) {
				this.planeta[i][j] = planeta[i][j];
			}
		}
	}

	/**
	 * Verifica se um array bi-dimensional de instancias de planeta tem a forma
	 * correta para inicializar uma instancia de SistemaSolar.
	 * 
	 * @param arrayBi Array Bi-dimensional contendo instancias de Planeta
	 * @requires arrayBi != null
	 * @return true se o array bi-dimensional eh uma matriz e nao contem elementos a
	 *         null.
	 */
	public static boolean universoValido(Planeta[][] arrayBi) {

		int numeroColunas = arrayBi[0].length;
		boolean valido = true;

		for (int i = 0; i < arrayBi.length && valido; i++) {
			if (numeroColunas != arrayBi[i].length) {
				valido = false;
			}
			for (int j = 0; j < numeroColunas && valido; j++) {
				valido = arrayBi[i][j] != null;
			}
		}

		return valido;
	}

	/**
	 * Devolve o nome deste Sistema Solar
	 * 
	 * @return Uma String que representa o nome desta instancia Sistema Solar.
	 */
	public String nome() {
		return this.nome;
	}

	/**
	 * Verifica se o Sistema Solar tem o planeta com o nome dado
	 * 
	 * @param nome String nome do planeta a procurar
	 * @requires nome != null
	 * @return true se o sistema solar contem o planeta com o nome dado
	 */
	public boolean temPlaneta(String nome) {

		boolean planetaEncontrado = false;

		for (int i = 0; i < this.planeta.length && !planetaEncontrado; i++) {
			for (int j = 0; j < this.planeta[i].length && !planetaEncontrado; j++) {
				planetaEncontrado = nome.equals(this.planeta[i][j].nome);
			}
		}

		return planetaEncontrado;
	}

	/**
	 * Devolve os planetas que contem todas as propriedades referidadas na lista
	 * props.
	 * 
	 * @param props Lista de String onde cada elemento eh uma propriedade que se
	 *              pretende procurar nos planetas.
	 * @requires props != null
	 * @return Lista de Strings onde cada elemento eh o nome de um planeta que
	 *         contem todas as propriedades referidas na lista props
	 */
	public List<String> comPropriedades(List<Propriedade> props) {

		List<String> planetasComProps = new ArrayList<String>();

		for (Planeta[] arrayPlanetas : this.planeta) {
			for (Planeta p : arrayPlanetas) {
				if (p.temTodas(props)) {
					planetasComProps.add(p.nome);
				}
			}
		}

		return planetasComProps;
	}

	/**
	 * Devolve um vetor onde o elemento i representa o numero de planetas deste
	 * sistema solar com a propriedade presente no elemento i do vetor
	 * Propriedade.values()
	 * 
	 * @return Int[] Array de inteiros onde o elemento na posicao i do array
	 *         corresponde ao numero de planetas do sistema solar que tem a
	 *         propriedade na posicao i do vetor Propriedade.values()
	 */
	public int[] quantosPorPropriedade() {

		int[] numeroPropriedades = new int[Propriedade.values().length];

		for (Planeta[] arrayPlanetas : this.planeta) {
			for (Planeta p : arrayPlanetas) {
				for (Propriedade prop : Propriedade.values()) {
					if (p.temPropriedade(prop)) {
						numeroPropriedades[prop.ordinal()] += 1;
					}
				}
			}
		}

		return numeroPropriedades;
	}

	/**
	 * Mapeia a ordem de um planeta para as coordenadas na matriz planetas, quando
	 * esta eh percorrida na direcao de uma lagarta horizontal que volta ao inicio
	 * quando termina.
	 * 
	 * @param ordemPlaneta  Int com a ordem do planeta que se pretende encontrar na
	 *                      matriz
	 * @param numeroLinhas  Int com o numero de linhas da matriz
	 * @param numeroColunas Int com o numero de colunas da matriz
	 * @requires ordemPlaneta >= 0, numeroLinhas > 0, numeroColunas > 0
	 * @return Array onde o primeiro elemento eh a linha e o segundo a coluna onde o
	 *         planeta se encontra na matriz Planeta.
	 */
	private int[] coordenadasMatriz(int ordemPlaneta, int numeroLinhas, int numeroColunas) {

		// pos = {indice linha, indice coluna}
		int[] pos = new int[2];

		pos[0] = ((ordemPlaneta / numeroColunas) + numeroLinhas) % numeroLinhas;
		pos[1] = ordemPlaneta % numeroColunas;

		// Se o numero da linha for impar entao o sentido em que a linha eh percorrida
		// eh ao contrario
		if (pos[0] % 2 != 0) {
			pos[1] = (numeroColunas - 1) - pos[1];
		}

		return pos;
	}

	/**
	 * Procura o n-ehsimo planeta na matriz planeta, quando percorrida na direcao de
	 * uma lagarta na horizontal que volta ao inicio quando chega ao fim, e verifica
	 * se esse planeta tem as condicoes referidas na lista props.
	 * 
	 * @param n     Ordem do planeta que se pretende verificar
	 * @param props Lista de propriedades a verificar
	 * @requires n >= 0, props != null
	 * @return true se o planeta tiver todas as propriedades referidas.
	 */
	public boolean nEsimoTem(int n, List<Propriedade> props) {
		int[] pos = coordenadasMatriz(n, this.planeta.length, this.planeta[0].length);

		return this.planeta[pos[0]][pos[1]].temTodas(props);
	}

	/**
	 * Encontra e devolve a propriedade mais frequente neste sistema solar.
	 * 
	 * @return O elemento do enumerado Propriedade mais frequente entre planetas
	 *         neste sistema solar.
	 */
	public Propriedade maisFrequente() {

		// Vamos determinar o indice da propriedade mais frequente para depois
		// a pudermos devolvermos
		int[] nPorPropriedade = quantosPorPropriedade();
		int maxOcorrencias = nPorPropriedade[0];
		int indice = 0;

		for (int i = 0; i < nPorPropriedade.length; i++) {
			if (nPorPropriedade[i] > maxOcorrencias) {
				maxOcorrencias = nPorPropriedade[i];
				indice = i;
			}
		}

		return Propriedade.values()[indice];
	}

	/**
	 * Devolve a representacao textual desta instancia de Sistema Solar
	 * 
	 * @return String contento a representacao textual do Sistema solar
	 */
	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append(this.nome + "\n");
		for (Planeta[] arrayPlanetas : this.planeta) {
			for (Planeta p : arrayPlanetas) {
				sb.append(p.toString());
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}
