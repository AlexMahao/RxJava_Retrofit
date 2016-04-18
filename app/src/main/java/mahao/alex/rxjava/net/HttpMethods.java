package mahao.alex.rxjava.net;

import java.util.List;
import java.util.concurrent.TimeUnit;

import mahao.alex.rxjava.bean.MovieEntity;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * 用一个单例来封装该对象，在构造方法中创建Retrofit和对应的Service。
 * 如果需要访问不同的基地址，那么你可能需要创建多个Retrofit对象，或者干脆根据不同的基地址封装不同的HttpMethod类。
 *
 * Created by mdw on 2016/4/18.
 */
public class HttpMethods {

    public static final String BASE_URL = "";


    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;

    private MovieService movieService;


    private HttpMethods() {
        OkHttpClient.Builder okhttp = new OkHttpClient().newBuilder().connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(okhttp.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();


        movieService = retrofit.create(MovieService.class);


    }


    private static class SingletonHolder {

        private static final HttpMethods INSTANCE = new HttpMethods();

    }

    public static HttpMethods getInstance(){

        return SingletonHolder.INSTANCE;
    }


    public void getTopMovie(Subscriber<List<MovieEntity>> subscriber , int start, int count){
        movieService.getTopMovie(start,count)
                .subscribeOn(Schedulers.io())
                .map(new HttpResultFunc<List<MovieEntity>>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
