package nb.scode.a3rapps.ui.cart;

import dagger.Module;
import dagger.Provides;

/**
 * Created by neobyte on 2/11/2017.
 */

@Module
public class CartPresenterModule {

    private CartContract.View view;

    public  CartPresenterModule(CartContract.View view){
        this.view = view;
    }

    @Provides
    CartContract.View providesCartView(){
        return view;
    }

}
