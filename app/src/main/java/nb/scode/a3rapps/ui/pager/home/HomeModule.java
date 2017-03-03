package nb.scode.a3rapps.ui.pager.home;

import dagger.Module;
import dagger.Provides;
import nb.scode.a3rapps.ui.pager.LoadFirst;

/**
 * Created by neobyte on 2/9/2017.
 */

@Module
public class HomeModule {

    @Provides
    @HomeScope
    LoadFirst provideLoadef(){
        return new LoadFirst();
    }

}
