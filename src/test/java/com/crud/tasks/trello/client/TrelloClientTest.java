package com.crud.tasks.trello.client;

import com.crud.tasks.domain.Badges;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest{

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Before
    public void init() {
        Mockito.when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        Mockito.when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        Mockito.when(trelloConfig.getTrelloToken()).thenReturn("test");
    }

    @Test
    public void testShouldFetchTrelloBoard() throws URISyntaxException {
        //Given
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());


        URI url = new URI("http://test.com/members/null/boards?key=test&token=test&fields=name,%20id&lists=all");

        Mockito.when(restTemplate.getForObject(url, TrelloBoardDto[].class)).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        Assert.assertEquals(1, fetchedTrelloBoards.size());
        Assert.assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        Assert.assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        Assert.assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void testShouldCreateCard() throws URISyntaxException {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id");

        URI url = new URI("http://test.com/cards/?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
                "1",
                "Test task",
                "http://test.com",
                new Badges());

        Mockito.when(restTemplate.postForObject(url, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);
        //When
        CreatedTrelloCard newCart = trelloClient.createNewCard(trelloCardDto);
        //Then
        Assert.assertEquals("1", newCart.getId());
        Assert.assertEquals("Test task", newCart.getName());
        Assert.assertEquals("http://test.com", newCart.getShortUrl());
    }

    @Test
    public void testShouldReturnEmptyList() throws URISyntaxException {
        //Given
        URI url = new URI("http://test.com/members/null/boards?key=test&token=test&fields=name,%20id&lists=all");

        Mockito.when(restTemplate.getForObject(url, TrelloBoardDto[].class)).thenReturn(null);
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloClient.getTrelloBoards();

        //Then
        Assert.assertEquals(0, trelloBoardDtoList.size());
    }
}