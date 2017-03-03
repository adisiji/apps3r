package nb.scode.a3rapps.ui.pager.cart;

import dagger.Module;
import dagger.Provides;
import nb.scode.a3rapps.ui.pager.LoadFirst;

/**
 * Created by neobyte on 2/11/2017.
 */

@Module
public class CartModule {

    @CartScope
    @Provides
    LoadFirst provideLoadfCart(){
        return new LoadFirst();
    }

}
