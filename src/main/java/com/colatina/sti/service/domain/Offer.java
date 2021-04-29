package com.colatina.sti.service.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item Item;

    @ManyToOne
    @JoinColumn(name = "id_situation")
    private SituationOffer situation;

    @ManyToOne
    @JoinColumn(name = "id_user_offer")
    private User user;

    @ManyToMany()
    @JoinTable(name = "item_offer",
               joinColumns = { @JoinColumn(name = "id_offer") },
               inverseJoinColumns = { @JoinColumn(name = "id_item_offer") }
    )
    private List<Item> itens_ofertados;

}
