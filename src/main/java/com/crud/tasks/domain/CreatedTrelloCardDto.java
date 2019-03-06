package com.crud.tasks.domain;

import com.crud.tasks.domain.TrelloBadgesDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCardDto {
    @JsonProperty("id")
    private String id;
//    @JsonProperty("badges")
//    private TrelloBadgesDto lists;
    private String name;
    private String shortUrl;
}
