package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.ItemOffer;
import com.colatina.sti.service.domain.Offer;
import com.colatina.sti.service.repository.OfferRepository;
import com.colatina.sti.service.service.dto.offer.OfferDTO;
import com.colatina.sti.service.service.exception.RegraNegocioException;
import com.colatina.sti.service.service.mapper.OfferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    public List<OfferDTO> index() {
        List<Offer> list = offerRepository.findAll();
        return offerMapper.listToDTO(list);
    }

    public OfferDTO show(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Nenhuma oferta encontrada!"));
        return offerMapper.toDTO(offer);
    }

    public OfferDTO store(OfferDTO offerDTO, List<Long> idsItensOfertados) {
        Offer offer = offerMapper.toEntity(offerDTO);
        offer = offerRepository.save(offer);
        List<ItemOffer> itensOfertados = new ArrayList<>();
        final Offer finalOffer = offer;
        idsItensOfertados.forEach(id -> {
            ItemOffer item = new ItemOffer();
            item.setIdOffer(finalOffer.getId());
            item.setIdItemOffer(id);
            itensOfertados.add(item);
        });


        return offerMapper.toDTO(offer);
    }

    public OfferDTO update(OfferDTO offerDTO) {
        Offer offer = offerMapper.toEntity(offerDTO);
        offer = offerRepository.save(offer);
        return offerMapper.toDTO(offer);
    }

    public void delete(Long id) {
        offerRepository.deleteById(id);
    }
}
