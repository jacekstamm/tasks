package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void mapToBoards() {
        //Given
        TrelloListDto trelloListTest1 = new TrelloListDto("List 1", "Test List 1", false);
        TrelloListDto trelloListTest2 = new TrelloListDto("List 2", "Test List 2", false);
        TrelloListDto trelloListTest3 = new TrelloListDto("List 3", "Test List 3", false);
        TrelloListDto trelloListTest4 = new TrelloListDto("List 4", "Test List 4", false);
        TrelloListDto trelloListTest5 = new TrelloListDto("List 5", "Test List 5", false);

        List<TrelloListDto> trelloListDtoList1 = new ArrayList<>();
        List<TrelloListDto> trelloListDtoList2 = new ArrayList<>();

        trelloListDtoList1.add(trelloListTest1);
        trelloListDtoList1.add(trelloListTest2);
        trelloListDtoList1.add(trelloListTest3);
        trelloListDtoList2.add(trelloListTest4);
        trelloListDtoList2.add(trelloListTest5);

        TrelloBoardDto trelloBoardDtoTest1 = new TrelloBoardDto("Board 1", "Test Board 1", trelloListDtoList1);
        TrelloBoardDto trelloBoardDtoTest2 = new TrelloBoardDto("Board 2", "Test Board 2", trelloListDtoList2);

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDtoTest1);
        trelloBoardDtoList.add(trelloBoardDtoTest2);

        //When
        List<TrelloBoard> trelloBoardTestList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(2, trelloBoardTestList.size());
        assertEquals("Board 1", trelloBoardTestList.get(0).getId());
        assertEquals("Board 2", trelloBoardTestList.get(1).getId());
        assertEquals("Test List 3", trelloBoardTestList.get(0).getLists().get(2).getName());
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("List 1", "Test List 1", false);
        TrelloList trelloList2 = new TrelloList("List 2", "Test List 2", false);
        TrelloList trelloList3 = new TrelloList("List 3", "Test List 3", false);
        TrelloList trelloList4 = new TrelloList("List 4", "Test List 4", false);

        List<TrelloList> trelloTestList1 = new ArrayList<>();
        List<TrelloList> trelloTestList2 = new ArrayList<>();

        trelloTestList1.add(trelloList1);
        trelloTestList1.add(trelloList2);
        trelloTestList2.add(trelloList3);
        trelloTestList2.add(trelloList4);

        TrelloBoard trelloTestBoard1 = new TrelloBoard("Test Board 1", "Board 1", trelloTestList1);
        TrelloBoard trelloTestBoard2 = new TrelloBoard("Test Board 2", "Board 2", trelloTestList2);

        List<TrelloBoard> testBoardList = new ArrayList<>();
        testBoardList.add(trelloTestBoard1);
        testBoardList.add(trelloTestBoard2);

        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(testBoardList);

        //Then
        assertEquals("Test Board 1", result.get(0).getId());
        assertEquals("Test Board 2", result.get(1).getId());
        assertEquals("List 1", result.get(0).getLists().get(0).getId());
    }

    @Test
    public void mapToList() {
        //Given
        TrelloListDto trelloListTest1 = new TrelloListDto("List 1", "Test List 1", false);
        TrelloListDto trelloListTest2 = new TrelloListDto("List 2", "Test List 2", false);
        TrelloListDto trelloListTest3 = new TrelloListDto("List 3", "Test List 3", false);
        TrelloListDto trelloListTest4 = new TrelloListDto("List 4", "Test List 4", false);

        List<TrelloListDto> testTrelloList = new ArrayList<>();
        testTrelloList.add(trelloListTest1);
        testTrelloList.add(trelloListTest2);
        testTrelloList.add(trelloListTest3);
        testTrelloList.add(trelloListTest4);

        //When
        List<TrelloList> result = trelloMapper.mapToList(testTrelloList);

        //Then
        assertEquals("Test List 1", result.get(0).getName());
        assertFalse(result.get(1).isClosed());
        assertEquals("List 4", result.get(3).getId());
    }

    @Test
    public void mapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("List 1", "Test List 1", false);
        TrelloList trelloList2 = new TrelloList("List 2", "Test List 2", false);
        TrelloList trelloList3 = new TrelloList("List 3", "Test List 3", false);
        TrelloList trelloList4 = new TrelloList("List 4", "Test List 4", false);

        List<TrelloList> testTrelloList = new ArrayList<>();
        testTrelloList.add(trelloList1);
        testTrelloList.add(trelloList2);
        testTrelloList.add(trelloList3);
        testTrelloList.add(trelloList4);

        //When
        List<TrelloListDto> result = trelloMapper.mapToListDto(testTrelloList);

        //Then
        assertEquals("List 1", result.get(0).getId());
        assertEquals("Test List 3", result.get(2).getName());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Trello Card", "Trello Card Test", "pos", "list");
        //When
        TrelloCardDto result = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("Trello Card", result.getName());
        assertEquals("Trello Card Test", result.getDescription());
        assertEquals("pos", result.getPos());
        assertEquals("list", result.getListId());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Trello Card", "Trello CardDto Test", "pos", "list");
        //When
        TrelloCard result = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("Trello Card", result.getName());
        assertEquals("Trello CardDto Test", result.getDescription());
    }
}