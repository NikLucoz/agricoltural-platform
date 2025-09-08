package it.unicam.cs.agricultural_platform.content;

import it.unicam.cs.agricultural_platform.content.user.User;
import it.unicam.cs.agricultural_platform.repository.RepositoryItem;

public abstract class Content extends RepositoryItem {
    protected boolean IsApproved = false;
    protected boolean ReviewNeeded = false;
    protected User Author;

    public Content(long id) {
        super(id);
    }

    public boolean isApproved() {
        return IsApproved;
    }

    public boolean isReviewNeeded() {
        return ReviewNeeded;
    }

    public User getAuthor() {
        return Author;
    }

    public void setApproved(boolean approved) {
        IsApproved = approved;
    }

    public void setReviewNeeded(boolean reviewNeeded) {
        ReviewNeeded = reviewNeeded;
    }

    public void setAuthor(User author) {
        Author = author;
    }
}
