package nb.scode.a3rapps.ui.pager.cart;

import javax.inject.Inject;

import biz.laenger.android.together.BasePresenter;
import io.realm.RealmResults;
import nb.scode.a3rapps.localdata.LocalDataTask;
import nb.scode.a3rapps.modelretro.DetailPackage;
import nb.scode.a3rapps.modelretro.Products;
import nb.scode.a3rapps.ui.pager.LoadFirst;

/**
 * Created by neobyte on 2/11/2017.
 */

@CartScope
public class CartPresenter extends BasePresenter<CartContract.View,CartComponent> implements CartContract.Presenter {

    private final String TAG = CartPresenter.class.getSimpleName();

    @Inject
    LoadFirst mLoadFirst;

    @Inject
    LocalDataTask dataRepo;

    @Inject
    public CartPresenter(){

    }

    @Override
    public void bindView(CartContract.View view) {
        super.bindView(view);
        if(mLoadFirst.isLoaded()){
            getView().setView();
        }
        else {
            mLoadFirst.setLoaded(true);
        }
    }

    @Override
    public String getName() {
        return dataRepo.getName();
    }

    @Override
    public String getProductName(String id) {
        return dataRepo.getProductName(id);
    }

    @Override
    public String getProductSize(String id) {
        return dataRepo.getProductSize(id);
    }

    @Override
    public String getProductColor(String id) {
        return dataRepo.getProductColor(id);
    }

    @Override
    public int getTimeLimit() {
        return dataRepo.getTimeLimit();
    }

    @Override
    public int getReqCount() {
        return dataRepo.getReqCount();
    }

    @Override
    public int getReqLimit() {
        return dataRepo.getReqLimit();
    }

    @Override
    public RealmResults<DetailPackage> getRealmResultDetailPackage() {
        return dataRepo.getRealmResultDetailPackage();
    }

    @Override
    public RealmResults<Products> getRealmResultsProduct(String id) {
        return dataRepo.getRealmResultProducts(id);
    }

    @Override
    public long getSaldo() {
        return dataRepo.getSaldo();
    }

    @Override
    public int getAvailProduct(String id) {
        return dataRepo.getAvailProduct(id);
    }

    @Override
    public int getReqPrduct(String id) {
        return dataRepo.getReqProduct(id);
    }

}
