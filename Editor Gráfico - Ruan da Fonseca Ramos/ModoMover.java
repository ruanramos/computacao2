public class ModoMover extends Modo {
    Figura foco;
    int xorig, x, yorig, y;
    
    public ModoMover(ModeloEditor modelo) {
        super(modelo);
    }
    
    public void inicio(int x, int y) {
        for(Figura f: modelo.figuras) {
            if(f.dentro(x, y)) {
                foco = f;
                this.x = xorig = x;
                this.y = yorig = y;
            }
        }
    }
    
    public void meio(int x, int y) {
        if(foco != null) {
            int dx = x - this.x;
            int dy = y - this.y;
            this.x = x;
            this.y = y;
            foco.mover(dx, dy);
        }
    }
    
    public void fim(int x, int y) {
        super.fim(x, y);
        if(foco != null) {
            modelo.feitos.push(new ComandoMover(foco, x - xorig, y - yorig));
            foco = null;
        }
    }

    public String tag() {
        return "mov";
    }
}