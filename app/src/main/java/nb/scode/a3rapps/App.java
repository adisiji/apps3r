package nb.scode.a3rapps;

import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
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

public class App extends MultiDexApplication {

    private static LocalDataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        initInjector();
        initRealm();
    }

    private void initInjector(){
        AppComponent appComponent = DaggerAppComponent.builder()
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

}
