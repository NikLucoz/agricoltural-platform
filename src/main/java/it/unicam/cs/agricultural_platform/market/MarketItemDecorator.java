package it.unicam.cs.agricultural_platform.market;

import it.unicam.cs.agricultural_platform.content.Content;

public class MarketItemDecorator extends Content {
    protected Content wrappee;
    protected double price;
    protected boolean onSale;
    protected double saleDate;
    protected int quantity;

    public MarketItemDecorator(long id, Content wrappee) {
        super(id);
        this.wrappee = wrappee;
    }

    public Content getWrappee() {
        return wrappee;
    }

    public void setWrappee(Content wrappee) {
        this.wrappee = wrappee;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    public double getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(double saleDate) {
        this.saleDate = saleDate;
    }
}
