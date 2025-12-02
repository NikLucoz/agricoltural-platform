package it.unicam.cs.agricultural_platform.middlewares;

public class MiddlewareValidationContext {
    private final MiddlewareOperationType operationType;
    private long optionalId = -1;

    public MiddlewareValidationContext(MiddlewareOperationType operationType) {
        this.operationType = operationType;
    }

    public MiddlewareValidationContext(MiddlewareOperationType operationType,  long optionalId) {
        this.operationType = operationType;
        this.optionalId = optionalId;
    }

    public static MiddlewareValidationContext forCreate() {
        return new MiddlewareValidationContext(MiddlewareOperationType.CREATE);
    }

    public static MiddlewareValidationContext forUpdate() {
        return new MiddlewareValidationContext(MiddlewareOperationType.UPDATE);
    }

    public static MiddlewareValidationContext forCreate(long optionalId) {
        return new MiddlewareValidationContext(MiddlewareOperationType.CREATE, optionalId);
    }

    public static MiddlewareValidationContext forUpdate(long optionalId) {
        return new MiddlewareValidationContext(MiddlewareOperationType.UPDATE, optionalId);
    }

    public MiddlewareOperationType getOperation() {
        return operationType;
    }

    public long getOptionalId() {
        return optionalId;
    }

    public boolean isCreate() {
        return operationType == MiddlewareOperationType.CREATE;
    }

    public boolean isUpdate() {
        return operationType == MiddlewareOperationType.UPDATE;
    }
}