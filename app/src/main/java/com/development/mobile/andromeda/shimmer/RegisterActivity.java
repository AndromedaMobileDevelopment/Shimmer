package com.development.mobile.andromeda.shimmer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    }

    @Override
    public void onClick(View view) {
        nickName = EtNickName.getText().toString();
        email = EtEmail.getText().toString();
        password = EtPass.getText().toString();
        invokeWS();
    }

    private void goToLoginActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void invokeWS(){
        RequestParams params = new RequestParams();
        params.put("username",nickName);
        params.put("name", email);
        params.put("password",password);
        // Show Progress Dialog
        prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://195.19.44.155/anton/index.php?method=register", params, new JsonHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'

            public void onSuccess(int statusCode, Header[] headers, JSONObject obj) {
                // Hide Progress Dialog
                prgDialog.hide();
                try {
                    if (obj.getBoolean("status")) {
                        // Set Default Values for Edit View controls

                    } else {
                        errorMsg.setText(obj.getString("error_msg"));
                        Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Сервер Json не отвечает!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

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
