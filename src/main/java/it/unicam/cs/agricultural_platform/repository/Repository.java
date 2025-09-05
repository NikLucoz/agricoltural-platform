package it.unicam.cs.agricultural_platform.repository;

import java.util.Collection;

public interface Repository<T> {
    T add(T t);
    void addAll(Collection<T> T);
    boolean remove(T t);
    void removeAll(Collection<T> T);
    Collection<T> getItems();
    boolean contains(T item);
    T getItemByID(long id);
    long getNextId();
}
