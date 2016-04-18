package mahao.alex.rxjava.net;

import android.content.Context;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by mdw on 2016/4/18.
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressDialogListener {


    private SubscribeOnNextListener<T> mSubscribeOnNextListener;

    private ProgressDialogHandler mProgressDialogHandler;

    private Context mContext;

    public ProgressSubscriber(SubscribeOnNextListener<T> mSubscribeOnNextListener, ProgressDialogHandler mProgressDialogHandler, Context mContext) {
        this.mSubscribeOnNextListener = mSubscribeOnNextListener;
        this.mProgressDialogHandler = new ProgressDialogHandler(true,this,mContext);
        this.mContext = mContext;
    }

    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }


    @Override
    public void onStart() {
        showProgressDialog();
    }



    @Override
    public void onCompleted() {
        dismissProgressDialog();
        Toast.makeText(mContext, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(mContext, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(mContext, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();
    }

    @Override
    public void onNext(T o) {
        mSubscribeOnNextListener.onNext(o);
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
