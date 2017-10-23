package com.clerkiechat.ui.userregistration.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.clerkiechat.R;
import com.clerkiechat.utils.ViewUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Pranav Bhoraskar
 */

public class ForgotPasswordActivity extends AppCompatActivity {

    @BindView(R.id.forgot_password)
    MaterialEditText forgotPasswordEditText;
    @BindView(R.id.button_forgot_password)
    Button forgotPasswordButton;
    @BindView(R.id.forgot_password_layout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.password_reset_progress_bar)
    ProgressBar progressBar;

    private String userEmailAddress;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if (getIntent().getExtras() != null) {
            userEmailAddress = getIntent().getStringExtra(LoginActivity.EMAIL_ADDRESS);
        }
        else {
            userEmailAddress = "";
        }
        forgotPasswordEditText.setText(userEmailAddress);
    }

    @OnClick(R.id.button_forgot_password)
    public void onViewClicked() {
        if (ViewUtils.isInternetAvailable(this)) {
            if (ViewUtils.checkEmailValidity(forgotPasswordEditText)) {
                userEmailAddress = forgotPasswordEditText.getText().toString();
                sendResetPasswordEmail(userEmailAddress);
                displaySnackBar(getString(R.string.snackbar_check_email));

                finish();
            }
            else {
                forgotPasswordEditText.setError(getString(R.string.invalid_email));
            }
        }
        else {
            ViewUtils.createNoInternetDialog(this, R.string.network_error);
        }
    }

    private void sendResetPasswordEmail(String userEmailAddress) {
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.sendPasswordResetEmail(userEmailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            displaySnackBar(getString(R.string.snackbar_check_email));
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void displaySnackBar(String title) {
        Snackbar.make(constraintLayout, title, Snackbar.LENGTH_LONG)
                .setAction("Resend", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendResetPasswordEmail(userEmailAddress);
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.colorPrimaryLight))
                .show();
    }
}