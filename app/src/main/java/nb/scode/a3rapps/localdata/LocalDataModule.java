package nb.scode.a3rapps.localdata;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

import nb.scode.a3rapps.di.scope.Local;
import nb.scode.a3rapps.di.scope.PerApp;
import nb.scode.a3rapps.home.HomeContract;
import nb.scode.a3rapps.home.HomeFragment;
import nb.scode.a3rapps.login.LoginContract;
import nb.scode.a3rapps.login.LoginFragment;
import nb.scode.a3rapps.network.ApiService;
import nb.scode.a3rapps.util.NetworkManager;

/**
 * Created by neobyte on 2/9/2017.
 */

@Module
public class LocalDataModule {

    @Provides
    @Local
    LocalDataRepo providesLocalDataRepo(ApiService apiService, Realm realm, NetworkManager networkManager){
        return new LocalDataRepo(apiService,realm,networkManager);
    }


}
