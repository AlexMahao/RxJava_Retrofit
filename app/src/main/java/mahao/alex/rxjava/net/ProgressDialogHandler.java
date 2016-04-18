package mahao.alex.rxjava.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/**
 * Created by mdw on 2016/4/18.
 */
public class ProgressDialogHandler extends Handler {

    public static final int SHOW_DIALOG = 1;

    public static final int DISMISS_DIALOG =2;


    private boolean mCancelable;

    private ProgressDialogListener mProgressDialogListener;

    private Context mContext;

    private ProgressDialog pd;



    public ProgressDialogHandler(boolean mCancelable, ProgressDialogListener mProgressDialogListener, Context mContext) {
        this.mCancelable = mCancelable;
        this.mProgressDialogListener = mProgressDialogListener;
        this.mContext = mContext;
    }


    private void initProgressDialog(){
        if (pd == null) {
            pd = new ProgressDialog(mContext);

            pd.setCancelable(mCancelable);

            if (mCancelable) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressDialogListener.onCancelProgress();
                    }
                });
            }

            if (!pd.isShowing()) {
                pd.show();
            }
        }
    }


    private void dismissProgressDialog(){
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
