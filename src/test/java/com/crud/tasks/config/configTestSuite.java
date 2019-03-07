package com.crud.tasks.config;

import lombok.Value;
import org.junit.Assert;
import org.junit.Test;

public class configTestSuite {
    @Test
    public void shouldHaveAdminConfig() {
        //Given
        AdminConfig config = new AdminConfig();
        //When
        //Then
        Assert.assertNull(config.getAdminMail());
    }

    @Test
    public void shouldHaveTrelloConfig() {
        //Given
        TrelloConfig config = new TrelloConfig();
        //When
        //Then
        Assert.assertNull(config.getTrelloApiEndpoint());
        Assert.assertNull(config.getTrelloAppKey());
        Assert.assertNull(config.getTrelloToken());
        Assert.assertNull(config.getTrelloUsername());
    }
}
