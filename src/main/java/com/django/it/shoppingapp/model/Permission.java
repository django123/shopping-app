package com.django.it.shoppingapp.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    READ_PRIVILEGE,
    WRITE_PRIVILEGE,
    DELETE_PRIVILEGE,
    UPDATE_PRIVILEGE,
    ;
}
