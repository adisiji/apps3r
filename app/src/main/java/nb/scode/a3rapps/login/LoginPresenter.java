package nb.scode.a3rapps.login;

import javax.inject.Inject;

import nb.scode.a3rapps.localdata.LocalDataRepo;
import nb.scode.a3rapps.localdata.LocalDataTask;

/**
 * Created by neobyte on 2/8/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private LocalDataRepo dataRepo;

    @Inject
    public LoginPresenter(LoginContract.View view, LocalDataRepo localDataRepo){
        this.view = view;
        this.view.setPresenter(this);
        dataRepo = localDataRepo;
    }

    @Override
    public void start(){

    }

    @Override
    public void destroy(){
        view = null;
    }

    @Override
    public void saveMeta(int uid, String key){
        dataRepo.setMeta(uid, key, new LocalDataTask.LoadTaskCallback() {
            @Override
            public void success() {
                view.goHome();
            }

            @Override
            public void failed(String message) {

            }
        });
    }

}
