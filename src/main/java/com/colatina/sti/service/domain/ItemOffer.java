package com.colatina.sti.service.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "item_offer", schema = "public")
public class ItemOffer implements Serializable {

    @EmbeddedId
    @Column(name = "id_item_offer")
    private Long idItemOffer;
    @Column(name = "id_offer")
    private Long idOffer;

}
