package nb.scode.a3rapps.modelrealm;

import android.support.annotation.NonNull;

/**
 * Created by neobyte on 2/8/2017.
 */

public class SyncEvent {

    private String message;

    public SyncEvent(@NonNull String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
