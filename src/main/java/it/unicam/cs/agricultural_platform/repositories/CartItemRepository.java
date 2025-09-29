package it.unicam.cs.agricultural_platform.repositories;

import it.unicam.cs.agricultural_platform.models.Content;
import it.unicam.cs.agricultural_platform.models.user.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.wrappee = :content")
    void deleteByContent(@Param("content") Content content);
}
