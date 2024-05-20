package com.django.it.shoppingapp.model;

import com.django.it.shoppingapp.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="shares")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class Share extends BaseEntity {

    private Integer shareId;
    private Integer shoppingId;
}
