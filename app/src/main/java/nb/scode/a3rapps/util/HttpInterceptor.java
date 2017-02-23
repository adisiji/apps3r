package nb.scode.a3rapps.util;

/**
 * Created by neobyte on 2/23/2017.
 */

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import nb.scode.a3rapps.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * HttpInterceptor
 */
public class HttpInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Log.d(BuildConfig.APPLICATION_ID, "call ==> " + request.url());
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Log.d(BuildConfig.APPLICATION_ID, "response ==> " + buffer.clone().readString(UTF8).toString());
        return response;
    }
}