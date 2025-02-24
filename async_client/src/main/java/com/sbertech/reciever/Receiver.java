package com.sbertech.reciever;

public interface Receiver<T> {
    void onObject(T object);
}
