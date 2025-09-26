package it.unicam.cs.agricultural_platform.dto;

public class UpdateMarketDataDTO {
    private long contentId;
    private double price;
    private int stockQuantity;


    public UpdateMarketDataDTO(long contentId, double price, int stockQuantity, long userId) {
        this.contentId = contentId;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public UpdateMarketDataDTO(){}

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

}
