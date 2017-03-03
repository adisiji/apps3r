package nb.scode.a3rapps.ui;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import nb.scode.a3rapps.BasePagerFragment;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.ui.pager.cart.CartFragment;
import nb.scode.a3rapps.ui.pager.home.HomeFragment;

/**
 * Created by neobyte on 3/2/2017.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private int[] mTabsIcons = {
            R.drawable.home,
            R.drawable.profile,
            R.drawable.bell,
            R.drawable.adjust};

    private enum TabItem {
        HOME(HomeFragment.class, R.string.title_home),
        CART(CartFragment.class, R.string.title_cart); /*
        ,

        PROFILE(CountingFragment.class, R.string.title_profile),
        MORE(ShiftingFragment.class, R.string.title_more);
        */

        private final Class<? extends BasePagerFragment<?, ?, ?>> fragmentClass;
        private final int titleResId;

        TabItem(Class<? extends BasePagerFragment<?, ?, ?>> fragmentClass, @StringRes int titleResId) {
            this.fragmentClass = fragmentClass;
            this.titleResId = titleResId;
        }
    }

    private final TabItem[] tabItems = TabItem.values();
    private final Context context;

    MainPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return newInstance(tabItems[position].fragmentClass);
    }

    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        Glide.with(context)
                .load(mTabsIcons[position])
                .fitCenter()
                .into(icon);
        return view;
    }

    private Fragment newInstance(Class<? extends Fragment> fragmentClass) {
        try {
            return fragmentClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("fragment must have public no-arg constructor: " + fragmentClass.getName(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("fragment must have public no-arg constructor: " + fragmentClass.getName(), e);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(tabItems[position].titleResId);
    }



    @Override
    public int getCount() {
        return tabItems.length;
    }

}