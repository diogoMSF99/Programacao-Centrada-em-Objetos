import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * As instancias desta classe representam grandes premios siderais
 * que se realizam sobre um dado sistema solar e em que varios
 * viajantes vao fazendo jogadas (escolhas dos destinos para viagens
 * no sistema solar). Define um premio base que cada viajante recebe
 * por visitar um dado corpo celeste. O premio e maior em viagens de
 * risco (aquelas para planetas a uma distancia de risco de um buraco
 * negro) e mais pequeno em determinadas condicoes
 *
 * @author Grupo 36
 * @author Luís Ferreirinha Nº51127
 * @author Rui Pereira Nº51623
 * @author Diogo Ferreira Nº53330
 *
 */
public class GrandePremioSideral {
    private SistemaSolar ss;
    private Map<String, Viajante> jogs;
    private int premioBase;
    private int TAXA_RISCO = 5;
    private int PONTOS_BURACO_NEGRO = Integer.MAX_VALUE;

    /**
     * Inicializa os atributos de um novo objeto
     * @param ss - Sistema solar onde decorre o grande premio
     * @param jogs - Participantes do grande premio
     * @param premioBase - Premio base do gp
     * @requires ss != null and jogs != null and premioBase != null
     *           and premioBase > 0
     */
    public GrandePremioSideral(SistemaSolar ss, List<Viajante> jogs, int premioBase) {
        this.ss = ss;
        this.jogs = new HashMap<String, Viajante>();
        this.premioBase = premioBase;

        for (Viajante v : jogs) {
            this.jogs.put(v.nome(), v);
        }
    }

    /**
     * Devolve o premio base definido para este grande premio
     */
    public int premioBase() {
        return this.premioBase;
    }

    /**
     * Regista as jogadas dos varios viajantes que participam no grande
     * premio
     * @param jogadas - Lista de jogadas
     * @requires jogadas != null
     */
    public void fazJogada(List<Par<String, Integer>> jogadas) {
        int numero;
        String nome;
        CorpoCeleste c;
        Viajante v;
        for (Par<String, Integer> jogada : jogadas) {
        	// Guardar os dados que vamos utilizar em variaveis para melhorar visibilidade
            numero = jogada.segundo();
            c = ss.getElemento(numero);
            
            if (c != null) {
                nome = jogada.primeiro();
                v = jogs.get(nome);
                
                if (v.podeViajar(c.posicao()) && v.posicaoGlobal() != c.posicao()) {
                	// Registar a viagem 
                    v.mudaPosicaoGlobal(c.posicao());
                    
                    if (c instanceof BuracoNegro) {
                        v.registaPontos(-this.PONTOS_BURACO_NEGRO);
                    } else if (menorDistanciaSeguranca(c, this.ss)) {
                        v.registaPontos(this.premioBase * this.TAXA_RISCO);
                    } else {
                        v.registaPontos(this.premioBase);
                    }
                // Nao se conseguiu mover
                } else {
                    v.registaPontos(-(v.pontuacao()/5));
                }
            // C for null, retira-se metade da pontuacao
            } else {
                jogs.get(jogada.primeiro()).registaPontos(-(jogs.get(jogada.primeiro()).pontuacao()/2));
            }
        }
    }
    
    
    /**
     * Determina se o Corpo Celeste esta a uma distancia menor que a distancia minima de
     * seguranca do buraco negro mais proximo
     * @param Corpo Celeste
     * @param Sistema Solar
     * @requires c != null and !(c instanceof BuracoNegro) and ss != null
     * @return true a distancia entre os corpos for menor que a distancia minima de seguranca
     */
    private static boolean menorDistanciaSeguranca(CorpoCeleste c, SistemaSolar ss) {
    	return c.distancia(ss.buracoNegroMaisPerto(c)) < ss.buracoNegroMaisPerto(c).distanciaMinimaSeguranca(c);
    }
    

    /**
     * Devolve o(s) nome(s) do(s) viajante(s) que obteve(obtiveram) a maior pontuacao
     * @returns Lista de Strings representando os jogadores com maior pontuacao
     */
    public List<String> vencedores() {
        int maiorPontuacao = 0;
        List<String> result = new ArrayList<String>();

        for (Viajante v : this.jogs.values()) {
            if (v.pontuacao() >= maiorPontuacao) {
                maiorPontuacao = v.pontuacao();
            }
        }
        for (Viajante v : this.jogs.values()) {
            if (v.pontuacao() == maiorPontuacao) {
                result.add(v.nome());
            }
        }
        return result;
    }

    /**
     * Devolve a representacao textual do grande premio
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("============ GRANDE PREMIO ============\n");
        sb.append("Premio base: " + this.premioBase + "\n");
        sb.append("Sistema Solar:\n");
        sb.append(ss.toString());
        sb.append("Viajantes:\n");
        for (Viajante v : this.jogs.values()) {
            sb.append(v.toString());
        }
        return sb.toString();
    }
}
