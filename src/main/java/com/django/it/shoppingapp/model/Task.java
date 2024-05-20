package com.django.it.shoppingapp.model;

import com.django.it.shoppingapp.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="tasks")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@SuperBuilder @ToString
public class Task extends BaseEntity {

    @NotNull
    private String name;
    private String description;
    private boolean status;

    @ManyToOne(fetch= FetchType.EAGER, optional = false)
    @JsonIgnoreProperties("tasks")
    private Shopping shopping;
}
