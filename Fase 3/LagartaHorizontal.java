/**
 * As instancias destas classe representam um direcionador
 * que percorre uma matriz no sentido de uma lagarta na 
 * horizontal
 * 
 * @author Grupo 36
 * @author Luís Ferreirinha Nº51127
 * @author Rui Pereira Nº51623
 * @author Diogo Ferreira Nº53330
 * 
 */
public class LagartaHorizontal implements Direcionador{
    private CorpoCeleste[][] m;

    /**
     * Define o universo sobre o qual o direcionador vai trabalhar
     * como sendo m
     * @param m - Sistema solar 2D
     * @requires m != null
     */
    @Override
    public void defineUniverso(CorpoCeleste[][] m) {
        this.m = new CorpoCeleste[m.length][m[0].length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                this.m[i][j] = m[i][j];
            }
        }
    }

    /**
     * Devolve o elemento na posicao n da matriz universo, de acordo
     * com a estrategia de lagarta horizontal
     * @param n - Posicao desejada
     * @requires n >= 0 and n != null
     * @returns Corpo celeste na posicao n
     */
    @Override
    public CorpoCeleste nEsimoElemento(int n) {
        
        // i, j = {indice linha, indice coluna}
		int i, j;

        int numeroColunas = this.m[0].length;
        int numeroLinhas = this.m.length;

		i = (((n - 1) / numeroColunas) + numeroLinhas) % numeroLinhas;
		j = (n - 1) % numeroColunas;

		// Se o numero da linha for impar entao o sentido em que a linha eh percorrida
		// eh ao contrario
		if (i % 2 != 0) {
			j = (numeroColunas - 1) - j;
		}

		return this.m[i][j];
    }
}