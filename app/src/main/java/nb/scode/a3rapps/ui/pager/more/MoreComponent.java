package nb.scode.a3rapps.ui.pager.more;

import biz.laenger.android.together.BaseComponent;
import dagger.Subcomponent;

/**
 * Created by neobyte on 3/5/2017.
 */

@MoreScope
@Subcomponent(modules = MoreModule.class)
public interface MoreComponent extends BaseComponent<MoreContract.View, MorePresenter> {

}
