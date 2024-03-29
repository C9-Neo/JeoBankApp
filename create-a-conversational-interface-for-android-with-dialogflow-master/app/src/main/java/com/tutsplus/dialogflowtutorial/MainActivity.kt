package com.tutsplus.dialogflowtutorial

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.bassaer.chatmessageview.model.ChatUser
import com.github.bassaer.chatmessageview.model.Message
import com.github.bassaer.chatmessageview.view.ChatView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val REQ_CODE_SPEECH_INPUT = 100
    private var mVoiceInputTv: ChatView? = null


    companion object {
        private const val ACCESS_TOKEN = "6e0a0a5b00a142f8a1e5e478c85ba366"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?")
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {

        }


        FuelManager.instance.baseHeaders = mapOf(
                "Authorization" to "Bearer $ACCESS_TOKEN"
        )

        FuelManager.instance.basePath = "https://api.dialogflow.com/v1/"

        FuelManager.instance.baseParams = listOf(
                "v" to "20170712",                  // latest protocol
                "sessionId" to UUID.randomUUID(),   // random Id
                "lang" to "en"                      // English language
        )

        val human = ChatUser(
                1,
                "You",
                BitmapFactory.decodeResource(resources,
                        R.drawable.ic_account_circle)
        )

        val agent = ChatUser(
                2,
                "Agent",
                BitmapFactory.decodeResource(resources,
                        R.drawable.ic_account_circle)
        )

        my_chat_view.setOnClickSendButtonListener(View.OnClickListener {
            my_chat_view.send(Message.Builder()
                    .setUser(human)
                    .setText(my_chat_view.inputText)
                    .build()
            )
            Fuel.get("/query",
                    listOf("query" to my_chat_view.inputText))
                    .responseJson { _, _, result ->
                val reply = result.get().obj()
                                .getJSONObject("result")
                                .getJSONObject("fulfillment")
                                .getString("speech")

                my_chat_view.send(Message.Builder()
                        .setRight(true)
                        .setUser(agent)
                        .setText(reply)
                        .build()
                )

                val intent:String? = result.get().obj()
                        .getJSONObject("result")
                        .optJSONObject("metadata")
                        .optString("intentName")

                if(intent!! == "WEIGHT") {
                    val unitWeightName = result.get().obj()
                            .getJSONObject("result")
                            .getJSONObject("parameters")
                            .getString("unit-weight-name")

                    val unitWeight = result.get().obj()
                            .getJSONObject("result")
                            .getJSONObject("parameters")
                            .getJSONObject("unit-weight")
                            .getDouble("amount")

                    val result = if(unitWeightName == "lb") {
                        unitWeight * 2.20462
                    } else {
                        unitWeight / 2.20462
                    }

                    my_chat_view.send(Message.Builder()
                            .setRight(true)
                            .setUser(agent)
                            .setText("That's ${"%.2f".format(result)} $unitWeightName")
                            .build()
                    )
                }
            }
        })
    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == RESULT_OK && null != data) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    my_chat_view.inputText=result[0]
                }
            }
        }
    }
}
