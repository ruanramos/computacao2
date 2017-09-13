/*
 * Universidade Federal do Rio de Janeiro
 * Computacao II - 2016.2
 * Professor Fabio Mascarenhas
 * Jogo Flappy Bird - Trabalho 2
 * Aluno : Ruan da Fonseca Ramos - DRE 111309866
 */

import java.util.HashMap;

/**
 * Cores em RGB
 */
public class Cor
{
    public int r;
    public int g;
    public int b;
    
    private static HashMap<Integer, Cor> cores = new HashMap<Integer, Cor>();
    
    /*
     * Cria uma cor dados os componentes entre 0 e 255
     */
    private Cor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
    /*
     * Cria uma cor dados os componentes entre 0 e 1
     */
    public static Cor rgb(double r, double g, double b) {
        return Cor.rgb((int)(r*255), (int)(g*255), (int)(b*255));
    }
    
    public static Cor rgb(int r, int g, int b) {
        int indice = (r << 16) | (g << 8) | b;
        if(!cores.containsKey(indice)) {
            cores.put(indice, new Cor(r, g, b));
        }
        return cores.get(indice);
    }
    
    public static Cor BRANCO = Cor.rgb(1.0, 1.0, 1.0);
    public static Cor AZUL = Cor.rgb(0.0, 0.0, 1.0);
    public static Cor VERMELHO = Cor.rgb(1.0, 0.0, 0.0);
    public static Cor VERDE = Cor.rgb(0.0, 1.0, 0.0);
}
