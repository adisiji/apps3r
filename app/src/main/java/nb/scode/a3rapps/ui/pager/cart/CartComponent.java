package nb.scode.a3rapps.ui.pager.cart;

import biz.laenger.android.together.BaseComponent;
import dagger.Subcomponent;

/**
 * Created by neobyte on 2/11/2017.
 */

@CartScope
@Subcomponent(modules = CartModule.class)
public interface CartComponent extends BaseComponent<CartFragment, CartPresenter>{

}
