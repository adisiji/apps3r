package nb.scode.a3rapps.ui.pager.more;

import dagger.Module;
import dagger.Provides;
import nb.scode.a3rapps.ui.pager.LoadFirst;

/**
 * Created by neobyte on 3/5/2017.
 */

@Module
public class MoreModule {

    @Provides
    @MoreScope
    LoadFirst provideLoadfMore(){
        return new LoadFirst();
    }
}
