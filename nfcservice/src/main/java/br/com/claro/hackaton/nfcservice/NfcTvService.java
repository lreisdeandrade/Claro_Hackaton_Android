package br.com.claro.hackaton.nfcservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.claro.hackaton.nfcservice.model.DiagnosticResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Leandro on 22/02/2018.
 */

public interface NfcTvService {

    @POST("api/diagnostico")
    Call<DiagnosticResponse> getDiagnostic(@Body HashMap<String, String> decoderInfo);

//    @GET("gists/{id}")
//    Observable<Gist> gist(@Path("id") String id);
}
