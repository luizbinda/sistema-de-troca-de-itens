package com.colatina.sti.service.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "situation_offer", schema = "public")
@NoArgsConstructor
public class SituationOffer implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    public SituationOffer(Long id) {
        this.id = id;
    }


}
