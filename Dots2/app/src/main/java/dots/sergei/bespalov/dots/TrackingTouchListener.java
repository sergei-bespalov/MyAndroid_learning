package dots.sergei.bespalov.dots;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Sergei on 21.12.2014.
 */
public class TrackingTouchListener implements View.OnTouchListener {
    private final Dots dots;

    TrackingTouchListener(Dots dots){
        this.dots = dots;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
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
        /*addDot(
                dots,
                event.getX(),
                event.getY(),
                event.getPressure(),
                event.getSize()
        );*/
        return true;
    }

    private void addDot(Dots dots, float x, float y, float p, float s){
        dots.addDot(
                x,
                y,
                Color.CYAN,
                (int)((p*s*Dot.DIAMETER) + 1)
        );
    }
}
