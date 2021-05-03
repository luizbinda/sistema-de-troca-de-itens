package com.colatina.sti.service.resource;


import com.colatina.sti.service.domain.Offer;
import com.colatina.sti.service.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/offer")
@RequiredArgsConstructor
public class OfferResources {
    private final OfferService offerService;

    @GetMapping("/{id}")
    public ResponseEntity<Offer> show(@PathVariable Long id) {
        return  new ResponseEntity<>(offerService.show(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Offer> store(@RequestParam Offer offer) throws IOException {
        return  new ResponseEntity<>(offerService.store(offer), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Offer> update(@RequestParam Offer offer) throws IOException {
        return  new ResponseEntity<>(offerService.update(offer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        offerService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
