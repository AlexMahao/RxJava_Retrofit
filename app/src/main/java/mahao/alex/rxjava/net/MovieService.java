package mahao.alex.rxjava.net;

import java.util.List;

import mahao.alex.rxjava.bean.HttpResult;
import mahao.alex.rxjava.bean.MovieEntity;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by mdw on 2016/4/18.
 */
public interface MovieService {


    @GET("top250")
    Observable<HttpResult<List<MovieEntity>>> getTopMovie(@Query("start") int start, @Query("count") int count );
}
