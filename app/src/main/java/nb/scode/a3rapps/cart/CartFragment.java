package nb.scode.a3rapps.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mehdi.sakout.fancybuttons.FancyButton;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.adapter.CartListAdapter;
import nb.scode.a3rapps.adapter.RealmAdapter.RealmCartAdapter;
import nb.scode.a3rapps.catat.CatatActivity;

import static android.view.View.GONE;
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
    @BindView(R.id.tv_tercatat_maks_cart)
    TextView tvTercatat;
    @BindView(R.id.tv_batas_simpan)
    TextView tvBatasSimpan;
    @BindView(R.id.progbar_cart)
    RoundCornerProgressBar progressBar;
    @BindView(R.id.btn_paket_baru)
    FancyButton btnPaketBaru;

    CartListAdapter.CartEvent event = new CartListAdapter.CartEvent() {
        @Override
        public void editPenerima(String pos) {
            Intent intent = new Intent(getContext(), CatatActivity.class);
            intent.putExtra("id",pos);
            startActivity(intent);
        }

        @Override
        public int availProduct(String id) {
            return mPresenter.getAvailProduct(id);
        }

        @Override
        public int reqProduct(String id) {
            return mPresenter.getReqPrduct(id);
        }

        @Override
        public void konfirmPenerima(int pos) {

        }

    };

    CartListAdapter.KeranjangEvent keranjangEvent = new CartListAdapter.KeranjangEvent() {
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
        adapter = new CartListAdapter(getContext(), event, keranjangEvent);
        rvCart.setLayoutManager(layoutManager);
        rvCart.setAdapter(adapter);
        int a = mPresenter.getTimeLimit();
        int b = mPresenter.getReqCount();
        int c = mPresenter.getReqLimit();
        if(b>=c){
            btnPaketBaru.setVisibility(GONE);
        }
        progressBar.setProgress(b);
        progressBar.setMax(c);
        tvBatasSimpan.setText("Batas penyimpanan "+ String.valueOf(a) +" hari");
        tvTercatat.setText("Tercatat "+String.valueOf(b)+" dari maks "+
                String.valueOf(c)+" produk");
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
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(CartContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter,"Presenter cannot be null");
    }
}
