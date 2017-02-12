package nb.scode.a3rapps.util;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by neobyte on 2/8/2017.
 */

public class CustomJsInterface {

    private final String TAG = CustomJsInterface.class.getSimpleName();

    private Context context;
    private CallbackJs callbackJs;

    public CustomJsInterface(Context context){
        this.context = context;
        callbackJs = (CallbackJs)context;
    }

    @JavascriptInterface
    public void showHTML(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            Log.d(TAG, "Success "+obj.toString());
            int uid = obj.getInt("uid");
            String key = obj.getString("key");
            String nama = obj.getString("nama");
            int saldo = obj.getInt("saldo");
            String prodTersimpan = obj.getString("produk_tersimpan");
            int produkTersimpanMaks = obj.getInt("produk_tersimpan_maks");
            callbackJs.sendMeta(uid,key, nama, saldo, prodTersimpan, produkTersimpanMaks);
        } catch (JSONException e) {
            Log.e(TAG, "Could not parse malformed JSON: \"" + result + "\"");
        }
    }

    public interface CallbackJs{
        void sendMeta(int uid, String key, String nama ,
                      int saldo, String prodTersimpan, int prodTersimpanMaks);
    }

}
