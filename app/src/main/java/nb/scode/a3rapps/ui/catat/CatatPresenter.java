package nb.scode.a3rapps.ui.catat;

import java.util.List;

import javax.inject.Inject;

import io.realm.RealmResults;
import nb.scode.a3rapps.localdata.LocalDataRepo;
import nb.scode.a3rapps.localdata.LocalDataTask;
import nb.scode.a3rapps.modelretro.Colors;
import nb.scode.a3rapps.modelretro.DetailPackage;
import nb.scode.a3rapps.modelretro.Products;
import nb.scode.a3rapps.modelretro.Sizes;

/**
 * Created by neobyte on 2/13/2017.
 */

public class CatatPresenter implements CatatContract.Presenter {

    private final CatatContract.View mView;
    private final LocalDataTask dataRepo;

    @Inject
    public CatatPresenter(CatatContract.View view, LocalDataRepo localDataRepo){
        mView = view;
        mView.setPresenter(this);
        dataRepo = localDataRepo;
    }

    @Override
    public void start() {

    }

    @Override
    public List<Sizes> getSizesList() {
        return dataRepo.getListSizes();
    }

    @Override
    public List<Colors> getColorList() {
        return dataRepo.getListColors();
    }

    @Override
    public DetailPackage getDetailPackage(String id) {
        return dataRepo.getDetailPackage(id);
    }

    @Override
    public RealmResults<Products> getRealmResultsProduct(String id) {
        return dataRepo.getRealmResultProducts(id);
    }

    @Override
    public int getSaldo() {
        return dataRepo.getSaldo();
    }

    @Override
    public String getName() {
        return dataRepo.getName();
    }
}
