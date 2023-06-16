package com.example.codewizard.ui.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.codewizard.R;
import com.example.codewizard.api.ApiResponse;
import com.example.codewizard.api.model.Resenia;
import com.example.codewizard.api.services.ReviewService;
import com.example.codewizard.api.services.UserService;
import com.example.codewizard.databinding.ActivityBroadcastBinding;
import com.example.codewizard.databinding.ActivityMainMenuBinding;
import com.example.codewizard.singleton.CurrentUser;

public class BroadcastActivity extends AppCompatActivity {

    ActivityBroadcastBinding activityBroadcastBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_broadcast);
        activityBroadcastBinding = ActivityBroadcastBinding.inflate(getLayoutInflater());
        setContentView(activityBroadcastBinding.getRoot());

        loadView();
    }

    private void loadView() {
        EditText editTextSubject = activityBroadcastBinding.etSubject;
        EditText editTextContent = activityBroadcastBinding.etContent;

        activityBroadcastBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = String.valueOf(editTextContent.getText());
                String subject = String.valueOf(editTextSubject.getText());
                ApiResponse apiResponse = UserService.sendBroadcast(subject,content);
                if(apiResponse.getCode() == 200){
                    Toast.makeText(getApplicationContext(), "Broadcast enviado correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Broadcast no enviado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}