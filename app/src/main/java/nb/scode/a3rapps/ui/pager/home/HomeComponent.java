package nb.scode.a3rapps.ui.pager.home;

import biz.laenger.android.together.BaseComponent;
import dagger.Subcomponent;

/**
 * Created by neobyte on 2/10/2017.
 */

@HomeScope
@Subcomponent(modules = HomeModule.class)
public interface HomeComponent extends BaseComponent<HomeFragment, HomePresenter> {

}
