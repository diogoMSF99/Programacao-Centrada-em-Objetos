import java.util.List;
/**
 * As instancias desta classe representam Sistemas solares em
 * que os corpos celetes estao organizados numa matriz e a sua
 * ordem depende da forma como esta matriz eh percorrida. Um
 * sistema 2D auxilia-se de um Direcionador para definir essa
 * ordem. Na matriz podem existir buracos negros, corpos celestes
 * vulgares e elementos a null, representando ausencia de corpo
 * celeste
 * 
 * @author Grupo 36
 * @author Luís Ferreirinha Nº51127
 * @author Rui Pereira Nº51623
 * @author Diogo Ferreira Nº53330
 */
public class Sistema2D extends AbstractSistemaSolar {
    private CorpoCeleste[][] m;
    private Direcionador d;

    /**
     * Inicializa os atributos de um novo objeto
     * @param nome - nome do sistema
     * @param m - Matriz de corpos celestes
     * @param d - Ordem do direcionador
     * @requires m tem de ser uma matriz,
     *           nome != null and m != null and d != null
     */
    public Sistema2D(String nome, CorpoCeleste[][] m, Direcionador d) {
        super(nome);
        this.m = new CorpoCeleste[m.length][m[0].length];
        this.d = d;

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                this.m[i][j] = m[i][j];
            }
        }
    }

    /**
     * Devolve o numero de elementos da matriz deste sistema
     */
    public int quantosElementos() {
        return this.m[0].length*this.m.length;
    }

    /**
     * Devolve o corpo celeste da matriz deste sistema solar que
     * corresponde ao numero de ordem n, calculador pelo direcionador 
     * associado a este sistema 2D
     * @param n - Ordem do corpo celeste
     * @requires n != null and n >= 0
     * @returns CorpoCeleste de ordem n
     */
    public CorpoCeleste getElemento(int n) {
        this.d.defineUniverso(m);
        return this.d.nEsimoElemento(n);
    }

    /**
     * Devolve o buraco negro deste sistema solar que se encontra mais perto
     * do corpo celeste c
     * @param c - Corpo celeste
     * @returns instancia de BuracoNegro mais proxima da instancia c de CorpoCeleste
     *          caso nao encontre, devolve null
     */
    public BuracoNegro buracoNegroMaisPerto(CorpoCeleste c) {
        double distancia = 0;
        BuracoNegro result = null;
        for (int i = 0; i < m.length; i++) {
            for (CorpoCeleste corpo : m[i]) {
                if (!(c.equals(corpo)) && (corpo instanceof BuracoNegro)) {
                    if (distancia == 0 || c.distancia(corpo) < distancia) {
                        distancia = c.distancia(corpo);
                        result = (BuracoNegro) corpo;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Verifica se todos os inteiros contidos na lista aVisitar sao maiores que
     * zero e menores ou iguais a quantosElementos() e se todos os elementos da
     * matriz correspondentes aos numeros de ordem em aVisitar sao corpos celestes
     * vulgares (nao sao buracos negros nem null)
     * @param aVisitar - Lista com as ordens a verificar
     * @requires aVisitar != null
     * @returns true se verificar todas os requisitos
     * 
     */
    @Override
    public boolean podeVisitar(List<Integer> aVisitar) {
        if (!super.podeVisitar(aVisitar)) {
            return false;
        }
        for (int i : aVisitar) {
            if (this.getElemento(i) == null || this.getElemento(i) instanceof BuracoNegro) {
                return false;
            }
        }
        return true;
    }

    /**
     * Devolve a representacao textual do sistema solar
     * @returns String contento a representacao do sistema
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString() + "\n");
        sb.append("Direcionador: " + this.d.getClass().getName() + "\n");

        for (int i = 0; i < m.length; i++) {
            for (CorpoCeleste c : m[i]) {
                if (c == null) {
                    sb.append("null" + "   ");
                } else if (c instanceof BuracoNegro) {
                    sb.append("B" + c.posicao().toString() + "   ");
                } else {
                    sb.append(c.posicao().toString() + "   ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}