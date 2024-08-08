package com.example.tokojahit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tokojahit.Adapter.SessionManager;

public class UseLanActivity extends AppCompatActivity {

    private Button button1 ,button2, button3, button4;
    SessionManager sessionManager;
    String level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_lan);
        sessionManager = new SessionManager(UseLanActivity.this);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        level = sessionManager.getUserDetail().get(SessionManager.LEVEL);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UseLanActivity.this, ProUseActivity.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UseLanActivity.this, TutorialActivity.class));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UseLanActivity.this, BajuDataActivity.class));
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UseLanActivity.this, PesananDataActivity.class));
            }
        });
    }

    private void moveToLogin() {
        Intent intent = new Intent(UseLanActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actionLogout) {
            sessionManager.logoutSession();
            moveToLogin();
            return true;
        } else if (item.getItemId() == R.id.actionProfil) {
            Intent intent = new Intent(this, ProfilActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.actionTutorial) {
            Intent intent = new Intent(this, TutorialActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}