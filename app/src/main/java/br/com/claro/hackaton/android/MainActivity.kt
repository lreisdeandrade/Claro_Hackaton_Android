package br.com.claro.hackaton.android

import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.toast


/**
 * Created by Leandro on 22/02/2018.
 */
class MainActivity : AppCompatActivity() {

    private var mNfcAdapter: NfcAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        val nfcMessage = NFCUtil.retrieveNFCMessage(this.intent)
        val splitedMessage = splitString(nfcMessage)

        if (splitedMessage.size == 4) {
            val cpf = splitedMessage.get(0)
            val contrato = splitedMessage.get(1)
            val macAddress = splitedMessage.get(2)
            val enderecavel = splitedMessage.get(3)

            toast(enderecavel);

        }
    }

    fun splitString(nfcMessage: String): List<String> {

        val splitMessage = nfcMessage.split("|")

        return splitMessage

    }

    override fun onResume() {
        super.onResume()
        mNfcAdapter?.let {
            NFCUtil.enableNFCInForeground(it, this, javaClass)
        }
    }

    override fun onPause() {
        super.onPause()
        mNfcAdapter?.let {
            NFCUtil.disableNFCInForeground(it, this)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
//        val messageWrittenSuccessfully = NFCUtil.createNFCMessage(messageEditText.text.toString(), intent)
//        toast(messageWrittenSuccessfully.ifElse("Successful Written to Tag", "Something When wrong Try Again"))
    }
}

fun <T> Boolean.ifElse(primaryResult: T, secondaryResult: T) = if (this) primaryResult else secondaryResult