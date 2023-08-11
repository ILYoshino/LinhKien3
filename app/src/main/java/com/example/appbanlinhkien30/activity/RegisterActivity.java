package com.example.appbanlinhkien30.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.appbanlinhkien30.R;
import com.example.appbanlinhkien30.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtPhone, edtPassword, edtEmail;
    private Button btnRegister;
    private Boolean valid = true;
    private Dialog registerProgress;
    FirebaseAuth fAuth;

    DatabaseReference rootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();

        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtPassword = (EditText) findViewById(R.id.edtPasswordRegister);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnRegister = (Button) findViewById(R.id.btnCreateAccount);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(edtPhone);
                checkField(edtPassword);
                checkField(edtEmail);

                if (valid) {
                    CreateAccount();
                }
            }
        });
    }

    private void CreateAccount() {
       String phone = edtPhone.getText().toString();
       String email = edtEmail.getText().toString();
       String pwd = edtPassword.getText().toString();

       registerProgress = new Dialog(RegisterActivity.this);
       registerProgress.setContentView(R.layout.register_dialog);
       registerProgress.setCancelable(false);
       registerProgress.show();

       fAuth.createUserWithEmailAndPassword(email, pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
           @Override
           public void onSuccess(AuthResult authResult) {
               registerProgress.dismiss();
               User user = new User(phone, email, pwd, 0);
               rootRef.child("User").push().setValue(user);

               Toast.makeText(RegisterActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
               Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
               startActivity(i);
               finish();
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               registerProgress.dismiss();
               Toast.makeText(RegisterActivity.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
           }
       });
    }
    public boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()) {
            textField.setError("Error");
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
//    private void CreateAccount() {
//        String phone = edtPhone.getText().toString();
//        String email = edtEmail.getText().toString();
//        String pwd = edtPassword.getText().toString();
//
//        if (TextUtils.isEmpty(phone)) {
//            Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
//        }
//        else if (TextUtils.isEmpty(email)) {
//            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
//        }
//        else if (TextUtils.isEmpty(pwd)) {
//            Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            ValidateEmail(phone, email, pwd);
//            registerProgress = new Dialog(this);
//            registerProgress.setContentView(R.layout.register_dialog);
//            registerProgress.setCancelable(false);
//            registerProgress.show();
//        }
//    }

//    private void ValidateEmail(String phone, String email, String pwd) {
//        final DatabaseReference rootRef;
//        rootRef = FirebaseDatabase.getInstance().getReference();
//        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (!(snapshot.exists())){
//                    User user = new User(phone, email, pwd, false);
//
//                    rootRef.child("User").push().setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                registerProgress.dismiss();
//                                Toast.makeText(RegisterActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
//
//                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
//                                startActivity(i);
//                            }
//                            else {
//                                registerProgress.dismiss();
//                                Toast.makeText(RegisterActivity.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }
//                else {
//                    registerProgress.dismiss();
//                    Toast.makeText(RegisterActivity.this, email + " đã tồn tại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}