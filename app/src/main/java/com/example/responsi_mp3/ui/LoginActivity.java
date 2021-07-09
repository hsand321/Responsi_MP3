package com.example.responsi_mp3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.responsi_mp3.MainActivity;
import com.example.responsi_mp3.R;
import com.example.responsi_mp3.database.User;
import com.example.responsi_mp3.database.UserDao;
import com.example.responsi_mp3.database.UserDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button login;
    TextView recovery, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnMasuk);
        register = findViewById(R.id.tvBuatAkun);
        recovery = findViewById(R.id.tvLupaAkun);

        register.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        recovery.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RecoveryActivity.class)));

        login.setOnClickListener(v -> {
            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();
            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Text tidak boleh kosong", Toast.LENGTH_SHORT).show();
            } else {
                UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                UserDao userDao = userDatabase.userDao();
                new Thread(() -> {
                    User user = userDao.login(emailText, passwordText);
                    if (user == null) {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Tidak Sesuai", Toast.LENGTH_SHORT).show());
                    } else {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                }).start();
            }
        });
    }
}