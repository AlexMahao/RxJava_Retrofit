package mahao.alex.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mahao.alex.rxjava.bean.MovieEntity;
import mahao.alex.rxjava.net.HttpMethods;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.btn_click)
    Button btn_click;

    @Bind(R.id.tv_result)
    TextView tv_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_click)
    public void onClick() {
        getMovie();


    }



    public void getMovie() {

        HttpMethods.getInstance().getTopMovie(new Subscriber<List<MovieEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<MovieEntity> listHttpResult) {

            }
        }, 0, 10);

    }
}
