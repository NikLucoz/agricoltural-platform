package it.unicam.cs.agricultural_platform.repository;

import java.util.*;

public class ItemRepository<T extends RepositoryItem> implements Repository<T> {
    private final Map<Long, T> items;
    private long lastId;

    public ItemRepository() {
        this.items = new HashMap<>();
        lastId = 0;
    }

    @Override
    public T add(T item) {
        T res = this.items.putIfAbsent(item.getId(), item);
        if(res != null) {
            lastId = item.getId();
            return res;
        }

        return null;
    }

    @Override
    public boolean remove(T item) {
        boolean res = this.items.remove(item.getId(), item);
        if(res) {
            if (lastId == item.getId()) {
                lastId--;
            }
            return true;
        }
        return false;
    }

    @Override
    public void addAll(Collection<T> items) {
        for (T item : items) this.add(item);
    }

    @Override
    public void removeAll(Collection<T> items) {
        for (T item : items) this.remove(item);
    }

    @Override
    public List<T> getItems() {
        return new ArrayList<>(items.values());
    }

    @Override
    public boolean contains(T item) {
        return this.items.containsValue(item);
    }

    @Override
    public T getItemByID(long id) {
        T item = this.items.get(id);
        if (item == null) throw new NoSuchElementException("Item not found");
        return item;
    }

    @Override
    public long getNextId() {
        return lastId + 1;
    }
}