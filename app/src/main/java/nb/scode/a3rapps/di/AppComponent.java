package nb.scode.a3rapps.di;

import com.google.gson.Gson;
import dagger.Component;
import io.realm.Realm;
import nb.scode.a3rapps.di.modules.AppModule;
import nb.scode.a3rapps.di.modules.DataModule;
import nb.scode.a3rapps.di.modules.NetworkModule;
import nb.scode.a3rapps.di.scope.PerApp;
import nb.scode.a3rapps.network.ApiService;
import nb.scode.a3rapps.util.NetworkManager;

/**
 * Created by Aksiom on 6/29/2016.
 */
@PerApp
@Component(modules = {AppModule.class, NetworkModule.class, DataModule.class})
public interface AppComponent {

    Gson gson();

    Realm realm();

    ApiService apiService();

    NetworkManager networkManager();

}
