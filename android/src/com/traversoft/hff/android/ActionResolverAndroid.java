package com.traversoft.hff.android;

import android.content.Context;
import android.os.Handler;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.traversoft.hff.utils.ActionResolver;

import java.io.File;

public class ActionResolverAndroid implements ActionResolver {

    Handler uiThread;
    Context appContext;

    private static String url = "http://www.traversoft.com/apps/";

    public ActionResolverAndroid (Context appContext) {

        uiThread = new Handler();
        this.appContext = appContext;
    }

    @Override
    public void showShortToast(final CharSequence toastMessage) {

        uiThread.post(new Runnable() {

            public void run() {
                Toast.makeText(appContext, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showLongToast(final CharSequence toastMessage) {

        uiThread.post(new Runnable() {

            public void run() {
                Toast.makeText(appContext, toastMessage, Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void showAlertBox(final String alertBoxTitle,
                             final String alertBoxMessage,
                             final String alertBoxButtonText) {

        uiThread.post(new Runnable() {

            public void run() {

                new AlertDialog.Builder(appContext).setTitle(alertBoxTitle)
                        .setMessage(alertBoxMessage)
                        .setNeutralButton(alertBoxButtonText,
                                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int button) {}
                }).create().show();
            }
        });
    }


    @Override
    public void openUri(String uri) {

        Uri myUri = Uri.parse(uri);
        Intent intent = new Intent(Intent.ACTION_VIEW, myUri);
        appContext.startActivity(intent);
    }

    @Override
    public void launchIntent(String msg, String uriToImage) {

        Intent shareIntent = new Intent();

        if (!uriToImage.equalsIgnoreCase("")){

            System.out.println(uriToImage);
            File f = new File(uriToImage);
            Uri uri = Uri.fromFile(f);
            System.out.println(uri.toString());
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("*/*");
            //shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.putExtra(Intent.EXTRA_TEXT, msg + " " + url);
            appContext.startActivity(shareIntent);
        }
        else {

            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, msg + " " + url);
            shareIntent.setType("text/plain");
            appContext.startActivity(shareIntent);
        }
    }

//    @Override
//    public void showMyList() {
//
//        appContext.startActivity(new Intent(this.appContext, MyListActivity.class));
//    }
}

