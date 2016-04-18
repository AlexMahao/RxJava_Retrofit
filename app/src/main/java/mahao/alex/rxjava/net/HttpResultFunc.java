package mahao.alex.rxjava.net;

import mahao.alex.rxjava.bean.HttpResult;
import rx.functions.Func1;

/**
 * Created by mdw on 2016/4/18.
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>,T> {


    @Override
    public T call(HttpResult<T> httpResult) {
        if(httpResult.getResultCode()!=200){
            throw  new ApiException(httpResult.getResultCode());
        }
        return httpResult.getSubjects();
    }
}
