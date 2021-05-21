package com.colatina.sti.service.repository;

import com.colatina.sti.service.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

  List<Offer> findBySituationIdAndItemIdIn(Long situationId, List<Long> itensIds);

  List<Offer> findAllByIdNotAndSituationIdAndItemId(Long id, Long situationId, Long ItenId);

  List<Offer> findAllBySituationIdAndItemId(Long situationId, Long ItenId);

  List<Offer> findAllBySituationIdAndUserId(Long situationId, Long ItenId);
}
