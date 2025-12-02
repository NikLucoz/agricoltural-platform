package it.unicam.cs.agricultural_platform.middlewares;

public abstract class Middleware<T> {

    protected Middleware<T> next;

    public abstract boolean handle(T data, MiddlewareValidationContext validationContext);

    public static <T> Middleware<T> link(Middleware<T> first, Middleware<T>... chain) {
        Middleware<T> head = first;
        for (Middleware<T> nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    protected boolean handleNext(T data, MiddlewareValidationContext validationContext) {
        if (next == null) return true;
        return next.handle(data, validationContext);
    }
}
