package com.auth.authentication.models;

public interface IObserver {
    void addObserver(String callback, Object observating);

    void deleteObserver(String callback);

    void notifyAuthenticationSuccess(String callback);
    
    void notifyAuthenticationFailed(String callback);
}
