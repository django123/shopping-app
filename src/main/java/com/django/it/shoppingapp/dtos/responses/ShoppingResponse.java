package com.django.it.shoppingapp.dtos.responses;

import lombok.*;

@Setter @Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ShoppingResponse {


    private String name;

    private String comment;

    private boolean statut;

    private boolean archived;

    private boolean shared;

    private String saverName;
}
