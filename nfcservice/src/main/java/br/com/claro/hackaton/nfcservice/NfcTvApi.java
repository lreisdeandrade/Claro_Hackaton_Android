package br.com.claro.hackaton.nfcservice;

import java.util.HashMap;

import br.com.claro.hackaton.nfcservice.model.DiagnosticoResponse;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Leandro on 22/02/2018.
 */

public class NfcTvApi implements NfcTvDataSource {
    private static NfcTvApi instance;
    private final NfcTvService nfcService;

    private NfcTvApi() {
        Retrofit retrofit = NfcApiModule.getRetrofit();
        nfcService = retrofit.create(NfcTvService.class);
    }

    public static NfcTvApi getInstance() {
        if (instance == null) {
            instance = new NfcTvApi();
        }
        return instance;
    }

    @Override
    public Call<DiagnosticoResponse> getDiagnostic(HashMap<String, String> decoderInfo) {
        return nfcService.getDiagnostic(decoderInfo);
    }


}