package com.ochobits.optica.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageDetails {

    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;

}
