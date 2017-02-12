package nb.scode.a3rapps.di.modules;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmList;
import nb.scode.a3rapps.di.scope.PerApp;
import nb.scode.a3rapps.modelretro.RealmString;
import nb.scode.a3rapps.util.RealmStringListTypeAdapter;

/**
 * Created by Aksiom on 6/29/2016.
 */
@Module
public class DataModule {

    private Application application;

    public DataModule(Application application) {
        this.application = application;
    }

    @Provides
    @PerApp
    Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Provides
    @PerApp
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

}
