package com.clerkiechat.ui.userregistration;

/**
 * Created by Pranav Bhoraskar
 */

public interface Presenter<T extends ViewInterface> {

    void attachView(T t);

    void detachView();
}
