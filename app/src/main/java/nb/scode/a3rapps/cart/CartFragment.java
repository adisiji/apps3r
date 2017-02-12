package nb.scode.a3rapps.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import nb.scode.a3rapps.App;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.adapter.CartListAdapter;
import nb.scode.a3rapps.adapter.RealmAdapter.RealmCartAdapter;
import nb.scode.a3rapps.modelretro.DetailPackage;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by neobyte on 2/11/2017.
 */

public class CartFragment extends Fragment implements CartContract.View {

    private CartListAdapter adapter;
    private CartContract.Presenter mPresenter;
    private Unbinder unbinder;
    @BindView(R.id.rvCart)
    RecyclerView rvCart;

    CartListAdapter.CartEvent event = new CartListAdapter.CartEvent() {
        @Override
        public void editPenerima(int pos) {

        }

        @Override
        public void konfirmPenerima(int pos) {

        }

        @Override
        public void editKeranjang(int pos) {

        }
    };

    public CartFragment() {
    }

    public static CartFragment newInstance(){
        return new CartFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this,root);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new CartListAdapter(getContext(), event);
        rvCart.setLayoutManager(layoutManager);
        rvCart.setAdapter(adapter);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        RealmCartAdapter realmCartAdapter = new RealmCartAdapter(getContext(),
                mPresenter.getRealmResultDetailPackage());
        adapter.setRealmAdapter(realmCartAdapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(CartContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter,"Presenter cannot be null");
    }
}
