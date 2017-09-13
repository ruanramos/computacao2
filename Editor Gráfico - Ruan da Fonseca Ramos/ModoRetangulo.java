
public class ModoRetangulo extends Modo {
    int xorig, yorig;
    Retangulo ret;
    
    public ModoRetangulo(ModeloEditor modelo) {
        super(modelo);
    }
    
    public void inicio(int x, int y) {
        ret = new Retangulo(x, y, 0, 0);
        xorig = x;
        yorig = y;
        modelo.figuras.add(ret);
    }
    
    public void meio(int x, int y) {
        if(x < xorig) {
            ret.x = x;
            ret.larg = xorig - x;
        } else {
            ret.x = xorig;
            ret.larg = x - xorig;
        }
        if(y < yorig) {
            ret.y = y;
            ret.alt = yorig - y;
        } else {
            ret.y = yorig;
            ret.alt = y - yorig;
        }
    }
    
    public void fim(int x, int y) {
        super.fim(x, y);
        modelo.feitos.push(new ComandoFigura(ret));
    }
    
    public String tag() {
        return "ret";
    }
}