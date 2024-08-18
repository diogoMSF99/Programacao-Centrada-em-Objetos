import java.util.List;
/**
 * Esta abstract class define o que eh comum a varios tipos de
 * sistemas solares
 * 
 * @author Grupo 36
 * @author Luís Ferreirinha Nº51127
 * @author Rui Pereira Nº51623
 * @author Diogo Ferreira Nº53330
 */
public abstract class AbstractSistemaSolar implements SistemaSolar {
    private String nome;

    public AbstractSistemaSolar(String nome) {
        this.nome = nome;
    }

    /**
     * Devolve o nome do sistema solar
     */
    @Override
    public String nome() {
        return this.nome;
    }

    /**
     * Determina se todos os inteiros da lista aVisitar sao maiores que zero
     * e menores ou iguais a quantosElementos()
     * 
     * @param aVisitar - Lista de inteiros a verificar
     * @returns - true se todos os elementos da lista verificarem as condicoes
     */
    @Override
    public boolean podeVisitar(List<Integer> aVisitar) {
        for (int i : aVisitar) {
            if (i > 0 && i <= quantosElementos()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Devolve a representacao textual correspondente ao nome do sistema solar
     */
    @Override
    public String toString() {
        return "Nome: " + this.nome;
    }
}