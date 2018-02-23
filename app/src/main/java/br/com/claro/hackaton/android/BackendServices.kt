package br.com.claro.hackaton.android;

import okhttp3.OkHttpClient;
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject



/**
 * Created by Leandro on 22/02/2018.
 */

class BackendServices {

    fun diagnostico(enderecavel: String): String? {
        val baseURL = "https://service.us.apiconnect.ibmcloud.com/gws/apigateway/api/4d88866c15adf934a4964c33d7c9f62d5610f9defd2227e9a391adaa690cc96c/hackathon"
        val client = OkHttpClient()

        val request = Request.Builder()
                .url(baseURL + "/api/diagnostico")
                .build()

        val response = client.newCall(request).execute()
        val jsonData = response.body()?.string()

        val jObject = JSONObject(jsonData)
        val responseCode = jObject.getString("code")
        val diagnosticoBody = jObject.getJSONObject("body").getJSONObject("diagnostico")

        val diagnosticoCode = diagnosticoBody.getString("codigo")
        val diagnosticoDescricao = diagnosticoBody.getString("descricao")

        return diagnosticoCode
    }
}
