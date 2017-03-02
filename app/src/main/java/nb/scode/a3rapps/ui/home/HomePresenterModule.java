package nb.scode.a3rapps.ui.home;

import dagger.Module;
import dagger.Provides;

/**
 * Created by neobyte on 2/9/2017.
 */

@Module
public class HomePresenterModule {

    private HomeContract.View view;

    public HomePresenterModule(HomeContract.View view){
        this.view = view;
    }

    @Provides
    public HomeContract.View providesHomeView(){
        return view;
    }

}
