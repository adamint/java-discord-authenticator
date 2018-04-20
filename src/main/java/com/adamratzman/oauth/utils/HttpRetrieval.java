package com.adamratzman.oauth.utils;

import com.adamratzman.oauth.main.DiscordAuthenticator;
import com.adamratzman.oauth.models.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class HttpRetrieval {
    private static final String baseUrl = "https://discordapp.com/api";

    public static Token retrieveToken(DiscordAuthenticator authenticator, String code) {
        try {
            return authenticator.getGson().fromJson(
                    getConnection(authenticator, "/oauth2/token")
                            .header("content-type", "application/x-www-form-urlencoded")
                            .header("authorization", "Bearer $code")
                            .header("cache-control", "no-cache")
                            .data("client_id", authenticator.getBotId())
                            .data("client_secret", authenticator.getBotSecret())
                            .data("grant_type", "authorization_code")
                            .data("redirect_uri", authenticator.getRedirectUrl())
                            .data("code", code).post().text()
                    , Token.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String retrieveJson(DiscordAuthenticator authenticator, String accessToken, DiscordScope scope) {
        try {
            return getConnection(authenticator, scope.getRoute()).header("Authorization", "Bearer " + accessToken)
                    .header("cache-control", "no-cache").get().text();
        } catch (IOException iox) {
            iox.printStackTrace();
            return null;
        }
    }

    private static Connection getConnection(DiscordAuthenticator authenticator, String route) {
        return Jsoup.connect(baseUrl + route).userAgent(authenticator.getBotName() + ": "
                + authenticator.getVersion()).ignoreContentType(true);
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
