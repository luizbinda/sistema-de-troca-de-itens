package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.domain.Offer;
import com.colatina.sti.service.domain.SituationOffer;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final OfferListMapper offerListMapper;
    private final ItemService itemService;



    public List<OfferListDTO> index(Long id) {
        List<Offer> list = offerRepository.findAllBySituationIdAndItemId(ConstantsUtils.SITUATION_PENDING, id);
        return offerListMapper.listToDTO(list);
    }

    public OfferListDTO show(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Nenhuma oferta encontrada!"));
        return offerListMapper.toDTO(offer);
    }

    public OfferListDTO changeSituationAccepted(Long id) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Nenhuma oferta encontrada!"));
        List<Long> itensIds = offer.getItemsOffered().stream().map(Item::getId).collect(Collectors.toList());
        List<Offer> offersToDecline = new ArrayList<>(offerRepository.findBySituationIdAndItemIdIn(ConstantsUtils.SITUATION_PENDING, itensIds));
        offersToDecline.addAll(offerRepository.findAllByIdNotAndSituationIdAndItemId(offer.getId(), ConstantsUtils.SITUATION_PENDING, offer.getItem().getId()));
        offersToDecline.forEach(offerToDecline ->  offerToDecline.setSituation(new SituationOffer(ConstantsUtils.SITUATION_REFUSED)));
        offerRepository.saveAll(offersToDecline);
        offer.setSituation(new SituationOffer(ConstantsUtils.SITUATION_ACCEPTED));
        offer = offerRepository.save(offer);
        return offerListMapper.toDTO(offer);
    }

    public OfferListDTO changeSituationRefused(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Nenhuma oferta encontrada!"));
        SituationOffer refused = new SituationOffer(ConstantsUtils.SITUATION_REFUSED);
        offer.setSituation(refused);
        offer = offerRepository.save(offer);
        return offerListMapper.toDTO(offer);
    }


    public OfferDTO store(OfferDTO offerDTO) {

        offerDTO.setSituationId(ConstantsUtils.SITUATION_PENDING);
        Offer offer = offerMapper.toEntity(offerDTO);

        if (!itemService.show(offer.getItem().getId()).getAvailable()) {
            throw  new RegraNegocioException("Item não disponivel!");
        }
        offer = offerRepository.save(offer);
        return offerMapper.toDTO(offer);
    }

    public OfferDTO update(OfferDTO offerDTO) {
        offerDTO.setSituationId(ConstantsUtils.SITUATION_PENDING);
        Offer offer = offerMapper.toEntity(offerDTO);
        offer = offerRepository.save(offer);
        return offerMapper.toDTO(offer);
    }

}
