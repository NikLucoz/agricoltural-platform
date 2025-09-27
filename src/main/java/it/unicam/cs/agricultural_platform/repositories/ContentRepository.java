package it.unicam.cs.agricultural_platform.repositories;

import it.unicam.cs.agricultural_platform.models.Content;
import it.unicam.cs.agricultural_platform.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository<T extends Content> extends JpaRepository<T, Long> {
    boolean existsById(Long id);
    T findById(long id);
    List<T> findAllByIsApprovedFalse();
    List<T> findAllByIsApprovedTrue();
    List<T> findAllByAuthor(User user);
    List<T> findAllByAuthorAndIsApprovedFalse(User user);
    List<T> findAllByAuthorAndIsApprovedTrue(User user);
    List<T> findAllByReviewNeededTrue();
    List<T> findAllByAuthorAndReviewNeededTrue(User user);
    T findByIdAndIsApprovedTrue(long id);
    T findByIdAndIsApprovedFalse(long id);
}
