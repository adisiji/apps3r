package nb.scode.a3rapps.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by neobyte on 2/8/2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface Local {
}
