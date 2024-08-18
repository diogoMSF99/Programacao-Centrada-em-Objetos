import java.util.ArrayList;
import java.util.List;
/**
 * As instancias desta classe representam sistemas solares em que
 * os corpos celestes estao organizados linearmente, sem apresentarem
 * buracos negros nem espacos null. Um sistema deste tipo tem por base
 * um sistema 2D e funciona como que uma versao filtrada do universo deste,
 * representando somente os elementos que sao corpos celestes vulgares
 *
 * @author Grupo 36
 * @author LuÃ­s Ferreirinha NÂº51127
 * @author Rui Pereira NÂº51623
 * @author Diogo Ferreira NÂº53330
 *
 */
public class Sistema1DSeguro extends AbstractSistemaSolar {
    private Sistema2D sistema2DBase;
    private List<CorpoCeleste> sistemaLinear;

    /**
     * Inicializa os atributos de um novo objeto
     * @param nome - nome do sistema
     * @param m - Matriz de corpos celestes
     * @param d - Ordem do direcionador
     * @requires m tem de ser uma matriz,
     *           nome != null and m != null and d != null
     */
    public Sistema1DSeguro(String nome, CorpoCeleste[][] m) {
        super(nome);
        Direcionador d = new LinhaALinha();
        this.sistema2DBase = new Sistema2D(nome, m, d);
        this.sistemaLinear = Convert2DTo1D(this.sistema2DBase);
    }


    /**
     * Metodo auxiliar ao construtor:
     * Converte um sistema solar 2D para um array 1D contendo apenas
     * corpos celestes regulares (nao sao null nem sao buracos negros)
     * @param base - Sistema solar 2D a coverter
     * @requires base != null
     * @returns Lista 1D contendo os corpos celestes regulares do sistema
     *          solar 2D fornecido
     */
    private static List<CorpoCeleste> Convert2DTo1D(Sistema2D base) {
        List<CorpoCeleste> result = new ArrayList<CorpoCeleste>();
        for (int i = 1; i < base.quantosElementos() + 1; i++) {
            if (base.getElemento(i) != null && !(base.getElemento(i) instanceof BuracoNegro)) {
                result.add(base.getElemento(i));
            }
        }
        return result;
    }

    /**
     * Devolve o numero de elementos da lista de corpos celestes deste sistema
     */
    public int quantosElementos() {
        return this.sistemaLinear.size();
    }

    /**
     * Devolve o (n-1)-esimo corpo celeste da lista deste sistema solar
     */
    public CorpoCeleste getElemento(int n) {
        return sistemaLinear.get(n-1);
    }

    /**
     * Devolve o buraco negro do sistema 2D associado que se encontra mais
     * perto do corpo celeste c
     */
    public BuracoNegro buracoNegroMaisPerto(CorpoCeleste c) {
        return this.sistema2DBase.buracoNegroMaisPerto(c);
    }

    /**
     * Devolve uma representacao textual do sistema solar 1D
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString() + "\n");
        sb.append("Planetas:\n");
        for (CorpoCeleste c : sistemaLinear) {
            sb.append(c.posicao().toString() + "   ");
        }
        return sb.toString();
    }
}