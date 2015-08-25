package ie.trusthub.trusthub2.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ThubRestClient {
//    private static final String BASE_URL =  "https://httpbin.org/get"; // test url
//    private static final String BASE_URL =  "https://secret-earth-8044.herokuapp.com/"; // heroku api server
    private static final String BASE_URL =  "http://10.0.2.2:3000/"; // localhost from android emmulator

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("Authorization", "jammer");
        client.addHeader("content-type", "application/x-www-form-urlencoded");
        client.addHeader("Accept", "*/*");
        client.addHeader("ThubUser-Agent", "Mozilla");
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void patch(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("Authorization", "jammer");
        client.addHeader("content-type", "application/x-www-form-urlencoded");
        client.addHeader("Accept", "*/*");
        client.addHeader("ThubUser-Agent", "Mozilla");
        client.patch(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
