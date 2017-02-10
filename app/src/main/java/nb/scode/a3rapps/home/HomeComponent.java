package nb.scode.a3rapps.home;

import dagger.Component;
import nb.scode.a3rapps.di.scope.FragmentScoped;
import nb.scode.a3rapps.localdata.LocalDataComponent;

/**
 * Created by neobyte on 2/10/2017.
 */

@FragmentScoped
@Component(dependencies = LocalDataComponent.class, modules = HomePresenterModule.class)
public interface HomeComponent {

    void inject(HomeActivity homeActivity);

}
