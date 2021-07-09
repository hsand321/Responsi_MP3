package com.example.responsi_mp3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.responsi_mp3.MainActivity;
import com.example.responsi_mp3.R;
import com.example.responsi_mp3.database.User;
import com.example.responsi_mp3.database.UserDao;
import com.example.responsi_mp3.database.UserDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password, confirm;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.etNewEmail);
        password = findViewById(R.id.etNewPassword);
        confirm = findViewById(R.id.etVerPassword);
        register = findViewById(R.id.btnDaftar);

        register.setOnClickListener(v -> {
            User user = new User();
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            user.setConfirm(confirm.getText().toString());
            if (validateInput(user)) {
                if (password.getText().toString().equals(confirm.getText().toString())) {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    new Thread(() -> {
                        userDao.registerUser(user);
                        runOnUiThread(() -> {
                            Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        });
                    }).start();
                } else {
                    Toast.makeText(getApplicationContext(), "Password tidak sama", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Harap isi semua dengan benar", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private Boolean validateInput(User user) {
        if (user.getEmail().isEmpty() ||
                user.getPassword().isEmpty() ||
                user.getConfirm().isEmpty()) {
            return false;
        }
        return true;
    }

}