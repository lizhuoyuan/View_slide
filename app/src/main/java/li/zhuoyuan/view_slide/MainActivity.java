package li.zhuoyuan.view_slide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private int startX, startY;
    private static final String TAG = MainActivity.class.getName();
    private int windowwidth, windowheight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        windowwidth = getWindowManager().getDefaultDisplay().getWidth(); // 屏幕宽（像素，如：480px）
        windowheight = getWindowManager().getDefaultDisplay().getHeight(); // 屏幕高（像素，如：800p）
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //手指按下：
                    case MotionEvent.ACTION_DOWN:
                        Log.i(TAG, "onTouch: 手指按下了");
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int x = (int) event.getRawX();
                        int y = (int) event.getRawY();
                        int l = btn.getLeft();
                        int r = btn.getRight();
                        int t = btn.getTop();
                        int b = btn.getBottom();
                        int dx = x - startX;
                        int dy = y - startY;
                        l += dx;
                        r += dx;
                        t += dy;
                        b += dy;
                        //超出屏幕时返回
                        if (l <= 0 || r >= windowwidth || t <= 0 || b >= windowheight) {
                            break;
                        }
                        btn.layout(l, t, r, b);
                        //把当前位置设成起始坐标
                        startX = (int) event.getRawX();
                        startY = (int) event.getRawY();
                        Log.e("MainActivity", "手指移动");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("MainActivity", "手指离开");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}