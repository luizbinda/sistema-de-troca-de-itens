package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.domain.Offer;
import com.colatina.sti.service.domain.SituationOffer;
import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.repository.OfferRepository;
import com.colatina.sti.service.service.Utils.ConstantsUtils;
import com.colatina.sti.service.service.Utils.OrderQueueSender;
import com.colatina.sti.service.service.dto.email.EmailDTO;
import com.colatina.sti.service.service.dto.item.ItemDTO;
import com.colatina.sti.service.service.dto.offer.OfferDTO;
import com.colatina.sti.service.service.dto.offer.OfferListDTO;
import com.colatina.sti.service.service.exception.RegraNegocioException;
import com.colatina.sti.service.service.mapper.ItemMapper;
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
    private final ItemMapper itemMapper;
    private final OrderQueueSender orderQueueSender;


    public List<OfferListDTO> index(Long id) {
        List<Offer> list = offerRepository.findAllBySituationIdAndItemId(ConstantsUtils.SITUATION_PENDING, id);
        return offerListMapper.listToDTO(list);
    }

    public List<OfferListDTO> findAllPendingByUser(Long id) {
        List<Offer> list = offerRepository.findAllBySituationIdAndUserId(ConstantsUtils.SITUATION_PENDING, id);
        return offerListMapper.listToDTO(list);
    }

    public OfferListDTO show(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException(ConstantsUtils.OFFER_NOT_FOUND));
        return offerListMapper.toDTO(offer);
    }

    public OfferListDTO changeSituationAccepted(Long id) {
        Offer offer = offerRepository.findById(id).orElseThrow(() -> new RegraNegocioException(ConstantsUtils.OFFER_NOT_FOUND));
        List<Long> itensIds = offer.getItemsOffered().stream().map(Item::getId).collect(Collectors.toList());
        List<Offer> offersToDecline = new ArrayList<>(offerRepository.findBySituationIdAndItemIdIn(ConstantsUtils.SITUATION_PENDING, itensIds));
        offersToDecline.addAll(offerRepository.findAllByIdNotAndSituationIdAndItemId(offer.getId(), ConstantsUtils.SITUATION_PENDING, offer.getItem().getId()));
        offersToDecline.forEach(offerToDecline ->  offerToDecline.setSituation(new SituationOffer(ConstantsUtils.SITUATION_REFUSED)));
        User user = offer.getItemsOffered().get(0).getUser();
        changeUserItems(offer.getItemsOffered(), offer.getUser().getId());
        changeUserItem(offer.getItem(), user.getId());
        offerRepository.saveAll(offersToDecline);
        offer.setSituation(new SituationOffer(ConstantsUtils.SITUATION_ACCEPTED));
        offer = offerRepository.save(offer);
        sendEmails(offer.getUser(), user);
        return offerListMapper.toDTO(offer);
    }

    private void sendEmails(User userRecivedOffer, User userSendOffe) {
        orderQueueSender.send(getEmail(userRecivedOffer));
        orderQueueSender.send(getEmail(userSendOffe));
    }

    private EmailDTO getEmail(User user){

        EmailDTO email = new EmailDTO();
        email.setAssunto("Cadastro STI");
        email.setUserName(user.getName());
        email.setTemplate(ConstantsUtils.EMAIL_OFFER_ACEPETED);
        email.setDestinatario(user.getEmail());
        return email;
    }

    private void changeUserItems(List<Item> items, Long userId) {
        List<ItemDTO> itemsDTO = itemMapper.listToDTO(items);
        itemsDTO.forEach( item -> {
             item.setUserId(userId);
         });
        itemService.saveAll(itemsDTO);
    }

    private void changeUserItem(Item item, Long userId) {
        ItemDTO itemDTO = itemMapper.toDTO(item);
        itemDTO.setUserId(userId);
        itemService.save(itemDTO);
    }

    public OfferListDTO changeSituationRefused(Long id) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException(ConstantsUtils.OFFER_NOT_FOUND));
        SituationOffer refused = new SituationOffer(ConstantsUtils.SITUATION_REFUSED);
        offer.setSituation(refused);
        offer = offerRepository.save(offer);
        return offerListMapper.toDTO(offer);
    }


    public OfferDTO save(OfferDTO offerDTO) {

        offerDTO.setSituationId(ConstantsUtils.SITUATION_PENDING);
        Offer offer = offerMapper.toEntity(offerDTO);

        if (!itemService.show(offer.getItem().getId()).getAvailable()) {
            throw  new RegraNegocioException(ConstantsUtils.ITEM_NOT_AVAILABLE);
        }

        offer = offerRepository.save(offer);
        return offerMapper.toDTO(offer);
    }

}
