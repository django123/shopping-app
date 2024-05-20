package com.django.it.shoppingapp.common;

import lombok.*;

import java.util.List;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PageResponse<T> {
    private List<T> content;
    private int number;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
}
