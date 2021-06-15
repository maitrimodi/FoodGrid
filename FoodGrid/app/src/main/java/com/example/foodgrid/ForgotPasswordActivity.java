package com.example.foodgrid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodgrid.databinding.ActivityForgotPasswordBinding;
import com.example.foodgrid.model.User;

public class ForgotPasswordActivity extends AppCompatActivity {

    ActivityForgotPasswordBinding binding;
    private final String TAG = "ABC";
    MyDatabase db = null;
    UserDAO dao = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding = new ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.db = MyDatabase.getDatabase(getApplicationContext());
        this.dao = db.userDao();


        binding.btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.editEmail.getText().toString();
                String password = binding.editPassword.getText().toString();
                String confirmPassword = binding.editConfirmPassword.getText().toString();
                if(password.equals(confirmPassword)){
                    Log.d(TAG, "onClick: " + email + password + confirmPassword);
                    User user = dao.getUser(email);
                    Log.d(TAG, "onClick: " + user);

                    if(user != null){
                        dao.updatePasswordById(user.getUserId(), password);
                        Toast.makeText(getApplicationContext(), "Password updated successfully", Toast.LENGTH_LONG).show();
                        onBackPressed();

                    } else {
                        Toast.makeText(getApplicationContext(), "No such user exists, please sign up", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Passwords doesn't match", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}