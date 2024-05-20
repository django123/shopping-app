package com.django.it.shoppingapp.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ShoppingRequest(
        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String name,
        @NotNull(message = "200")
        @NotEmpty(message = "200")
        String comment,
        boolean statut,
        boolean archived,
        boolean shared,
        String saverName

) {
}
