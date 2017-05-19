package com.development.mobile.andromeda.shimmer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText EtNickName;
    private EditText EtEmail;
    private EditText EtPass;

    private Button BtRegister;
    private Button GoToLogin;

    private TextView errorMsg;

    private String nickName;
    private String email;
    private String password;
    private ProgressDialog prgDialog;

    private int mCounter;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "counter";
    private SharedPreferences mSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EtNickName = (EditText) findViewById(R.id.registerName);
        EtEmail = (EditText) findViewById(R.id.registerEmail);
        EtPass = (EditText) findViewById(R.id.registerPassword);

        errorMsg = (TextView) findViewById(R.id.register_error);

        BtRegister = (Button) findViewById(R.id.btnRegister);
        BtRegister.setOnClickListener(this);

        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Подождите!");

        TextView link = (TextView) findViewById(R.id.contact_us);
        link.setOnClickListener(this);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnRegister:
            nickName = EtNickName.getText().toString();
            email = EtEmail.getText().toString();
            password = EtPass.getText().toString();
            invokeWS();
                break;
            case R.id.contact_us:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://exodus.1547.ru"));
                startActivity(browserIntent);
                break;
        }
    }

    private void goToLoginActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void invokeWS(){
        RequestParams params = new RequestParams();
        // Show Progress Dialog
        prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://195.19.44.155/anton/core/api.php?action=Registration", params, new JsonHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'

            public void onSuccess(int statusCode, Header[] headers, JSONObject obj) {
                // Hide Progress Dialog
                prgDialog.hide();

                int i = mSettings.getInt(APP_PREFERENCES_COUNTER, 0);
                if(i == 0) {
                    SharedPreferences.Editor editor = mSettings.edit();
                    editor.putInt(APP_PREFERENCES_COUNTER, 1);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            // When the response returned by REST has Http response code other than '200'

            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // Hide Progress Dialog
                prgDialog.hide();
                // When Http response code is '404'
                if (statusCode == 404) {
                    Toast.makeText(getApplicationContext(), "Ресурс не найден", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if (statusCode == 500) {
                    Toast.makeText(getApplicationContext(), "Что-то пошло не так с сервером =(", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else {
                    Toast.makeText(getApplicationContext(), "Неизвестная ошибка, возможно дефайс не подключен к Интернету", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
