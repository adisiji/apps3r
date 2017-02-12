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
import nb.scode.a3rapps.util.NetworkManager;
import nb.scode.a3rapps.util.RealmIntegerListTypeAdapter;
import nb.scode.a3rapps.util.RealmStringListTypeAdapter;
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
    public NetworkManager networkManager(){
        return new NetworkManager(context);
    }

    @Provides
    @PerApp public ApiService provideApiService(){
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
                .baseUrl(ApiService.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonString))
                .addConverterFactory(GsonConverterFactory.create(gsonInt))
                .build();
        return retrofit.create(ApiService.class);
    }
}
