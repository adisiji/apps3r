package nb.scode.a3rapps.catat;

import dagger.Component;
import nb.scode.a3rapps.di.scope.FragmentScoped;
import nb.scode.a3rapps.localdata.LocalDataComponent;

/**
 * Created by neobyte on 2/13/2017.
 */

@FragmentScoped
@Component(dependencies = LocalDataComponent.class, modules = CatatPresenterModule.class)
interface CatatComponent {

    void inject(CatatActivity activity);

}
