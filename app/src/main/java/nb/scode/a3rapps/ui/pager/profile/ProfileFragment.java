package nb.scode.a3rapps.ui.pager.profile;

import nb.scode.a3rapps.App;
import nb.scode.a3rapps.BasePagerFragment;

/**
 * Created by neobyte on 3/3/2017.
 */

public class ProfileFragment extends BasePagerFragment<ProfileView, ProfilePresenter, ProfileComponent> implements ProfileView {

    @Override
    public Class<? extends ProfilePresenter> getTypeClazz() {

        return ProfilePresenter.class;
    }

    @Override
    protected ProfileComponent createComponent() {

        return App.getPagerComponent().mProfileComponent(new ProfileModule());
    }
}
