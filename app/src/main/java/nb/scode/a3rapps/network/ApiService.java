package nb.scode.a3rapps.network;

import io.reactivex.Observable;
import nb.scode.a3rapps.modelretro.StampsRetro;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by neobyte on 2/8/2017.
 */

public interface ApiService {

    String BASE_URL = "https://dev.tigaer.id/";

    @GET("/api?method=ambilMeta") //return --> Stamps
    Observable<StampsRetro> ambilMeta(@Query("uid") int uid,
                                      @Query("key") String key);
}
