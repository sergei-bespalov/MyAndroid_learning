package dots.sergei.bespalov.dots;

import android.os.Handler;

/**
 * Created by Sergei on 25.12.2014.
 */
public class DotGenerator implements Runnable {
    final Dots dots;
    final DotsView dotsView;
    final int color;
    private final Runnable makeDot = new Runnable() {
        @Override
        public void run() {
            dotsActivity.addDot(dots, dotsView, color);
        }
    };
    final DotsActivity dotsActivity;
    private final Handler handler = new Handler();
    private volatile boolean done;

    /**
     * Constructor. Run in main thread
     *
     * @param dots     dots list
     * @param view     dots view
     * @param color    dot color
     * @param activity dots activity
     */
    DotGenerator(Dots dots, DotsView view, int color, DotsActivity activity) {
        this.dots = dots;
        this.dotsView = view;
        this.color = color;
        this.dotsActivity = activity;
    }

    /**
     * Run in main thread
     */
    public void done() {
        done = true;
    }

    @Override
    public void run() {
        while (!done) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            handler.post(makeDot);
        }
    }

}
