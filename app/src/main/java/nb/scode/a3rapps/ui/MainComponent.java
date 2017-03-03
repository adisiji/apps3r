package nb.scode.a3rapps.ui;

import biz.laenger.android.together.BaseComponent;
import dagger.Subcomponent;

/**
 * Created by neobyte on 3/2/2017.
 */

@MainScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent extends BaseComponent<MainHomeActivity, MainPresenter> {
}
