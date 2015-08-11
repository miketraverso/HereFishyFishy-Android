package com.traversoft.hff.utils;

public interface ActionResolver {

    void launchIntent(String msg, String uriToImage);
    void showShortToast(final CharSequence toastMessage);
    void showLongToast(final CharSequence toastMessage);
    void showAlertBox(String alertBoxTitle, String alertBoxMessage, String alertBoxButtonText);
    void openUri(String uri);
//    void showMyList();
}
