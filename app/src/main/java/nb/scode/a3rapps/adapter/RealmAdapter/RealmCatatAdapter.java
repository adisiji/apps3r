package nb.scode.a3rapps.adapter.RealmAdapter;

import android.content.Context;

import io.realm.RealmResults;
import nb.scode.a3rapps.modelretro.Products;

/**
 * Created by neobyte on 2/14/2017.
 */

public class RealmCatatAdapter extends RealmModelAdapter<Products> {

    public RealmCatatAdapter(Context context, RealmResults<Products> realmResults) {

        super(context, realmResults);
    }

}
