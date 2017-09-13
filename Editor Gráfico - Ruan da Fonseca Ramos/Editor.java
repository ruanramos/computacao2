/*
 * Universidade Federal do Rio de Janeiro
 * Departamento de Ciencia da Computação
 * Computação 2 - Trabalho 3: Editor gráfico
 * Professor Fábio Mascarenhas
 * Aluno: Ruan da Fonseca Ramos - DRE 111309866
 */

public class Editor extends GuiApp {
    ModeloEditor modelo;
 
    private Acao acaoRetangulo = new Acao() { public void executa() { modelo.retangulo(); } };
    private Acao acaoCirculo = new Acao() { public void executa() { modelo.circulo(); } };
    private Acao acaoMover = new Acao() { public void executa() { modelo.mover(); } };
    private Acao acaoDesfazer = new Acao() { public void executa() { modelo.desfazer(); } };
    private Acao acaoRefazer = new Acao() { public void executa() { modelo.refazer(); } };
    private Acao acaoApagar = new Acao() { public void executa() { modelo.apagar(); } };
    private Acao acaoTriangulo = new Acao() { public void executa() { modelo.triangulo(); } };
    
    private ObservadorCanvas obsCanvas = new ObservadorCanvas() {
        public void desenhar(final Canvas c) {
            for(Figura f: modelo.figuras) {
                f.desenhar(new DesenhoFigura() {
                    public void desenhar(Retangulo ret) {
                        c.retangulo(ret.x, ret.y, ret.larg, ret.alt, modelo.cor);
                    }
                    public void desenhar(Circulo circ) {
                        c.circulo(circ.x, circ.y, circ.raio, modelo.cor);
                    }
                    public void desenhar(Ponto ponto) {
                    	c.circulo(ponto.x, ponto.y, 1, modelo.cor);
                    }
                    public void desenhar(Triangulo tri) {
                    	c.triangulo(tri.p1.x, tri.p1.y, tri.p2.x, tri.p2.y, tri.p3.x, tri.p3.y, modelo.cor);
                    }
                });
            }
        }
        
        public void aperto(int x, int y) {
            modelo.inicio(x, y);
        }
    
        public void arrasto(int x, int y) {
            modelo.meio(x, y);
        }
    
        public void solta(int x, int y) {
            modelo.fim(x, y);
        }
    };
    
    public Editor() {
        super("Editor", 1000, 700);
        modelo = new ModeloEditor();
        componentes.add(new Canvas(300, 0, 700, 700, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), obsCanvas));
        componentes.add(new BotaoOnOff(20, 50, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Retangulo", acaoRetangulo, testaModo("ret")));
        componentes.add(new BotaoOnOff(20, 150, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Circulo", acaoCirculo, testaModo("circ")));
        componentes.add(new BotaoOnOff(20, 250, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Mover", acaoMover, testaModo("mov")));
        componentes.add(new BotaoOnOff(20, 350, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Apagar", acaoApagar, testaModo("apg")));
        componentes.add(new BotaoOnOff(20, 450, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Triangulo", acaoTriangulo, testaModo("tri")));
        componentes.add(new Botao(20, 500, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Desfazer", acaoDesfazer));
        componentes.add(new Botao(20, 600, 260, 50, Cor.rgb(1.0, 1.0, 1.0), Cor.rgb(0.0, 0.0, 0.0), "Refazer", acaoRefazer));
    }

    private OnOff testaModo(String modo) {
        return new OnOff() {
            public boolean valor() {
                return modelo.modo.tag().equals(modo);
            }
        };
    }
    
    public void sair() {}
    
    public static void main(String[] args) {
        (new Editor()).run();
    }
}