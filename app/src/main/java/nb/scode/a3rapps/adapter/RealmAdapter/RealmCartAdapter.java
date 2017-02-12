package nb.scode.a3rapps.adapter.RealmAdapter;

import android.content.Context;

import io.realm.RealmResults;
import nb.scode.a3rapps.modelretro.DetailPackage;

/**
 * Created by neobyte on 2/12/2017.
 */

public class RealmCartAdapter extends RealmModelAdapter<DetailPackage> {

    public RealmCartAdapter(Context context, RealmResults<DetailPackage> realmResults) {

        super(context, realmResults);
    }

}
