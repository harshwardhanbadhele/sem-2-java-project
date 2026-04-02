package com.university.Feed;

public class Feedback<T> {
    public void giveFeedback(T entity, String message) {
        System.out.println("[FEEDBACK] For " + entity.getClass().getSimpleName() + ": " + message);
    }
}