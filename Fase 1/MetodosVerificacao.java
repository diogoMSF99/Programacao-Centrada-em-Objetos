/**
 * 
 * @author Grupo 36
 * @author Luís Ferreirinha Nº51127
 * @author Rui Pereira Nº51623
 * @author Diogo Ferreira Nº53330
 * 
 * @date Outubro 2021
 *
 */
public class MetodosVerificacao {

	/**
	 * Um dado trajeto, num dado sentido, satisfaz uma dada propriedade?
	 * 
	 * @param trajeto     O trajeto em questao
	 * @param propriedade A propriedade a ser verificada
	 * @param sentido     O sentido a considerar no trajeto para a verificacao
	 * @requires trajeto != null && propriedade != null && sentido in
	 *           {"REGULAR","INVERSO"} && os elementos de trajeto são sequencias de
	 *           caracteristicas da forma caract_1 ,...,caract m propriedade e’ da
	 *           forma k_1 :prop_1 ;...;k_n :prop_n onde cada k_i e’ um inteiro e
	 *           cada prop_i e’ uma sequencia de caracteristicas da forma caract_1
	 *           ,...,caract_m
	 * @return true se o trajeto verifica todas as propriedades, false caso não o
	 *         verifique
	 */
	public static boolean verificaPropriedade(String[] trajeto, String propriedade, String sentido) {

		int planetaCorrente;

		boolean sentidoRegular;

		if (sentido == "REGULAR") {
			planetaCorrente = 0;
			sentidoRegular = true;
		} else {
			planetaCorrente = trajeto.length - 1;
			sentidoRegular = false;
		}

		// Dividir a string propriedade num vector com as propriedades de cada planeta
		String[] partes = propriedade.split(";");

		boolean trajetoValido = true;
		int distanciaDestino, indiceDestino;

		boolean condicaoSaida = false;

		for (int i = 0; i < partes.length && condicaoSaida == false; i++) {

			// Retirar da string tudo o que nao eh um numero e guardar o numero como int
			distanciaDestino = Integer.valueOf(partes[i].replaceAll("\\D+",""));

			indiceDestino = proximoPlaneta(distanciaDestino, planetaCorrente, trajeto.length, sentidoRegular);

			// Se algum dos planetas nao tiver uma das propriedades saimos do ciclo
			if (temPropriedades(trajeto[indiceDestino], partes[i]) == false) {
				trajetoValido = false;
				condicaoSaida = true;
			} else {
				planetaCorrente = indiceDestino;
			}

		}

		return trajetoValido;
	}

	/**
	 * Calcula o indice do planeta destino para o array trajeto utilizando condicoes
	 * de fronteira periodicas e dependendo do sentido fornecido
	 * 
	 * @param distanciaDestino Distancia entre o planeta atual e o planeta destino
	 * @param indiceCorrente   Indice do planeta atual no array trajeto
	 * @param tamanhoTrajeto   Tamanho do array trajeto
	 * @param sentidoRegular   Sentido em que se percorre o trajeto, tem valor true
	 *                         se for regular e false se for ao contrário
	 * @requires distanciaDestino >= 0 && tamanhoTrajeto > 0 && sentidoRegular !=
	 *           null && indiceCorrente tem de ser um indice dentro da range do
	 *           array trajeto
	 * @return Int que representa o indice do proximo planeta no array trajeto
	 */
	private static int proximoPlaneta(int distanciaDestino, int indiceCorrente, int tamanhoTrajeto,
			boolean sentidoRegular) {

		int indiceProximo;

		// Condicoes de fronteira periodicas para o vector trajeto
		if (sentidoRegular) {
			indiceProximo = (indiceCorrente + distanciaDestino + 2 * tamanhoTrajeto) % tamanhoTrajeto;
		} else {
			indiceProximo = (indiceCorrente - distanciaDestino + 2 * tamanhoTrajeto) % tamanhoTrajeto;
		}

		return indiceProximo;

	}

	/**
	 * Verifica se um dado planeta tem as propriedades dadas
	 * 
	 * @param planeta      Planeta a verificar se tem as propriedades
	 * @param propriedades Propriedades a verificar no planeta
	 * @requires planeta != null && propriedades != null && as propriedades sao uma
	 *           sequencia da distancia e propriedades a verificar de cada planeta
	 *           da forma k_1:prop_1,prop_2 , ... , k_n:prop_n_1,prop_n_2
	 * @return true se o planeta verificar todas as propriedades, caso contrario
	 *         devolve false
	 */
	private static boolean temPropriedades(String planeta, String propriedades) {

		// Retirar a distancia e o char ":" da string propriedades
		String tempProp = propriedades.replaceAll("\\d:", "");

		// Dividir a string num vector com as propriedades individuais
		String[] vectorPropriedades = tempProp.split(",");

		boolean contemProp = true;
		boolean condicaoSaida = false;

		for (int i = 0; i < vectorPropriedades.length && condicaoSaida == false; i++) {

			// Se algum dos planetas nao tiver uma das propriedade saimos do ciclo
			if (planeta.contains(vectorPropriedades[i]) == false) {
				contemProp = false;
				condicaoSaida = true;
			}
		}

		return contemProp;

	}

}
