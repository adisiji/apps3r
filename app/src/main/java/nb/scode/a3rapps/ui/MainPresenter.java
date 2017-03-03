package nb.scode.a3rapps.ui;

import javax.inject.Inject;

import biz.laenger.android.together.BasePresenter;

/**
 * Created by neobyte on 3/2/2017.
 */
@MainScope
public class MainPresenter extends BasePresenter<MainView, MainComponent> {

    private int lastPagerItemPosition = 0;

    @Inject
    MainPresenter() {
        // ;
    }

    @Override
    public void bindView(MainView view) {
        super.bindView(view);
        view.showPagerItem(lastPagerItemPosition);
    }

    void onPagerItemSelected(int position) {
        lastPagerItemPosition = position;
    }

}
