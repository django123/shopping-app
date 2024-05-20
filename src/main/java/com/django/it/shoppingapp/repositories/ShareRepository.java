package com.django.it.shoppingapp.repositories;

import com.django.it.shoppingapp.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share, Integer> {
}
