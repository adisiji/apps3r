package nb.scode.a3rapps.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import io.realm.RealmList;
import nb.scode.a3rapps.modelretro.RealmInteger;

/**
 * Created by neobyte on 2/12/2017.
 */

public class RealmIntegerListTypeAdapter extends TypeAdapter<RealmList<RealmInteger>> {

    public static final TypeAdapter<RealmList<RealmInteger>> INSTANCE =
            new RealmIntegerListTypeAdapter().nullSafe();

    private RealmIntegerListTypeAdapter() { }

    @Override public void write(JsonWriter out, RealmList<RealmInteger> src) throws IOException {
        out.beginArray();
        for(RealmInteger realmString : src) { out.value(realmString.value); }
        out.endArray();
    }

    @Override public RealmList<RealmInteger> read(JsonReader in) throws IOException {
        RealmList<RealmInteger> realmStrings = new RealmList<>();
        in.beginArray();
        while (in.hasNext()) {
            if(in.peek() == JsonToken.NULL) {
                in.nextNull();
            } else {
                RealmInteger realmString = new RealmInteger();
                realmString.value = in.nextInt();
                realmStrings.add(realmString);
            }
        }
        in.endArray();
        return realmStrings;
    }
    
}
