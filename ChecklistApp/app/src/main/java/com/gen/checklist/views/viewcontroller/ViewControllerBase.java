package com.gen.checklist.views.viewcontroller;

public interface ViewControllerBase <T> {
    void failureWS(String message);

    void successWS(T parametro);

    void showProgressDialog(String message);

    void dissmissProgressDialog();
}
