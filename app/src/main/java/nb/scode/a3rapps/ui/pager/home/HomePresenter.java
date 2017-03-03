package nb.scode.a3rapps.ui.pager.home;

import javax.inject.Inject;

import biz.laenger.android.together.BasePresenter;
import nb.scode.a3rapps.localdata.LocalDataTask;
import nb.scode.a3rapps.ui.pager.LoadFirst;

/**
 * Created by neobyte on 2/8/2017.
 */

@HomeScope
public class HomePresenter extends BasePresenter<HomeContract.View,HomeComponent> implements HomeContract.Presenter {

    private final String TAG = HomePresenter.class.getSimpleName();

    @Inject
    LocalDataTask dataRepo;

    @Inject
    LoadFirst mLoadFirst;

    @Inject
    public HomePresenter(){

    }

    @Override
    public void bindView(HomeContract.View view) {
        super.bindView(view);
        if(!mLoadFirst.isLoaded()){
            start();
            mLoadFirst.setLoaded(true);
        }

    }


    @Override
    public void start(){

        if(dataRepo.getMeta()){
             //sudah ada, bandingkan dengan proses di cache
            if(dataRepo.isEmptyLocalStamp()){
                getView().showFirstUpdate("Update first data");
            }
            else {
                getStamp();
            }
        }
        else {
            getView().goLogin();
        }
    }

    @Override
    public void getFirstData() {
        getDataStatis();
        getDataJne();
        getStamp();
    }

    @Override
    public void getStamp() {
        dataRepo.getStamp(new LocalDataTask.UpdateCacheCallback() {

            @Override
            public void updateJneStatis() {
                getView().showUpdateJneStatis("Update JNE & Static data");
            }

            @Override
            public void updateJne() {
                getView().showUpdateJne("Update JNE Data");
            }

            @Override
            public void updateStatis() {
                getView().showUpdateStatis("Update Static Data");
            }

            @Override
            public void success() {
                getPaket();
                getView().showSuccessUpdate("Update success");
            }

            @Override
            public void failed(String message) {
                getView().showFailedUpdate(message);
                getView().goLogin();
            }
        });
    }

    private void getPaket(){
        dataRepo.getDaftarPaket(new LocalDataTask.LoadTaskCallback() {
            @Override
            public void success() {
                getView().finishGetData();
            }

            @Override
            public void failed(String message) {

            }
        });
    }

    @Override
    public void getDataStatis() {
        dataRepo.getDataStatis(new LocalDataTask.LoadTaskCallback() {
            @Override
            public void success() {
                getView().showSuccessUpdate("Success get Static Data");
            }

            @Override
            public void failed(String message) {

            }
        });
    }

    @Override
    public void getDataJne() {
        dataRepo.getDataJne(new LocalDataTask.LoadTaskCallback() {
            @Override
            public void success() {
                getView().showSuccessUpdate("Success get JNE Data");
            }

            @Override
            public void failed(String message) {

            }
        });
    }

}
