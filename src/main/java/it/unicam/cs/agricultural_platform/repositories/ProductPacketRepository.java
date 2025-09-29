package it.unicam.cs.agricultural_platform.repositories;

import it.unicam.cs.agricultural_platform.models.product.ProductPacket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductPacketRepository extends ContentRepository<ProductPacket> {
    @Query("SELECT p FROM ProductPacket p WHERE p.isApproved = true AND p.name LIKE %:filter%")
    List<ProductPacket> findAllApprovedProductPacketsByFilter(String filter);
}
