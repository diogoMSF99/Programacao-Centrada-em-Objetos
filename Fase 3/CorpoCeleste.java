
/**
 * As instancias desta classe representam corpos celestes com uma
 * massa e posicao no espaco tridimensional
 * 
 * @author Grupo 36
 * @author Luís Ferreirinha Nº51127
 * @author Rui Pereira Nº51623
 * @author Diogo Ferreira Nº53330
 */
public class CorpoCeleste {
    private double massa;
    private Ponto3D pos;

    /**
     * Inicializa os atributos de um novo objeto
     * @param massa - Massa do novo corpo celeste
     * @param pos - Posicao do novo corpo celeste
     * @requires massa > 0 and pos != null
     */
    public CorpoCeleste (double massa, Ponto3D pos) {
        this.massa = massa;
        this.pos = pos;
    }

    /**
     * Devolve a massa do corpo celeste
     */
    public double massa() {
        return this.massa;
    }

    /**
     * Devolve a posicao do corpo celeste
     */
    public Ponto3D posicao() {
        return this.pos;
    }

    /**
     * Devolve a distancia deste corpo celeste ao corpo celeste c
     * @param c - corpo celeste ao qual se pretende calcular a distancia
     * @return A distancia entre este corpo celeste e c
     * @requires c != null
     */
    public double distancia(CorpoCeleste c) {
        return this.pos.distancia(c.pos);
    }

    /**
     * Verifica se este corpo celeste eh igual a um outro
     * @param other O objeto para comparacao
     * @return true se other eh instanceof de CorpoCeleste, se as massas
     *         deste corpo forem iguais as de other (com um erro de 0.0001)
     *         e se as posicoes deste corpo forem iguais as de other
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof CorpoCeleste)) {
            return false;
        }

        CorpoCeleste c = (CorpoCeleste) other;

        if (c.massa() >= this.massa + 0.0001 && c.massa() < this.massa - 0.0001) {
            return false;
        }
        return this.pos.equals(c.posicao());
    }
}