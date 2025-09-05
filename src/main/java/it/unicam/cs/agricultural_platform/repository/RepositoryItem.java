package it.unicam.cs.agricultural_platform.repository;

public abstract class RepositoryItem {
    private long id;

    public RepositoryItem(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
