package com.ochobits.optica.Utils.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ochobits.optica.Utils.PageDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseObject <T>{

    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PageDetails page;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

}
