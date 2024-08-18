import java.util.List;

/**
 * Este interface define um tipo de dados representando um Sistema Solar que tem
 * diversos elementos, podendo estes elementos serem CorpoCeleste ou BuracoNegro.
 
 * @author Grupo 36
 * @author Luís Ferreirinha Nº51127
 * @author Rui Pereira Nº51623
 * @author Diogo Ferreira Nº53330 
 * 
 */
public interface SistemaSolar {

    /**
     * Devolve o nome do sistema solar como String
     */
    String nome();

    /**
     * Determina se eh possivel visitar todos os elementos do sistema solar
     * correspondentes aos numeros de ordem contidos na lista aVisitar
     * 
     * @param aVisitar
     */
    boolean podeVisitar(List<Integer> aVisitar);

    /**
     * Devolve o numero de elementos que este sistema solar define
     */
    int quantosElementos();

    /**
     * Devolve o corpo celeste deste sistema solar que corresponde ao numero
     * de ordem n
     * 
     * @param n - Ordem do corpo celeste
     * @requires n >= 0
     */
    CorpoCeleste getElemento (int n);

    /**
     * Devolve o buraco negro deste sistema solar que se encontra mais proximo
     * do corpo celeste c
     * 
     * @param c - Um corpo celeste do sistema solar
     */
    BuracoNegro buracoNegroMaisPerto (CorpoCeleste c);
}