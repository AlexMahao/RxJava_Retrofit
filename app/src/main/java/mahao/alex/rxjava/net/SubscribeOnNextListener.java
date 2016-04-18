package mahao.alex.rxjava.net;

/**
 *
 * 订阅的回调
 * Created by mdw on 2016/4/18.
 */
public interface SubscribeOnNextListener<T> {

    void onNext(T t);
}
