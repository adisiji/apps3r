package nb.scode.a3rapps.di.modules;

import dagger.Module;
import dagger.Provides;
import nb.scode.a3rapps.localdata.LocalDataTask;

/**
 * Created by neobyte on 3/3/2017.
 */

@Module
public class PagerModule {

    private LocalDataTask mLocalDataTask;

    public PagerModule(LocalDataTask localDataTask) {

        mLocalDataTask = localDataTask;
    }

    @Provides
    LocalDataTask providesLocalDataTaskPager(){
        return mLocalDataTask;
    }

}
