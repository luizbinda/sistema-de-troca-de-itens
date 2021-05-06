package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.Offer;
import com.colatina.sti.service.repository.OfferRepository;
import com.colatina.sti.service.service.Utils.ConstantsUtils;
import com.colatina.sti.service.service.dto.offer.OfferDTO;
import com.colatina.sti.service.service.dto.offer.OfferListDTO;
import com.colatina.sti.service.service.exception.RegraNegocioException;
import com.colatina.sti.service.service.mapper.OfferListMapper;
import com.colatina.sti.service.service.mapper.OfferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final OfferListMapper offerListMapper;


    public List<OfferDTO> index() {
        List<Offer> list = offerRepository.findAll();
        return offerMapper.listToDTO(list);
    }

    public OfferListDTO show(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Nenhuma oferta encontrada!"));
        return offerListMapper.toDTO(offer);
    }

    public OfferDTO changeSituationAccepted(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Nenhuma oferta encontrada!"));
        offer.getSituation().setId(ConstantsUtils.SITUATION_ACCEPTED);
        return offerMapper.toDTO(offer);
    }

    public OfferDTO changeSituationRefused(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Nenhuma oferta encontrada!"));
        offer.getSituation().setId(ConstantsUtils.SITUATION_REFUSED);
        return offerMapper.toDTO(offer);
    }


    public OfferDTO store(OfferDTO offerDTO) {
        offerDTO.setSituationId(ConstantsUtils.SITUATION_PENDING);
        Offer offer = offerMapper.toEntity(offerDTO);
        offer = offerRepository.save(offer);
        return offerMapper.toDTO(offer);
    }

    public OfferDTO update(OfferDTO offerDTO) {
        offerDTO.setSituationId(ConstantsUtils.SITUATION_PENDING);
        Offer offer = offerMapper.toEntity(offerDTO);
        offer = offerRepository.save(offer);
        return offerMapper.toDTO(offer);
    }

    public void delete(Long id) {
        offerRepository.deleteById(id);
    }
}
