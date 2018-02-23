package br.com.claro.hackaton.nfcservice;

import java.util.HashMap;
import java.util.List;

import br.com.claro.hackaton.nfcservice.model.DiagnosticResponse;
import retrofit2.Call;

/**
 * Created by Leandro on 22/02/2018.
 */

public interface NfcTvDataSource {

    Call<DiagnosticResponse> getDiagnostic(HashMap<String, String> decoderInfo);

}
