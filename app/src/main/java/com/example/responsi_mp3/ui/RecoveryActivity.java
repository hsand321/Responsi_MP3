package com.example.responsi_mp3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.responsi_mp3.R;
import com.example.responsi_mp3.database.User;
import com.example.responsi_mp3.database.UserDao;
import com.example.responsi_mp3.database.UserDatabase;

public class RecoveryActivity extends AppCompatActivity {

    EditText email;
    Button recovery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        email = findViewById(R.id.etEmail);
        recovery = findViewById(R.id.btnRecovery);

        recovery.setOnClickListener(v -> {
            String emailText = email.getText().toString();
            if (emailText.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
            } else {
                UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                UserDao userDao = userDatabase.userDao();
                new Thread(() -> {
                    User user = userDao.recovery(emailText);
                    if (user == null) {
                        runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Tidak sesuai", Toast.LENGTH_SHORT).show());
                    } else {
                        startActivity(new Intent(RecoveryActivity.this, LoginActivity.class));
                    }
                }).start();
            }
        });
    }
}