package com.adamratzman.oauth.main;

import com.adamratzman.oauth.models.Token;
import com.adamratzman.oauth.utils.HttpRetrieval;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscordAuthenticatorTest {
    DiscordAuthenticator authenticator = new DiscordAuthenticator.Builder()
            .setOauthConfiguration("https://ardentbot.com", "https://ardentbot.com/oauth")
            .setBotConfiguration("Ardent", "414618291265601547", "kw856BzuFFUkAgt6y2xg5yzALwkrrs1N", "NDE0NjE4MjkxMjY1NjAxNTQ3.DbuKkw.RKqFArrDXxfHRvmobPh8pxRN-tY")
            .build();
    Token token = authenticator.getToken("ryA0jidaYF4aVIxEGfWhfPVM6UR3Ai");
    @Test
    void getUserWithEmail() {
        System.out.println(authenticator.getUserWithEmail(token).getEmail());
    }

    @Test
    void getUser() {
        System.out.println(authenticator.getUser(token).getUsername());
    }

    @Test
    void getUserGuilds() {
        System.out.println(authenticator.getUserGuilds(token)[1].getName());
    }

    @Test
    void getUserConnections() {
        System.out.println(authenticator.getUserConnections(token)[1].getName());
    }

}