package org.cinemacraftstudios.surrealdb.api;

public class Result<T> {

    public final T content;
    public final int time;
    public final Status status;

    public Result(T content, Status status, int time) {
        this.content = content;
        this.status = status;
        this.time = time;
    }
}

