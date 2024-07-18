package com.django.it.shoppingapp.services.impl;

import com.django.it.shoppingapp.common.PageResponse;
import com.django.it.shoppingapp.dtos.requests.ShoppingRequest;
import com.django.it.shoppingapp.dtos.responses.ShoppingResponse;
import com.django.it.shoppingapp.mapper.ShoppingMapper;
import com.django.it.shoppingapp.model.Share;
import com.django.it.shoppingapp.model.Shopping;
import com.django.it.shoppingapp.model.User;
import com.django.it.shoppingapp.repositories.ShareRepository;
import com.django.it.shoppingapp.repositories.ShoppingRepository;
import com.django.it.shoppingapp.repositories.UserRepository;
import com.django.it.shoppingapp.services.ShoppingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ShoppingServiceImplement implements ShoppingService {

    private final ShoppingRepository shoppingRepository;
    private final ShoppingMapper shoppingMapper;
    private final UserRepository userRepository;
    private final ShareRepository shareRepository;

    @Override
    public PageResponse<ShoppingResponse> findAllShopping(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Shopping> shoppingPage = shoppingRepository.findAll(pageable);
        List<ShoppingResponse> shoppingResponses = shoppingPage.stream()
                .map(shoppingMapper::mapToShoppingResponse)
                .toList();
        return new PageResponse<>(
                shoppingResponses,
                shoppingPage.getNumber(),
                shoppingPage.getSize(),
                shoppingPage.getTotalPages(),
                (int) shoppingPage.getTotalElements(),
                shoppingPage.isFirst(),
                shoppingPage.isLast()
        );
    }

    @Override
    public ShoppingResponse findShoppingById(Integer id) {
        return shoppingRepository.findById(id)
                .map(shoppingMapper::mapToShoppingResponse)
                .orElseThrow(()-> new EntityNotFoundException("Not shopping found with id: " + id));
    }

    @Override
    public void addShopping(ShoppingRequest shoppingRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        User user1 = userRepository.findById(user.getId()).orElseThrow(()-> new EntityNotFoundException("Not user found with id: " + user.getId()));
        Shopping shopping = shoppingMapper.mapToShopping(shoppingRequest);
        shopping.setUsers(new HashSet<>(Arrays.asList(user1)));
        shopping.setSaverName(user1.fullName());
        shopping.setArchived(false);
        shopping.setStatut(false);
        shopping.setShared(false);
        shoppingRepository.save(shopping);
    }

    @Override
    public void updateShopping(ShoppingRequest shoppingRequest,Integer id) {
        Shopping shopping = shoppingRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Not shopping found with id: " + id));
        shoppingMapper.mapToShopping(shoppingRequest);
        shoppingRepository.save(shopping);
    }

    @Override
    public void deleteShoppingById(Integer id) {
        shoppingRepository.deleteById(id);
    }

    @Override
    public void shareShopping(Share share,  Integer shopId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user =((User) authentication.getPrincipal());
        User user1 = userRepository.findById(user.getId()).orElseThrow(()-> new EntityNotFoundException("Not user found with id: " + user.getId()));
        Shopping shopping = shoppingRepository.findById(shopId).orElseThrow(()-> new EntityNotFoundException("Not shopping found with id: " + shopId));
        shopping.addUser(user1);
        shopping.setShared(true);
        share.setId(user.getId());
        share.setShoppingId(shopping.getId());
        shareRepository.save(share);
    }

    @Override
    public List<ShoppingResponse> findAllShoppingArchived( ) {
        Authentication connectedUser = SecurityContextHolder.getContext().getAuthentication();
        User user =((User) connectedUser.getPrincipal());
        List<Shopping> shoppings = shoppingRepository.findByArchived(true);
        List<Shopping> shoppings2 = shoppingRepository.findByUsers_Id(user.getId());
        List<Shopping> shoppingList = new ArrayList<>();

        for (Shopping shopping : shoppings) {
            for (Shopping shopping2 : shoppings2) {
                if (shopping.getId().equals(shopping2.getId())) {
                    shoppingList.add(shopping);
                }
            }
        }

        return  shoppingList.stream()
                .map(shoppingMapper::mapToShoppingResponse)
                .toList();
    }

    @Override
    public void archived(Integer id) {
        Shopping shopping = shoppingRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Shopping not found"));
        if (shopping.isArchived()){
            shopping.setArchived(false);
        }else {
            shopping.setArchived(true);
        }
      shoppingRepository.save(shopping);
    }

    @Override
    public List<ShoppingResponse> sharedShopping(){
        Authentication connectedUser = SecurityContextHolder.getContext().getAuthentication();
        User user =((User) connectedUser.getPrincipal());
        List<Shopping> shoppings1 = shoppingRepository.findByShared(true);
        List<Shopping> shoppings2 = shoppingRepository.findByUsers_Id(user.getId());
        List<Shopping> shoppings3 =new ArrayList<>();

        for (Shopping shopping : shoppings1) {
            for (Shopping shopping2 : shoppings2) {
                if (shopping.getId().equals(shopping2.getId())) {
                    shoppings3.add(shopping2);
                }
            }
        }

        return shoppings3.stream()
                .map(shoppingMapper::mapToShoppingResponse)
                .toList();

    }
}
