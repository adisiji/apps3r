package nb.scode.a3rapps.home;

import javax.inject.Inject;

import nb.scode.a3rapps.localdata.LocalDataRepo;
import nb.scode.a3rapps.localdata.LocalDataTask;

/**
 * Created by neobyte on 2/8/2017.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;
    private LocalDataRepo dataRepo;

    @Inject
    public HomePresenter(HomeContract.View view, LocalDataRepo dataRepo){
        this.view = view;
        this.view.setPresenter(this);
        this.dataRepo = dataRepo;
    }

    @Override
    public void start(){
        if(dataRepo.getMeta()){
            dataRepo.getStamp(new LocalDataTask.LoadTaskCallback(){
                @Override
                public void success(){

                }

                @Override
                public void failed(String message){

                }
            });
        }
        else {
            view.goLogin();
        }
    }

    @Override
    public void destroy(){
        view = null;
    }

}
