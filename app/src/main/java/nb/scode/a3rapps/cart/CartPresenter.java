package nb.scode.a3rapps.cart;

import javax.inject.Inject;

import io.realm.RealmResults;
import nb.scode.a3rapps.localdata.LocalDataRepo;
import nb.scode.a3rapps.localdata.LocalDataTask;
import nb.scode.a3rapps.modelretro.DetailPackage;

/**
 * Created by neobyte on 2/11/2017.
 */

public class CartPresenter implements CartContract.Presenter {

    private final CartContract.View mView;
    private final LocalDataTask dataRepo;

    @Inject
    public CartPresenter(CartContract.View view, LocalDataRepo localDataRepo){
        mView = view;
        mView.setPresenter(this);
        dataRepo = localDataRepo;
    }

    @Override
    public void start() {

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
