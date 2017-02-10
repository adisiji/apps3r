package nb.scode.a3rapps.login;

import dagger.Module;
import dagger.Provides;

/**
 * Created by neobyte on 2/10/2017.
 */

@Module
public class LoginPresenterModule {

    private LoginContract.View view;

    public LoginPresenterModule(LoginContract.View view){
        this.view = view;
    }

    @Provides
    public LoginContract.View providesLoginView(){
        return view;
    }

}
