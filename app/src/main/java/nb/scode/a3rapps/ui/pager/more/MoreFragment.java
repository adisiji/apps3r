package nb.scode.a3rapps.ui.pager.more;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import nb.scode.a3rapps.App;
import nb.scode.a3rapps.BasePagerFragment;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.adapter.MoreMenuAdapter;

/**
 * Created by neobyte on 3/5/2017.
 */

public class MoreFragment extends BasePagerFragment<MoreContract.View,MorePresenter,MoreComponent> implements MoreContract.View {

    private Unbinder mUnbinder;
    @BindView(R.id.rv_more_item)
    RecyclerView mRecyclerView;
    @BindDrawable(R.drawable.ic_menu_grey_300_36dp)
    Drawable mDrawable;
    private MoreMenuAdapter mMoreMenuAdapter;
    private List<Integer> listImage = new ArrayList<>();
    private List<String>  listTitle = new ArrayList<>();

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_more, container, false);
        mUnbinder = ButterKnife.bind(this,root);
        listImage = Arrays.asList(R.drawable.ic_check_light_green_a700_36dp,R.drawable.ic_watch_later_light_green_a700_24dp);
        listTitle = Arrays.asList("Catat","Konfirmasi");
        mMoreMenuAdapter = new MoreMenuAdapter(listImage,listTitle,getContext());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mDrawable));
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mMoreMenuAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private class DividerItemDecoration extends RecyclerView.ItemDecoration {

        private Drawable mDivider;

        public DividerItemDecoration(Drawable divider) {

            mDivider = divider;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            if (parent.getChildAdapterPosition(view) == 0) {
                return;
            }

            outRect.top = mDivider.getIntrinsicHeight();
        }

        @Override
        public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            int dividerLeft = parent.getPaddingLeft();
            int dividerRight = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int dividerTop = child.getBottom() + params.bottomMargin;
                int dividerBottom = dividerTop + mDivider.getIntrinsicHeight();

                mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
                mDivider.draw(canvas);
            }
        }
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
