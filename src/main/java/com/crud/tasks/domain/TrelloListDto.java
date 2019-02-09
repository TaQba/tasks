package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloListDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;

    @JsonProperty("isClosed")
    private boolean isClosed;
}
