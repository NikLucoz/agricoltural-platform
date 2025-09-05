package it.unicam.cs.agricultural_platform.content;

import it.unicam.cs.agricultural_platform.content.user.User;

public class Content {
    protected boolean IsApproved;
    protected boolean ReviewNeeded;
    protected User Author;

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
