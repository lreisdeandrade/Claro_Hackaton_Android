package br.com.claro.hackaton.android;


import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.claro.hackaton.nfcservice.NfcTvApi;
import br.com.claro.hackaton.nfcservice.model.DiagnosticResponse;
import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by Leandro on 22/02/2018.
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private NfcAdapter mNfcAdapter;
    private String cpf;
    private String contrato;
    private String macAddress;
    private String enderecavel;

    @AfterViews
    protected void onCreate() {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        String nfcMessage = NFCUtil.INSTANCE.retrieveNFCMessage(this.getIntent());

        String[] splitedMessage = splitString(nfcMessage);

        if (splitedMessage.length >= 4) {
            cpf = splitedMessage[0];
            contrato = splitedMessage[1];
            macAddress = splitedMessage[2];
            enderecavel = splitedMessage[3];
//

            Timber.d("Executing diagnostic");
            getDiagnosticTv();
//
        }
    }

    protected String[] splitString(String nfcMessage) {

        String[] splitMessage = nfcMessage.split("\\|");

        return splitMessage;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mNfcAdapter != null) {
            NFCUtil.INSTANCE.enableNFCInForeground(mNfcAdapter, this, MainActivity.class);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mNfcAdapter != null) {
            NFCUtil.INSTANCE.disableNFCInForeground(mNfcAdapter, this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    public void getDiagnosticTv() {

        HashMap<String, String> map = new HashMap<>();

        map.put("enderecavel", enderecavel);
        NfcTvApi.getInstance().getDiagnostic(map).enqueue(new retrofit2.Callback<DiagnosticResponse>() {
            @Override
            public void onResponse(@NonNull Call<DiagnosticResponse> call, @NonNull Response<DiagnosticResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                    Timber.d("Diagnostic %s", response.body().toString());
            }

            @Override
            public void onFailure(Call<DiagnosticResponse> call, Throwable t) {

            }
        });
    }
}

//    private var mNfcAdapter: NfcAdapter? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
//        val nfcMessage = NFCUtil.retrieveNFCMessage(this.intent)
//        val splitedMessage = splitString(nfcMessage)
//
//        if (splitedMessage.size == 4) {
//            val cpf = splitedMessage.get(0)
//            val contrato = splitedMessage.get(1)
//            val macAddress = splitedMessage.get(2)
//            val enderecavel = splitedMessage.get(3)
//
//            toast(enderecavel);
//
//        }
//    }
//
//    fun splitString(nfcMessage: String): List<String> {
//
//        val splitMessage = nfcMessage.split("|")
//
//        return splitMessage
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        mNfcAdapter?.let {
//            NFCUtil.enableNFCInForeground(it, this, javaClass)
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//        mNfcAdapter?.let {
//            NFCUtil.disableNFCInForeground(it, this)
//        }
//    }
//
//    override fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
////        val messageWrittenSuccessfully = NFCUtil.createNFCMessage(messageEditText.text.toString(), intent)
////        toast(messageWrittenSuccessfully.ifElse("Successful Written to Tag", "Something When wrong Try Again"))
//    }
//}
//
//    fun <T> Boolean.ifElse(primaryResult: T, secondaryResult: T) = if (this) primaryResult else secondaryResult