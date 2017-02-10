package nb.scode.a3rapps;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import nb.scode.a3rapps.di.AppComponent;
import nb.scode.a3rapps.di.DaggerAppComponent;
import nb.scode.a3rapps.di.modules.DataModule;
import nb.scode.a3rapps.di.modules.NetworkModule;
import nb.scode.a3rapps.localdata.DaggerLocalDataComponent;
import nb.scode.a3rapps.localdata.LocalDataComponent;
import nb.scode.a3rapps.localdata.LocalDataModule;

/**
 * Created by neobyte on 2/8/2017.
 */

public class App extends Application {

    private static LocalDataComponent dataComponent;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
        initRealm();
    }

    private void initInjector(){
        appComponent = DaggerAppComponent.builder()
                .dataModule(new DataModule(this))
                .networkModule(new NetworkModule(this))
                .build();
        dataComponent = DaggerLocalDataComponent.builder()
                .localDataModule(new LocalDataModule())
                .appComponent(appComponent).build();
    }

    private void initRealm(){
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static LocalDataComponent getDataComponent(){
        return dataComponent;
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }

}
