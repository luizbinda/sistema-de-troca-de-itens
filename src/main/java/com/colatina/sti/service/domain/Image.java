package com.colatina.sti.service.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "image", schema = "public")
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_image")
    @SequenceGenerator(name = "seq_image", allocationSize = 1, sequenceName = "seq_image")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item")
    private Item item;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "description")
    private String description;
}
