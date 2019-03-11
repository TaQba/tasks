package com.crud.tasks.dto;

import com.crud.tasks.domain.TrelloAttachmentsByTypeDto;
import com.crud.tasks.domain.TrelloBadgesDto;
import com.crud.tasks.domain.TrelloDto;
import org.junit.Assert;
import org.junit.Test;

public class TrelloBadgesDtoTestSuite {

    @Test
    public void shouldBuildTrelloBadgesDto() {
        //Given
        TrelloDto trelloDto = new TrelloDto("board","card");
        TrelloAttachmentsByTypeDto trelloAttachmentsByTypeDto = new TrelloAttachmentsByTypeDto(trelloDto);

        //When
        TrelloBadgesDto dto = new TrelloBadgesDto("foo", trelloAttachmentsByTypeDto);

        //Then
        Assert.assertEquals("foo", dto.getVotes());
        Assert.assertEquals("card", dto.getAttachmentsByType().getTrello().getCard());
        Assert.assertEquals("board", dto.getAttachmentsByType().getTrello().getBoard());
    }

    @Test
    public void shouldEmptyTelloDto() {
        //When
        TrelloBadgesDto dto = new TrelloBadgesDto();

        //Then
        Assert.assertNull(dto.getVotes());
        Assert.assertNull(dto.getAttachmentsByType());
    }
}
