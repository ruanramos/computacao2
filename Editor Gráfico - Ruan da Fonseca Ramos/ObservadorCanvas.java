public interface ObservadorCanvas {
    void desenhar(Canvas c);
    // Coordenadas do canvas - (0,0) é o canto superior esquerdo do canvas
    void aperto(int x, int y);
    void arrasto(int x, int y);
    void solta(int x, int y);
}