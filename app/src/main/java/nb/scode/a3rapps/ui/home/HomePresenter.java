package nb.scode.a3rapps.ui.home;

import javax.inject.Inject;

import nb.scode.a3rapps.localdata.LocalDataRepo;
import nb.scode.a3rapps.localdata.LocalDataTask;

/**
 * Created by neobyte on 2/8/2017.
 */

public class HomePresenter implements HomeContract.Presenter {

    private final String TAG = HomePresenter.class.getSimpleName();
    private final HomeContract.View view;
    private final LocalDataTask dataRepo;

    @Inject
    public HomePresenter(HomeContract.View view, LocalDataRepo dataRepo){
        this.view = view;
        this.view.setPresenter(this);
        this.dataRepo = dataRepo;
    }

    @Override
    public void start(){
        if(dataRepo.getMeta()){
             //sudah ada, bandingkan dengan proses di cache
            if(dataRepo.isEmptyLocalStamp()){
                view.showFirstUpdate("Update first data");
            }
            else {
                getStamp();
            }
        }
        else {
            view.goLogin();
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
                view.showUpdateJneStatis("Update JNE & Static data");
            }

            @Override
            public void updateJne() {
                view.showUpdateJne("Update JNE Data");
            }

            @Override
            public void updateStatis() {
                view.showUpdateStatis("Update Static Data");
            }

            @Override
            public void success() {
                view.showSuccessUpdate("Update success");
                getPaket();
            }

            @Override
            public void failed(String message) {
                view.showFailedUpdate(message);
                view.goLogin();
            }
        });
    }

    private void getPaket(){
        dataRepo.getDaftarPaket(new LocalDataTask.LoadTaskCallback() {
            @Override
            public void success() {

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
                view.showSuccessUpdate("Success get Static Data");
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
                view.showSuccessUpdate("Success get JNE Data");
            }

            @Override
            public void failed(String message) {

            }
        });
    }

}
