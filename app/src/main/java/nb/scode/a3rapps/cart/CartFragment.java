package nb.scode.a3rapps.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.RealmExpandableRecyclerAdapter;
import io.realm.RealmResults;
import io.realm.Sort;
import mehdi.sakout.fancybuttons.FancyButton;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.adapter.ExpandCartAdapter;
import nb.scode.a3rapps.catat.CatatActivity;
import nb.scode.a3rapps.dialogs.PindahDialogFragment;
import nb.scode.a3rapps.modelretro.DetailPackage;
import nb.scode.a3rapps.modelretro.Products;

import static android.view.View.GONE;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by neobyte on 2/11/2017.
 */

public class CartFragment extends Fragment implements CartContract.View, PindahDialogFragment.DialogCallback {

    private ExpandCartAdapter adapter;
    private CartContract.Presenter mPresenter;
    private Unbinder unbinder;
    private String id;

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
    @BindView(R.id.switch_urutkan)
    SwitchCompat btnSwitch;

    @Override
    public void pindah() {
        Log.d("Pindah","GOGOGO");
    }

    @Override
    public RealmResults<Products> getProduct(String id) {
        return mPresenter.getRealmResultsProduct(id);
    }

    @Override
    public String versiProduct(String id) {
        return null;
    }

    @Override
    public String colorProduct(String id) {
        return mPresenter.getProductColor(id);
    }

    @Override
    public String sizeProduct(String id) {
        return mPresenter.getProductSize(id);
    }

    @Override
    public String nameProduct(String id) {
        return mPresenter.getProductName(id);
    }

    @Override
    public List<String> listPenerima() {
        List<String> hasil = new ArrayList<>();
        List<DetailPackage> detailPackageList = mPresenter.getRealmResultDetailPackage();
        for(DetailPackage detailPackage: detailPackageList){
            if(detailPackage.getKeranjang().equals("0")){
                hasil.add(detailPackage.getRecipientDetailList().getName());
            }
        }
        return hasil;
    }

    ExpandCartAdapter.ProductEvent productEvent = new ExpandCartAdapter.ProductEvent() {
        @Override
        public String nameProduct(String id) {
            return mPresenter.getProductName(id);
        }

        @Override
        public String versiProduct(String id) {
            return null;
        }

        @Override
        public String colorProduct(String id) {
            return mPresenter.getProductColor(id);
        }

        @Override
        public String sizeProduct(String id) {
            return mPresenter.getProductSize(id);
        }

        @Override
        public void pindahProduct(DetailPackage detailPackage) {
            showDialog(detailPackage.getPackaged());
        }
    };

    ExpandCartAdapter.CartEvent cartEvent = new ExpandCartAdapter.CartEvent() {
        @Override
        public void editPenerima(String pos) {
            Intent intent = new Intent(getContext(), CatatActivity.class);
            intent.putExtra("id",pos);
            startActivity(intent);
        }

        @Override
        public int reqProduct(String id) {
            return mPresenter.getAvailProduct(id);
        }

        @Override
        public int availProduct(String id) {
            return mPresenter.getReqPrduct(id);
        }

        @Override
        public void konfirmPenerima(int pos) {

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        adapter = new ExpandCartAdapter(mPresenter.getRealmResultDetailPackage(), getContext(),cartEvent, productEvent);

        adapter.setExpandCollapseListener(new RealmExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onParentExpanded(int parentPosition) {

            }

            @Override
            public void onParentCollapsed(int parentPosition) {

            }
        });
        rvCart.setItemAnimator(new DefaultItemAnimator());
        rvCart.setLayoutManager(layoutManager);
        rvCart.setAdapter(adapter);
        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    adapter.updateData(mPresenter.getRealmResultDetailPackage().sort("modified", Sort.DESCENDING));
                }
                else {
                    adapter.updateData(mPresenter.getRealmResultDetailPackage());
                }
            }
        });
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

    private void showDialog(String id){
        PindahDialogFragment pindahDialogFragment = PindahDialogFragment.newInstance(id,8,8f,true,true);
        pindahDialogFragment.setTargetFragment(this,1);
        pindahDialogFragment.show(getFragmentManager(),"dialog");
    }

}
