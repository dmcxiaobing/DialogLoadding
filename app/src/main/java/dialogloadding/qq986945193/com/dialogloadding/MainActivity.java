package dialogloadding.qq986945193.com.dialogloadding;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */
public class MainActivity extends AppCompatActivity {
    private Dialog mDialog;
    private Dialog mWeiboDialog;
    private Button btn_show_weibo_loading;
    private Button btn_show_thrid_loading;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    DialogThridUtils.closeDialog(mDialog);
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_show_weibo_loading = (Button) findViewById(R.id.btn_show_weibo_loading);
        btn_show_thrid_loading = (Button) findViewById(R.id.btn_show_thrid_loading);
        btn_show_weibo_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWeiboDialog = WeiboDialogUtils.createLoadingDialog(MainActivity.this, "加载中...");
                mHandler.sendEmptyMessageDelayed(1, 2000);
            }
        });

        btn_show_thrid_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = DialogThridUtils.showWaitDialog(MainActivity.this, "加载中...", false, true);
                mHandler.sendEmptyMessageDelayed(1, 2000);
            }
        });
    }
}
