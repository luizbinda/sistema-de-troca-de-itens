package com.colatina.sti.service.resource;

import com.colatina.sti.service.service.OfferService;
import com.colatina.sti.service.service.dto.offer.OfferDTO;
import com.colatina.sti.service.service.dto.offer.OfferListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
public class OfferResources {
    private final OfferService offerService;

    @GetMapping("/{id}")
    public ResponseEntity<OfferListDTO> show(@PathVariable Long id) {
        return  new ResponseEntity<>(offerService.show(id), HttpStatus.OK);
    }

    @PatchMapping("/accepted/{id}")
    public ResponseEntity<OfferDTO> changeSituationAccepted(@PathVariable Long id) {
        return  new ResponseEntity<>(offerService.changeSituationAccepted(id), HttpStatus.OK);
    }

    @PatchMapping("/refused/{id}")
    public ResponseEntity<OfferDTO> changeSituationRefused(@PathVariable Long id) {
        return  new ResponseEntity<>(offerService.changeSituationRefused(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OfferDTO> store(@RequestBody OfferDTO offerDTO) {
        return  new ResponseEntity<>(offerService.store(offerDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<OfferDTO> update(@RequestBody OfferDTO offerDTO) {
        return  new ResponseEntity<>(offerService.update(offerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        offerService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
