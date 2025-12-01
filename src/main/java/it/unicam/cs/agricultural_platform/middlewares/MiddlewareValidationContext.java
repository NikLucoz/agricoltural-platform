package it.unicam.cs.agricultural_platform.middlewares;

public class MiddlewareValidationContext {
    private final MiddlewareOperationType operationType;

    public MiddlewareValidationContext(MiddlewareOperationType operationType) {
        this.operationType = operationType;
    }

    public static MiddlewareValidationContext forCreate() {
        return new MiddlewareValidationContext(MiddlewareOperationType.CREATE);
    }

    public static MiddlewareValidationContext forUpdate() {
        return new MiddlewareValidationContext(MiddlewareOperationType.UPDATE);
    }

    public MiddlewareOperationType getOperation() {
        return operationType;
    }

    public boolean isCreate() {
        return operationType == MiddlewareOperationType.CREATE;
    }

    public boolean isUpdate() {
        return operationType == MiddlewareOperationType.UPDATE;
    }
}
