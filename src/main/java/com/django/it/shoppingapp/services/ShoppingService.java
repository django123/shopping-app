package com.django.it.shoppingapp.services;

import com.django.it.shoppingapp.common.PageResponse;
import com.django.it.shoppingapp.dtos.requests.ShoppingRequest;
import com.django.it.shoppingapp.dtos.responses.ShoppingResponse;


import com.django.it.shoppingapp.model.Share;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface ShoppingService {

    PageResponse<ShoppingResponse> findAllShopping(int page, int size);
    ShoppingResponse findShoppingById(Integer id);
    void addShopping(ShoppingRequest shoppingRequest, Authentication authentication);
    void updateShopping(ShoppingRequest shoppingRequest,Integer id);
    void deleteShoppingById(Integer id);
    void shareShopping(Share share, Integer shopId, Authentication authentication);
    List<ShoppingResponse> findAllShoppingArchived(Authentication connectedUser);
    void archived(Integer id);
    List<ShoppingResponse> sharedShopping(Authentication connectedUser);
}
