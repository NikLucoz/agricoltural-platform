package it.unicam.cs.agricultural_platform.manager;

import it.unicam.cs.agricultural_platform.content.Content;
import it.unicam.cs.agricultural_platform.market.MarketItemDecorator;
import it.unicam.cs.agricultural_platform.repository.ItemRepository;
import it.unicam.cs.agricultural_platform.repository.Repository;

public class MarketManager {
    private Repository<MarketItemDecorator> repository = new ItemRepository<>();

    public boolean addContentToMarket(Content content, double price, int quantity) {
        if(content.isApproved()) {
            MarketItemDecorator marketItemDecorator = new MarketItemDecorator(repository.getNextId(), content);
            marketItemDecorator.setPrice(price);
            marketItemDecorator.setQuantity(quantity);
            marketItemDecorator.setOnSale(true);
            return repository.add(marketItemDecorator) != null;
        }
        return false;
    }

    public boolean deleteContentFromMarket(long contentId) {
        try {
            MarketItemDecorator m = repository.getItemByID(contentId);
            repository.remove((m));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public boolean changePrice(long contentId, double price) {
        try {
            MarketItemDecorator m = repository.getItemByID(contentId);
            m.setPrice(price);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public boolean changeQuantity(long contentId, int quantity) {
        try {
            MarketItemDecorator m = repository.getItemByID(contentId);
            m.setQuantity(quantity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean SetOnSale(long id){
        try {
            MarketItemDecorator m = repository.getItemByID(id);
            m.setOnSale(true);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
