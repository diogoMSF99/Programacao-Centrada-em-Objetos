/**
 * As instancias desta classe representam corpos celestes com
 * uma posicao no espaco tridimensional e uma massa tal, que 
 * faz com que tenham uma grande forca de atracao que pode 
 * provocar a destruicao de outros corpos nas suas proximidades
 *
 * @author Grupo 36
 * @author Luís Ferreirinha Nº51127
 * @author Rui Pereira Nº51623
 * @author Diogo Ferreira Nº53330
 */
public class BuracoNegro extends CorpoCeleste{

    /**
     * Inicializa os atributos de um novo objeto
     * @param massa - Massa do novo corpo celeste
     * @param pos - Posicao do novo corpo celeste
     * @requires massa > 0 and pos != null
     */
    public BuracoNegro(double massa, Ponto3D pos) {
        super(massa, pos);
    }

    /**
     * Retorna o valor da distancia minima a que um outro corpo celeste
     * tem que estar para nao ser afetado pela forca de atracao deste
     * buraco negro
     * @param c - Corpo celeste ao qual se vai determinar a distancia de seguranca
     * @requires c != null
     * @returns Um double representando a distancia de seguranca
     */
    public double distanciaMinimaSeguranca(CorpoCeleste c) {
        return Math.sqrt(this.massa() * c.massa());
    }
}