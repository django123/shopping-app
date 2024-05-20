package com.django.it.shoppingapp.repositories;

import com.django.it.shoppingapp.model.Shopping;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingRepository extends JpaRepository<Shopping, Integer> {

    List<Shopping> findByUsers_Id(Integer id);
    List<Shopping> findByArchived(boolean archived);
    List<Shopping> findByShared(boolean shared);
}
