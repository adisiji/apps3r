package nb.scode.a3rapps;

import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import nb.scode.a3rapps.di.AppComponent;
import nb.scode.a3rapps.di.DaggerAppComponent;
import nb.scode.a3rapps.di.DaggerPagerComponent;
import nb.scode.a3rapps.di.PagerComponent;
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
    private static PagerComponent sPagerComponent;
    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        initRealm();
        initInjector();
    }

    private void initInjector(){
        sAppComponent = DaggerAppComponent.builder()
                .dataModule(new DataModule(this))
                .networkModule(new NetworkModule(this))
                .build();
        dataComponent = DaggerLocalDataComponent.builder()
                .localDataModule(new LocalDataModule())
                .appComponent(sAppComponent).build();
        sPagerComponent = DaggerPagerComponent.builder()
                .pagerModule(new PagerModule(dataComponent.getLocalRepo()))
                .localDataComponent(dataComponent)
                .build();
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

    public static PagerComponent getPagerComponent(){
        return sPagerComponent;
    }

    public static AppComponent getAppComponent(){
        return sAppComponent;
    }

}
