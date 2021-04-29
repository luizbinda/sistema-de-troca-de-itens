package com.colatina.sti.service.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @SequenceGenerator(name = "seq_user", allocationSize = 1, sequenceName = "seq_user")
    @Column(name = "id")
    private Long id;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;

}
