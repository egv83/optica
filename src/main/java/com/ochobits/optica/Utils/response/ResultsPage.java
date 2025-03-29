package com.ochobits.optica.Utils.response;


import com.ochobits.optica.Utils.PageDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResultsPage<T>{

    private List<T> content;
    private PageDetails pageDetails;

}
