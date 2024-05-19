package com.auth.authentication.services;

import java.util.HashMap;
import org.springframework.stereotype.Service;

import com.auth.authentication.models.IObserverAuthSuccess;
import com.auth.authentication.models.IObserver;
import com.auth.authentication.models.IObserverAuthFailed;

@Service
public class ObserverService implements IObserver {

    private HashMap<String, Object> list = new HashMap<String, Object>();

    @Override
    public void addObserver(String callback, Object observating) {
        if (list.containsKey(callback)) {
            return;
        }

        list.put(callback, observating);
    }

    @Override
    public void deleteObserver(String callback) {
        list.remove(callback);
    }

    @Override
    public void notifyAuthenticationSuccess(String callback) {
        IObserverAuthSuccess observer = (IObserverAuthSuccess) list.get(callback);
        observer.notifyAuthenticationSuccess();
    }

    @Override
    public void notifyAuthenticationFailed(String callback) {
        IObserverAuthFailed observer = (IObserverAuthFailed) list.get(callback);
        observer.notifyAuthenticationFailed();
    }
    
}
