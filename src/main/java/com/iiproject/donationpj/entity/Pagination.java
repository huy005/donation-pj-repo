package com.iiproject.donationpj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagination implements Serializable {
    private static final long serialVersionUID = 1L;
    private String paginationId;
    private String keyword;
    private boolean first;
    private boolean last;
    private int number;
    private int numberOfElements;
    private int size;
    private long totalElements;
    private int totalPages;

//    private List objectList;
}
