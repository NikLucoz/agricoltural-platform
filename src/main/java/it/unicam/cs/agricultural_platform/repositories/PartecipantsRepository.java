package it.unicam.cs.agricultural_platform.repositories;

import it.unicam.cs.agricultural_platform.models.event.Partecipation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartecipantsRepository extends JpaRepository<Partecipation, Long> {
    List<Partecipation> findByUser_Id(Long partecipantId);
}
