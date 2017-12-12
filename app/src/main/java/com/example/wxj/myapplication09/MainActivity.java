package com.example.wxj.myapplication09;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String userInfo = "USERINFO";
    private TextView tvUsername, tvPassword;
    private EditText etPassword, etUsername;
    private CheckBox cbSaveinfo;
    private Button btnLogin, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        cbSaveinfo = (CheckBox) findViewById(R.id.cb_saveinfo);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnClear = (Button) findViewById(R.id.btn_clear);

        btnLogin.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        initdata();
    }

    private void initdata() {
        SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        String username = sp.getString("UserName", "");
        String password = sp.getString("PassWord", "");
        etUsername.setText(username);
        etPassword.setText(password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_clear:
                clear();
                break;
        }
    }

    private void login() {
        if (cbSaveinfo.isChecked()) {
            String strUserName = etUsername.getText().toString();
            String strPassWord = etPassword.getText().toString();
            SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("UserName", strUserName);
            editor.putString("PassWord", strPassWord);
            editor.commit();
            Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "未保存", Toast.LENGTH_LONG).show();
        }
    }

    private void clear() {
        SharedPreferences sp = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
}
