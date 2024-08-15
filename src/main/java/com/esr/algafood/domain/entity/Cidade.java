package com.esr.algafood.domain.entity;

import ch.qos.logback.core.joran.spi.DefaultClass;
import com.esr.algafood.domain.Groups;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    private String nome;

    @Valid
    @ConvertGroup(to = Groups.EstadoId.class)
    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Estado estado;
}
