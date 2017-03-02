package nb.scode.a3rapps.ui.catat;

import dagger.Module;
import dagger.Provides;

/**
 * Created by neobyte on 2/13/2017.
 */

@Module
public class CatatPresenterModule {

    private CatatContract.View view;

    public CatatPresenterModule(CatatContract.View view){
        this.view = view;
    }

    @Provides
    CatatContract.View providesCatatView() {
        return view;
    }
}
