package dots.sergei.bespalov.dots;

import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

/**
 * Created by Sergei on 21.12.2014.
 */
public class TrackingTouchListener implements View.OnTouchListener {
    private final Dots dots;
    private volatile LinkedList<Integer> tracks = new LinkedList<Integer>();

    TrackingTouchListener(Dots dots) {
        this.dots = dots;
    }

    @Override
    /**
     * multi touch
     */
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getActionMasked();
        int idx;
        int n;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                idx = MotionEventCompat.getActionIndex(event);
                tracks.add(event.getPointerId(idx));
                break;
            case MotionEvent.ACTION_POINTER_UP:
                idx = MotionEventCompat.getActionIndex(event);
                tracks.remove(event.getPointerId(idx));
                break;
            case MotionEvent.ACTION_MOVE:
                n = event.getHistorySize();
                for (Integer i : tracks) {
                    idx = event.findPointerIndex(i.intValue());
                    for (int j = 0; j < n; j++) {
                        addDot(
                                dots,
                                event.getHistoricalX(idx, j),
                                event.getHistoricalY(idx, j),
                                event.getHistoricalPressure(idx, j),
                                event.getHistoricalSize(idx, j)
                        );
                    }
                }
                break;
            default:
                return false;
        }
        for (Integer i: tracks){
            idx = event.findPointerIndex(i.intValue());
            addDot(
                    dots,
                    event.getX(idx),
                    event.getY(idx),
                    event.getPressure(idx),
                    event.getSize(idx)
            );
        }
        return true;
    }
    /*public boolean onTouch(View v, MotionEvent event) {
         switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                break;
            case  MotionEvent.ACTION_MOVE:
                for (int i = 0, n = event.getHistorySize(); i < n; i++){
                    addDot(
                            dots,
                            event.getHistoricalX(i),
                            event.getHistoricalY(i),
                            event.getHistoricalPressure(i),
                            event.getHistoricalSize(i)
                    );
                }
                break;
            default:
                return false;
        }
        //addDot(
        //        dots,
        //        event.getX(),
        //        event.getY(),
        //        event.getPressure(),
        //       event.getSize()
        //);

    }*/

    /**
     * add dot to dots
     *
     * @param dots
     * @param x    x coordinate
     * @param y    y coordinate
     * @param p    pressure
     * @param s    size
     */
    private void addDot(Dots dots, float x, float y, float p, float s) {
        dots.addDot(
                x,
                y,
                Color.CYAN,
                (int) ((p * s * Dot.DIAMETER) + 1)
        );
    }
}
