package com.clerkiechat.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.clerkiechat.R;
import com.clerkiechat.ui.splashscreen.SplashScreenActivity;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Pranav Bhoraskar
 */

public abstract class ViewUtils {

    /**
     * Check for the availability of Internet
     *
     * @return true if network status is active
     */
    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return (activeNetwork != null && activeNetwork.isConnectedOrConnecting());
    }

    public static void createNoInternetDialog(@NonNull final Context context, int title) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(context.getString(R.string.no_internet));
        builder.setCancelable(false);
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        if (context instanceof SplashScreenActivity) {
                            ((SplashScreenActivity) context).finish();
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static boolean checkPasswordValidity(MaterialEditText passwordEditText) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=])(?=\\S+$).{4,}$");
        Matcher matcher = pattern.matcher(passwordEditText.getText().toString());
        return matcher.matches();
    }

    public static boolean checkEmailValidity(MaterialEditText userNameEditText) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+" +
                "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(userNameEditText.getText().toString());
        return matcher.matches();
    }

    public static boolean checkPhoneValidity(MaterialEditText phoneNumberEditText) {
        Pattern pattern = Pattern.compile("^[0-9]{10}$");
        Matcher matcher = pattern.matcher(phoneNumberEditText.getText().toString());
        return matcher.matches();
    }

    @NonNull
    public static ArrayList<Integer> getColorInts() {
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }

        for (int c : ColorTemplate.JOYFUL_COLORS) {
            colors.add(c);
        }

        for (int c : ColorTemplate.COLORFUL_COLORS) {
            colors.add(c);
        }

        for (int c : ColorTemplate.LIBERTY_COLORS) {
            colors.add(c);
        }

        for (int c : ColorTemplate.PASTEL_COLORS) {
            colors.add(c);
        }
        colors.add(ColorTemplate.getHoloBlue());
        return colors;
    }

}