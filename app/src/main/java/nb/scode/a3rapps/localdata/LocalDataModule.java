package nb.scode.a3rapps.localdata;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import nb.scode.a3rapps.di.scope.Local;
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
