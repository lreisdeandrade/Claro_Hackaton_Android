package br.com.claro.hackaton.nfcservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Leandro on 22/02/2018.
 */

public class DiagnosticResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("body")
    @Expose
    private Diagnostic diagnostic;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Diagnostic getBody() {
        return diagnostic;
    }

    public void setBody(Diagnostic body) {
        this.diagnostic = body;
    }

}