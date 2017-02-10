package nb.scode.a3rapps.localdata;

import dagger.Component;
import nb.scode.a3rapps.di.AppComponent;
import nb.scode.a3rapps.di.scope.Local;

/**
 * Created by neobyte on 2/9/2017.
 */

@Local
@Component(dependencies = AppComponent.class, modules = {LocalDataModule.class})

public interface LocalDataComponent {

    LocalDataRepo getLocalRepo();

}
