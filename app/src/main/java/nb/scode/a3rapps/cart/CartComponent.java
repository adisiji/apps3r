package nb.scode.a3rapps.cart;

import dagger.Component;
import nb.scode.a3rapps.di.scope.FragmentScoped;
import nb.scode.a3rapps.localdata.LocalDataComponent;

/**
 * Created by neobyte on 2/11/2017.
 */

@FragmentScoped
@Component(dependencies = LocalDataComponent.class, modules = CartPresenterModule.class)
public interface CartComponent {

    void inject(CartActivity cartActivity);

}
