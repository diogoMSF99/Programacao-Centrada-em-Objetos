/**
 * Esta interface define um tipo de dados representando um
 * direcionador que vai percorrer uma matriz 2D num sentido
 * indicado
 * 
 * @author Grupo 36
 * @author Luís Ferreirinha Nº51127
 * @author Rui Pereira Nº51623
 * @author Diogo Ferreira Nº53330
 * 
 */
public interface Direcionador {
    
    /**
     * Define o universo sobre o qual o direcionador vai trabalhar
     */
    void defineUniverso(CorpoCeleste[][] m);

    /**
     * Devolve o elemento na poisicao n da matriz universo, de acordo
     * com a estrategia de direcionamento implementada pelo direcionadar
     * 
     * @param n - Posicao na matriz universo
     */
    CorpoCeleste nEsimoElemento(int n);
}