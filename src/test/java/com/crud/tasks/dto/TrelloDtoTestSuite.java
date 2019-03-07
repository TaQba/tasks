package com.crud.tasks.dto;

import com.crud.tasks.domain.TrelloDto;
import org.junit.Assert;
import org.junit.Test;

public class TrelloDtoTestSuite {
    @Test
    public void shouldBuildTrelloDto() {
        //When
        TrelloDto dto = new TrelloDto("board", "card");

        //Then
        Assert.assertEquals("board", dto.getBoard());
        Assert.assertEquals("card", dto.getCard());
    }
    @Test
    public void shouldEmptyTelloDto() {
        //When
        TrelloDto dto = new TrelloDto();

        //Then
        Assert.assertNull(dto.getBoard());
        Assert.assertNull(dto.getCard());
    }
}
