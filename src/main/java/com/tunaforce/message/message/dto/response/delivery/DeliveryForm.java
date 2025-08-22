package com.tunaforce.message.message.dto.response.delivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryForm<T> {
    private List<T> data;
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
    private String sort;
}
