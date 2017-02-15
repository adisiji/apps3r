package nb.scode.a3rapps.network;

import io.reactivex.Observable;
import nb.scode.a3rapps.modelretro.DataJne;
import nb.scode.a3rapps.modelretro.DataStatis;
import nb.scode.a3rapps.modelretro.MainPackages;
import nb.scode.a3rapps.modelretro.StampsRetro;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by neobyte on 2/8/2017.
 */

public interface ApiService {

    String BASE_URL = "https://tigaer.id/";

    @GET("api?method=ambilMeta") //return --> Stamps
    Observable<StampsRetro> ambilMeta(@Query("uid") int uid,
                                      @Query("key") String key);

    @GET("api?method=ambilDaftarPaket") //return --> list packages
    Observable<MainPackages> ambilDaftarPaket(@Query("uid") int uid,
                                              @Query("key") String key);

    @GET("api?method=ambilDataStatis") //return --> list data statis
    Observable<DataStatis> ambilDataStatis(@Query("uid") int uid,
                                           @Query("key") String key);

    @GET("api?method=ambilDataJne")
    Observable<DataJne> ambilDataJne(@Query("uid") int uid,
                                     @Query("key") String key);
}
