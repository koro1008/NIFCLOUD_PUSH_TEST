package com.example.sakakoro.pushtestsaka;

import android.support.annotation.Nullable;
import io.reactivex.subjects.BehaviorSubject;

public class Variable<T> {

    private T value;

    @Nullable
    private T oldValue = null;

    private final BehaviorSubject<T> subject;

    public Variable(T value) {
        subject = BehaviorSubject.create();
        setValue(value);
    }

    public synchronized T getValue() {
        return value;
    }

    public synchronized void setValue(T value) {
        oldValue = this.value;
        this.value = value;
        subject.onNext(this.value);
    }

    @Nullable
    public synchronized  T getOldValue() { return oldValue; }

    public BehaviorSubject<T> asObservable() {
        return subject;
    }

}
