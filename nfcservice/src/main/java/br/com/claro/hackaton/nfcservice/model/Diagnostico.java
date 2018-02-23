package br.com.claro.hackaton.nfcservice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Leandro on 22/02/2018.
 */

public class Diagnostico  implements Serializable {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("descricao")
    @Expose
    private String descricao;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Diagnostico{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}



