package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.Offer;
import com.colatina.sti.service.repository.OfferRepository;
import com.colatina.sti.service.service.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;

    public List<Offer> index() {
        List<Offer> list = offerRepository.findAll();
        return list;
    }

    public Offer show(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Nenhum Usuário encontrado!"));
        return offer;
    }

    public Offer store(Offer offer) {
        offer = offerRepository.save(offer);
        return offer;
    }

    public Offer update(Offer offer) {
        offer = offerRepository.save(offer);
        return offer;
    }

    public void delete(Long id) {
        offerRepository.deleteById(id);
    }
}
