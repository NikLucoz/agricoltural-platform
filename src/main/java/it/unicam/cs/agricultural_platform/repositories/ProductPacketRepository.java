package it.unicam.cs.agricultural_platform.repositories;

import it.unicam.cs.agricultural_platform.models.product.ProductPacket;
import it.unicam.cs.agricultural_platform.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPacketRepository extends JpaRepository<ProductPacket, Long> {

    ProductPacket findProductPacketById(long id);
    List<ProductPacket> findAllProductPacketsByAuthor(User author);

}
