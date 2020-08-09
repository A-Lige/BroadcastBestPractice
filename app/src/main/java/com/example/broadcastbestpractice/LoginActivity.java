package com.example.broadcastbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class LoginActivity extends BaseActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEdit=(EditText) findViewById(R.id.account);
        passwordEdit=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);

        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass=(CheckBox)findViewById(R.id.remember_pass);
        boolean isRemember=preferences.getBoolean("remember_password",false);
        if (isRemember){
            //将账号密码都设置到文本框中
            String account=preferences.getString("account","");
            String password=preferences.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                if (account.equals("abc")&&password.equals("123456")){
                    //检查复选框是否选中
                    editor=preferences.edit();
                    if (rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"account or password is invalid!",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}