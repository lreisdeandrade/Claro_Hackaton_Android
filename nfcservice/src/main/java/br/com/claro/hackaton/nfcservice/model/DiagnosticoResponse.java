package br.com.claro.hackaton.nfcservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Leandro on 22/02/2018.
 */

public class DiagnosticoResponse {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("body")
    @Expose
    private Diagnostico body;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Diagnostico getBody() {
        return body;
    }

    public void setBody(Diagnostico body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "DiagnosticoResponse{" +
                "code=" + code +
                ", body=" + body +
                '}';
    }
}