package it.unicam.cs.agricultural_platform.dto.user;

public class ItemCartOperationDTO {

    private long userId;
    private long contentId;
    private int quantity;

    public ItemCartOperationDTO(long userId, long contentId, int quantity) {
        this.userId = userId;
        this.contentId = contentId;
        this.quantity = quantity;
    }

    public ItemCartOperationDTO(){}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
