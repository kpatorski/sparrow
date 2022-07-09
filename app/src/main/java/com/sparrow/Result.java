package com.sparrow;

import java.util.Optional;
import java.util.function.Consumer;

public class Result<Success, Failure> {
    private final Success success;
    private final Failure failure;

    private Result(Success success, Failure failure) {
        this.success = success;
        this.failure = failure;
    }

    public static <Success, Failure> Result<Success, Failure> success(Success success) {
        return new Result<>(success, null);
    }

    public static <Success, Failure> Result<Success, Failure> failure(Failure failure) {
        return new Result<>(null, failure);
    }

    public Optional<Success> success() {
        return Optional.ofNullable(success);
    }

    public Optional<Failure> failure() {
        return Optional.ofNullable(failure);
    }

    public Result<Success, Failure> ifSuccess(Consumer<Success> consumer) {
        if (isSuccess()) {
            consumer.accept(success);
        }
        return this;
    }

    public boolean isSuccess() {
        return success != null;
    }

}