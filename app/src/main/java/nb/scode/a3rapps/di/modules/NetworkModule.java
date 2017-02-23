package nb.scode.a3rapps.di.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmList;
import nb.scode.a3rapps.di.scope.PerApp;
import nb.scode.a3rapps.modelretro.RealmInteger;
import nb.scode.a3rapps.modelretro.RealmString;
import nb.scode.a3rapps.network.ApiService;
import nb.scode.a3rapps.util.HttpInterceptor;
import nb.scode.a3rapps.util.NetworkManager;
import nb.scode.a3rapps.util.RealmIntegerListTypeAdapter;
import nb.scode.a3rapps.util.RealmStringListTypeAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aksiom on 6/29/2016.
 */
@Module
public class NetworkModule {

    private Context context;

    public NetworkModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerApp
    NetworkManager networkManager(){
        return new NetworkManager(context);
    }

    @Provides
    @PerApp
    HttpInterceptor interceptor(){
        return new HttpInterceptor();
    }

    @Provides
    @PerApp
    ApiService provideApiService(HttpInterceptor interceptor){

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gsonString = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<RealmList<RealmString>>(){}.getType(),
                        RealmStringListTypeAdapter.INSTANCE)
                .setLenient()
                .create();

        Gson gsonInt = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<RealmList<RealmInteger>>(){}.getType(),
                        RealmIntegerListTypeAdapter.INSTANCE)
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonString))
                .addConverterFactory(GsonConverterFactory.create(gsonInt))
                .baseUrl(ApiService.BASE_URL)
                .client(client) //TODO: Delete when release
                .build();

        return retrofit.create(ApiService.class);
    }
}
