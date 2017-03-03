package nb.scode.a3rapps.ui.pager.profile;

import biz.laenger.android.together.BaseComponent;
import dagger.Subcomponent;

/**
 * Created by neobyte on 3/3/2017.
 */
@ProfileScope
@Subcomponent(modules = ProfileModule.class)
public interface ProfileComponent extends BaseComponent<ProfileFragment, ProfilePresenter> {
}
