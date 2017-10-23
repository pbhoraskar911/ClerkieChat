package com.clerkiechat.ui.userregistration.signup;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.clerkiechat.R;
import com.clerkiechat.model.User;
import com.clerkiechat.ui.chatscreen.ChatScreenActivity;
import com.clerkiechat.utils.ViewUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Pranav Bhoraskar
 */

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.signup_email)
    MaterialEditText signupEmail;
    @BindView(R.id.signup_phone)
    MaterialEditText signupPhone;
    @BindView(R.id.signup_password)
    MaterialEditText signupPassword;
    @BindView(R.id.signup_confirm_password)
    MaterialEditText signupConfirmPassword;
    @BindView(R.id.signup_button)
    Button signupButton;
    @BindView(R.id.signup_constraint)
    ConstraintLayout signupConstraint;
    @BindView(R.id.signup_progressbar)
    ProgressBar progressbar;
    @BindView(R.id.signup_name)
    MaterialEditText signupName;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private String nameOfUser, emailAddress, userPhone, password, confirmPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @OnClick(R.id.signup_button)
    public void onViewClicked() {
        nameOfUser = signupName.getText().toString();
        emailAddress = signupEmail.getText().toString();
        userPhone = signupPhone.getText().toString();
        password = signupPassword.getText().toString();
        confirmPassword = signupConfirmPassword.getText().toString();

        if (nameOfUser == null) {
            nameOfUser = "";
        }

        if (!TextUtils.isEmpty(nameOfUser) && ViewUtils.checkEmailValidity(signupEmail)
                && ViewUtils.checkPhoneValidity(signupPhone)
                && ViewUtils.checkPasswordValidity(signupPassword)
                && ViewUtils.checkPasswordValidity(signupConfirmPassword)) {
            if (TextUtils.equals(password, confirmPassword)) {
                progressbar.setVisibility(View.VISIBLE);
                registerUser();
            }
            else {
                signupConfirmPassword.setError(getString(R.string.passwords_no_match));
                progressbar.setVisibility(View.GONE);
                displaySnackBar(getString(R.string.passwords_no_match));
            }

        }
        else {
            if (nameOfUser.length() == 0) {
                signupName.setError(getString(R.string.empty_name));
            }
            if (!ViewUtils.checkEmailValidity(signupEmail)) {
                signupEmail.setError(getString(R.string.invalid_email));
            }
            if (!ViewUtils.checkPhoneValidity(signupPhone)) {
                signupPhone.setError(getString(R.string.invalid_phone));
            }
            if (!ViewUtils.checkPasswordValidity(signupPassword)) {
                signupPassword.setError(getString(R.string.invalid_password_details));
            }
            if (!ViewUtils.checkPasswordValidity(signupConfirmPassword)) {
                signupConfirmPassword.setError(getString(R.string.invalid_password));
            }
        }

    }

    private void registerUser() {
        firebaseAuth.createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressbar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            createNewUserDetails(task.getResult().getUser());
                        }
                        else {
                            displaySnackBar(getString(R.string.auth_failed));
                        }
                    }
                });
    }

    private void createNewUserDetails(FirebaseUser user) {
        nameOfUser = signupName.getText().toString();
        createNewUser(user.getUid(), nameOfUser, user.getEmail());
        createLRegistrationDialog(R.string.registration_successful);
    }

    private void createNewUser(String userId, String nameOfUser, String email) {
        User user = new User();
        user.setNameOfUser(nameOfUser);
        user.setUserEmailId(email);

        databaseReference.child("user").child(userId).setValue(user);
    }

    private void navigateToChatScreen() {
        Intent intent = new Intent(SignUpActivity.this, ChatScreenActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void createLRegistrationDialog(int title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        navigateToChatScreen();
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void displaySnackBar(String title) {
        Snackbar.make(signupConstraint, title, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressbar.setVisibility(View.GONE);
    }
}
