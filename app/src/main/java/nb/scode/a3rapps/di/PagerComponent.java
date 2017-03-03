package nb.scode.a3rapps.di;

import dagger.Component;
import nb.scode.a3rapps.di.modules.PagerModule;
import nb.scode.a3rapps.di.scope.Pager;
import nb.scode.a3rapps.localdata.LocalDataComponent;
import nb.scode.a3rapps.ui.pager.cart.CartComponent;
import nb.scode.a3rapps.ui.pager.cart.CartModule;
import nb.scode.a3rapps.ui.pager.home.HomeComponent;
import nb.scode.a3rapps.ui.pager.home.HomeModule;
import nb.scode.a3rapps.ui.pager.profile.ProfileComponent;
import nb.scode.a3rapps.ui.pager.profile.ProfileModule;

/**
 * Created by neobyte on 3/3/2017.
 */
@Pager
@Component(dependencies = LocalDataComponent.class, modules = PagerModule.class)
public interface PagerComponent {

    HomeComponent mHomeComponent(HomeModule homePresenterModule);

    CartComponent mCartComponent(CartModule cartPresenterModule);

    ProfileComponent mProfileComponent(ProfileModule profileModule);

}
