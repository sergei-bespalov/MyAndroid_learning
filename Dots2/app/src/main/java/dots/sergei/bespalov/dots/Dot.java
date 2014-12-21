package dots.sergei.bespalov.dots;

/**
 * simple dot...
 */
public class Dot {
    public static final  int DIAMETER = 20;
    /**
     * coordinate axis x
     */
    private final Float X;
    /**
     * coordinate axis y
     */
    private final Float Y;
    /**
     * color code
     */
    private final Integer color;
    /**
     * diameter
     */
    private Integer dim;
    /**
     * constructor..
     * @param X coordinate axis x
     * @param Y coordinate axis y
     * @param color is color
     */
    public Dot(Float X, Float Y, Integer color, Integer dim){
        this.X = X;
        this.Y = Y;
        this.color = color;
        this.dim = dim;
    }
    public Float getX(){
        return  X;
    }
    public Float getY(){
        return Y;
    }
    public Integer getDim(){
        return dim;
    }
    public Integer getColor(){
        return color;
    }
}
