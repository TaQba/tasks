package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class trelloMapperTestSuite {
    @Test
    public void shouldMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        List<TrelloListDto> trelloListDtoList1 = new ArrayList<>();
        List<TrelloListDto> trelloListDtoList2 = new ArrayList<>();
        trelloListDtoList2.add(new TrelloListDto("name", "id1", true));
        trelloBoardDto.add(new TrelloBoardDto("1","One",trelloListDtoList1));
        trelloBoardDto.add(new TrelloBoardDto("2","Two",trelloListDtoList2));

        //When
        TrelloMapper mapper = new TrelloMapper();
        List<TrelloBoard> boards = mapper.mapToBoards(trelloBoardDto);

        //Then
        Assert.assertEquals(2, boards.size());
        Assert.assertEquals(TrelloBoard.class, boards.get(0).getClass());
        Assert.assertEquals("One", boards.get(0).getName());
        Assert.assertEquals("Two", boards.get(1).getName());
        Assert.assertEquals(0 ,boards.get(0).getLists().size());
        Assert.assertEquals(TrelloList.class ,boards.get(1).getLists().get(0).getClass());
        Assert.assertEquals(1, boards.get(1).getLists().size());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloList1 = new ArrayList<>();
        List<TrelloList> trelloList2 = new ArrayList<>();
        trelloList2.add(new TrelloList("name", "id1", true));
        trelloBoards.add(new TrelloBoard("name1", "One", trelloList1));
        trelloBoards.add(new TrelloBoard("name1", "Two", trelloList2));

        //When
        TrelloMapper mapper = new TrelloMapper();
        List<TrelloBoardDto> boards = mapper.mapToBoardsDto(trelloBoards);

        //Then
        Assert.assertEquals(2, boards.size());
        Assert.assertEquals(TrelloBoardDto.class, boards.get(0).getClass());
        Assert.assertEquals("One", boards.get(0).getName());
        Assert.assertEquals("Two", boards.get(1).getName());
        Assert.assertEquals(0 ,boards.get(0).getLists().size());
        Assert.assertEquals(TrelloListDto.class ,boards.get(1).getLists().get(0).getClass());
        Assert.assertEquals(1, boards.get(1).getLists().size());
    }

    @Test
    public void shouldMapToList() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("name", "id1", true));
        //When
        TrelloMapper mapper = new TrelloMapper();
        List<TrelloList> boards = mapper.mapToList(trelloListDto);

        //Then
        Assert.assertEquals(1, boards.size());
        Assert.assertEquals(TrelloList.class, boards.get(0).getClass());
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        List<TrelloList> TrelloLists = new ArrayList<>();
        TrelloLists.add(new TrelloList("name", "id1", true));
        //When
        TrelloMapper mapper = new TrelloMapper();
        List<TrelloListDto> boards = mapper.mapToListDto(TrelloLists);

        //Then
        Assert.assertEquals(1, boards.size());
        Assert.assertEquals(TrelloListDto.class, boards.get(0).getClass());
    }

    @Test
    public void shouldMapToCardDto() {

        //Given
        TrelloCard trelloCard = new TrelloCard("name1","desc1","pos1","lid1");
        //When
        TrelloMapper mapper = new TrelloMapper();
        TrelloCardDto dto = mapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals("name1", dto.getName());
        Assert.assertEquals(TrelloCardDto.class, dto.getClass());
    }

    @Test
    public void shouldMapToCard() {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name1","desc1","pos1","lid1");
        //When
        TrelloMapper mapper = new TrelloMapper();
        TrelloCard card = mapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals("name1", card.getName());
        Assert.assertEquals(TrelloCard.class, card.getClass());
    }

}
