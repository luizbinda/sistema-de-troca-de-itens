package com.colatina.sti.service.repository;

import com.colatina.sti.service.domain.SituationOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituationOfferRepository extends JpaRepository<SituationOffer, Long> {
}