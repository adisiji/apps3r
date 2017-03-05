package nb.scode.a3rapps.ui.pager.more;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import nb.scode.a3rapps.App;
import nb.scode.a3rapps.BasePagerFragment;
import nb.scode.a3rapps.R;

/**
 * Created by neobyte on 3/5/2017.
 */

public class MoreFragment extends BasePagerFragment<MoreContract.View,MorePresenter,MoreComponent> implements MoreContract.View {

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_more, container, false);
        mUnbinder = ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    protected MoreComponent createComponent() {

        return App.getPagerComponent().mMoreComponent(new MoreModule());

    }

    @Override
    public Class<? extends MorePresenter> getTypeClazz() {

        return MorePresenter.class;
    }
}
