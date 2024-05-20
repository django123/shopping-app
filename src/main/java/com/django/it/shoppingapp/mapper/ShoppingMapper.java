package com.django.it.shoppingapp.mapper;

import com.django.it.shoppingapp.dtos.requests.ShoppingRequest;
import com.django.it.shoppingapp.dtos.responses.ShoppingResponse;
import com.django.it.shoppingapp.model.Shopping;
import org.springframework.stereotype.Service;

@Service
public class ShoppingMapper {

    public Shopping mapToShopping(ShoppingRequest request){
        return Shopping.builder()
                .name(request.name())
                .comment(request.comment())
                .archived(request.archived())
                .statut(request.statut())
                .shared(request.shared())
                .saverName(request.saverName())
                .build();
    }


    public ShoppingResponse mapToShoppingResponse(Shopping shopping){
        return ShoppingResponse.builder()
                .name(shopping.getName())
                .comment(shopping.getComment())
                .archived(shopping.isArchived())
                .shared(shopping.isShared())
                .saverName(shopping.getSaverName())
                .statut(shopping.isStatut())
                .build();
    }
}
