package com.musicalcoder.authentication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.musicalcoder.authentication.models.RegisterUser;
import com.musicalcoder.authentication.models.UserResponse;
import com.musicalcoder.authentication.network.APIClient;
import com.musicalcoder.authentication.network.AuthAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    AuthAPI authAPI;
    private EditText editTextFName, editTextLName, editTextUName, editTextEmail, editTextPass1, editTextPass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authAPI = APIClient.getClient().create(AuthAPI.class);

        //TODO :: LOOK AT BUTTERKNIFE - https://github.com/JakeWharton/butterknife
        editTextFName = findViewById(R.id.fname_field);
        editTextLName = findViewById(R.id.lname_field);
        editTextUName = findViewById(R.id.uname_field);
        editTextEmail = findViewById(R.id.email_field);
        editTextPass1 = findViewById(R.id.password);
        editTextPass2 = findViewById(R.id.confirm_password);

        Button registerBtn = findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegistration();
            }
        });

    }

    //    Registration Method.
    private void userRegistration() {

        String fName = editTextFName.getText().toString().trim();
        String lName = editTextLName.getText().toString().trim();
        String uName = editTextUName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String pass1 = editTextPass1.getText().toString().trim();
        String pass2 = editTextPass2.getText().toString().trim();

        if (fName.isEmpty()) {
            editTextFName.setError("First Name cannot be empty.");
            editTextFName.requestFocus();
            return;
        }
        if (lName.isEmpty()) {
            editTextLName.setError("Last Name cannot be empty.");
            editTextLName.requestFocus();
            return;
        }
        if (uName.isEmpty()) {
            editTextUName.setError("Username cannot be empty.");
            editTextUName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Email cannot be empty.");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Invalid Email format.");
            editTextEmail.requestFocus();
            return;
        }
        if (pass1.isEmpty()) {
            editTextPass1.setError("Enter Password.");
            editTextPass1.requestFocus();
            return;
        }
        if (pass2.isEmpty()) {
            editTextPass2.setError("Confirm Password.");
            editTextPass2.requestFocus();
            return;
        }

        if (pass1.length() < 8) {
            editTextPass1.setError("Password should be at least 8 characters long.");
            editTextPass1.requestFocus();
            return;
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating User Account...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        RegisterUser registerUser = new RegisterUser(fName, lName, uName, email, pass1, pass2);

        Call<UserResponse> call = authAPI.registerUser(registerUser);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 201) {
                    UserResponse userResponse = response.body(); // Will map all data to your object
                    Log.d("User Created is ", userResponse.getRegisterUser().getFirst_name());
                } else {
                    assert response.body() != null;
                    JSONObject jsonObject = null;
                    try {
                        assert response.errorBody() != null;
                        jsonObject = new JSONObject(response.errorBody().string());

                        Log.d("Error Message is : ", jsonObject.getString("message"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                progressDialog.dismiss();
                //Mostly the error 500
                Log.d("Error ", t.getLocalizedMessage());
            }
        });


    }
}
