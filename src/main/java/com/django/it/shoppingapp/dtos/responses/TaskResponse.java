package com.django.it.shoppingapp.dtos.responses;

import lombok.*;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class TaskResponse {

    private String name;
    private String description;
    private boolean status;
    private String shopping;
}
