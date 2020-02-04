package com.zaidimarvels.voiceapp;

import android.content.Intent;
import android.os.Build;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Formulaire extends AppCompatActivity {
    private TextToSpeech tts;
    private SpeechRecognizer speechRecog;
    private EditText text1,text2,text3,text4;
    private static  List<String> sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_formulaire );


        text1=findViewById( R.id.editText );
        text2=findViewById( R.id.editText2 );
        text3=findViewById( R.id.editText3 );
        text4=findViewById( R.id.editText4 );

        initializeSpeechRecognizer();
        initializeTextToSpeech();


    }

    private void initializeSpeechRecognizer() {
        if (SpeechRecognizer.isRecognitionAvailable( this )) {
            speechRecog = SpeechRecognizer.createSpeechRecognizer( this );
            speechRecog.setRecognitionListener( new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {

                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float rmsdB) {

                }

                @Override
                public void onBufferReceived(byte[] buffer) {




                }

                @Override
                public void onEndOfSpeech() {

                }

                @Override
                public void onError(int error) {

                }

                @Override
                public void onResults(Bundle results) {
                    List<String> speet = results.getStringArrayList( SpeechRecognizer.RESULTS_RECOGNITION );
                      text1.setText(speet.get(0));
                      onStart();
                    if(text1.getText().toString()!=null){
                        text3.setText(speet.get(1));


                    }
                }

                @Override
                public void onPartialResults(Bundle partialResults) {

                }

                @Override
                public void onEvent(int eventType, Bundle params) {

                }
            } );
        }
    }

    private void initializeTextToSpeech() {
        tts = new TextToSpeech( this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (tts.getEngines().size() == 0) {
                    Toast.makeText( Formulaire.this, getString( R.string.tts_no_engines ), Toast.LENGTH_LONG ).show();
                    finish();
                } else {
                    tts.setLanguage( Locale.US );
                    speak( "Complete the name" );
                }

            }
        } );
    }

    private void speak(String message) {
        if (Build.VERSION.SDK_INT >= 21) {
            tts.speak( message, TextToSpeech.QUEUE_FLUSH, null, null );
        } else {
            tts.speak( message, TextToSpeech.QUEUE_FLUSH, null );
        }
        Intent intent = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
        speechRecog.startListening(intent);
    }












}



