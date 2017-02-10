package nb.scode.a3rapps.di.modules;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import nb.scode.a3rapps.di.scope.PerApp;

/**
 * Created by Aksiom on 6/29/2016.
 */
@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @PerApp
    Application providesApplication() {
        return mApplication;
    }

}
