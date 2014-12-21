package dots.sergei.bespalov.dots;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * list of dots
 */
public class Dots {
    /**
     * dots list
     */
    private LinkedList<Dot> dots;
    private List<Dot> safeDots;
    public interface DotChangeListener {
        void onChange(Dots dot);
    }
    private DotChangeListener listener;
    /**
     * constructor..
     */
    public Dots(){
        dots = new LinkedList<Dot>();
        safeDots = Collections.unmodifiableList(dots);
    }
    /**
     * safe list of dots
     * @return list of dots
     */
    public List<Dot> getDots(){
        return safeDots;
    }
    /**
     * set change listener
     */
    public void setOnChangeListener(DotChangeListener listener){
        this.listener = listener;
    }
    /**
     * add new dot to dots list
     * @param X dot coordinate x
     * @param Y dot coordinate y
     * @param color  dot color
     * @param dim dot diameter
     */
    public void addDot(Float X, Float Y, Integer color, Integer dim) {
        dots.add(new Dot(X, Y, color, dim));
        notifyListener();
    }
    /**
     * get last dot
     * @return last dot
     */
    public Dot getLastDot(){
        if (dots.size() > 0){
            return dots.getLast();
        }
        return null;
    }
    /**
     * notify listener
     */
    private void notifyListener(){
        if(null != listener){
            listener.onChange(this);
        }
    }


}
