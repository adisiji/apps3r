package nb.scode.a3rapps.ui.pager.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.RealmResults;
import io.realm.Sort;
import mehdi.sakout.fancybuttons.FancyButton;
import nb.scode.a3rapps.App;
import nb.scode.a3rapps.BasePagerFragment;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.adapter.ExpandCartAdapter;
import nb.scode.a3rapps.modelretro.DetailPackage;
import nb.scode.a3rapps.modelretro.Products;
import nb.scode.a3rapps.ui.catat.CatatActivity;
import nb.scode.a3rapps.ui.dialogs.PindahDialogFragment;
import nb.scode.a3rapps.ui.main.MainHomeActivity;

import static android.view.View.GONE;

/**
 * Created by neobyte on 2/11/2017.
 */

public class CartFragment extends BasePagerFragment<CartContract.View, CartPresenter, CartComponent>
        implements CartContract.View, PindahDialogFragment.DialogCallback, MainHomeActivity.CallbackCart {

    private final String TAG = CartFragment.class.getSimpleName();

    private ExpandCartAdapter adapter;
    private Unbinder unbinder;

    @BindView(R.id.progbar_load_cart)
    ProgressBar mProgressBar;
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
    public void finishGetData() {
        setView();
    }

    public CartFragment() {

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setAutoMeasureEnabled(true);
        showProgressDialog();
        rvCart.setLayoutManager(layoutManager);
        return root;
    }

    void showProgressDialog(){

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainHomeActivity mHomeActivity = (MainHomeActivity)getActivity();
        mHomeActivity.setCallbackCart(this);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setView() {

        adapter = new ExpandCartAdapter(getPresenter().getRealmResultDetailPackage(), getActivity() ,cartEvent, productEvent);

        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    adapter.updateData(getPresenter().getRealmResultDetailPackage().sort("modified", Sort.DESCENDING));
                }
                else {
                    adapter.updateData(getPresenter().getRealmResultDetailPackage());
                }
            }
        });
        rvCart.setAdapter(adapter);
        int a = getPresenter().getTimeLimit();
        int b = getPresenter().getReqCount();
        int c = getPresenter().getReqLimit();
        if(b>=c){
            btnPaketBaru.setVisibility(GONE);
        }
        progressBar.setProgress(b);
        progressBar.setMax(c);
        tvBatasSimpan.setText("Batas penyimpanan "+ String.valueOf(a) +" hari");
        tvTercatat.setText("Tercatat "+String.valueOf(b)+" dari maks "+
                String.valueOf(c)+" produk");

        if(rvCart.getVisibility() == View.GONE){
            rvCart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoad() {
        hideLoadCart();
    }

    @Override
    public void hideLoadCart() {
        if(mProgressBar.getVisibility() == View.VISIBLE){
            mProgressBar.setVisibility(GONE);
        }
    }

    @Override
    protected CartComponent createComponent() {

        return App.getPagerComponent().mCartComponent(new CartModule());
    }

    @Override
    public Class<? extends CartPresenter> getTypeClazz() {

        return CartPresenter.class;
    }

    @Override
    public void onPresenterProvided(CartPresenter presenter) {
        super.onPresenterProvided(presenter);
    }

    @Override
    public void pindah() {
        Log.d("Pindah","GOGOGO");
    }

    @Override
    public RealmResults<Products> getProduct(String id) {
        return getPresenter().getRealmResultsProduct(id);
    }

    @Override
    public String versiProduct(String id) {
        return null;
    }

    @Override
    public String colorProduct(String id) {
        return getPresenter().getProductColor(id);
    }

    @Override
    public String sizeProduct(String id) {
        return getPresenter().getProductSize(id);
    }

    @Override
    public String nameProduct(String id) {
        return getPresenter().getProductName(id);
    }

    @Override
    public List<String> listPenerima() {
        List<String> hasil = new ArrayList<>();
        List<DetailPackage> detailPackageList = getPresenter().getRealmResultDetailPackage();
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
            return getPresenter().getProductName(id);
        }

        @Override
        public String versiProduct(String id) {
            return null;
        }

        @Override
        public String colorProduct(String id) {
            return getPresenter().getProductColor(id);
        }

        @Override
        public String sizeProduct(String id) {
            return getPresenter().getProductSize(id);
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
            return getPresenter().getAvailProduct(id);
        }

        @Override
        public int availProduct(String id) {
            return getPresenter().getReqPrduct(id);
        }

        @Override
        public void konfirmPenerima(int pos) {

        }
    };

    private void showDialog(String id){
        PindahDialogFragment pindahDialogFragment = PindahDialogFragment.newInstance(id,8,8f,true,true);
        pindahDialogFragment.setTargetFragment(this,1);
        pindahDialogFragment.show(getFragmentManager(),"dialog");
    }

}
