
public class ComandoApagar implements Comando{
	private Figura fig;

    public ComandoApagar(Figura fig)
    {
        this.fig = fig;
    }

    @Override
    public void desfazer(ModeloEditor modelo)
    {
        modelo.figuras.add(this.fig);
    }

    @Override
    public void refazer(ModeloEditor modelo)
    {
        modelo.figuras.remove(this.fig);
    }
}
