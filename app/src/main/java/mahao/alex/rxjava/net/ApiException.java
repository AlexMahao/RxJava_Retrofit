package mahao.alex.rxjava.net;

/**
 * Created by mdw on 2016/4/18.
 */
public class ApiException extends RuntimeException {

    private static final int NET_ERROR = 200;

    private static final int HTTP_ERROR = 300;


    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    private static String getApiExceptionMessage(int resultCode) {
        switch (resultCode) {
            case NET_ERROR:
                return "网络错误200";
            case HTTP_ERROR:
                return "网络错误300";
            default:
                return "未知错误";
        }

    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

}
