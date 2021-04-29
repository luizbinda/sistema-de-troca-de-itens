package com.colatina.sti.service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "offer")
public class Offer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_offer")
    @SequenceGenerator(name = "seq_offer", allocationSize = 1, sequenceName = "seq_offer")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item")
    private Item Item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_situation")
    private SituationOffer situation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_offer")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "item_offer",
               joinColumns = { @JoinColumn(name = "id_offer") },
               inverseJoinColumns = { @JoinColumn(name = "id_item_offer") }
    )
    private List<Item> itens_ofertados;

}
