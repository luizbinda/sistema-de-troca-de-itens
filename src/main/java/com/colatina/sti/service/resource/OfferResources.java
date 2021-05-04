package com.colatina.sti.service.resource;


import com.colatina.sti.service.domain.Offer;
import com.colatina.sti.service.service.OfferService;
import com.colatina.sti.service.service.dto.offer.OfferDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
public class OfferResources {
    private final OfferService offerService;

    @GetMapping("/{id}")
    public ResponseEntity<OfferDTO> show(@PathVariable Long id) {
        return  new ResponseEntity<>(offerService.show(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OfferDTO> store(@RequestParam OfferDTO offerDTO, @RequestParam List<Long> idsItensOfertados) throws IOException {
        return  new ResponseEntity<>(offerService.store(offerDTO, idsItensOfertados), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<OfferDTO> update(@RequestParam OfferDTO offerDTO) throws IOException {
        return  new ResponseEntity<>(offerService.update(offerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        offerService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
