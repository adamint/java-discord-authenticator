package com.adamratzman.oauth.main;

import com.adamratzman.oauth.models.*;
import com.google.gson.Gson;

import static com.adamratzman.oauth.utils.HttpRetrieval.*;

public class DiscordAuthenticator {
    private Gson gson = new Gson();
    private String botId;
    private String botSecret;
    private String botToken;

    private String botName;
    private String websiteUrl;
    private String redirectUrl;
    private String version = "unversioned";

    private DiscordAuthenticator() {
    }

    private DiscordAuthenticator(String botId, String botSecret, String botToken, String botName, String websiteUrl, String redirectUrl,
                                 String version) {
        this.botId = botId;
        this.botSecret = botSecret;
        this.botToken = botToken;
        this.botName = botName;
        this.websiteUrl = websiteUrl;
        this.redirectUrl = redirectUrl;
        this.version = version;
    }

    public Token getToken(String code) {
        return retrieveToken(this, code);
    }

    public Email getUserWithEmail(String code) {
        return getUserWithEmail(retrieveToken(this, code));
    }

    public Email getUserWithEmail(Token token) {
        if (token.getScopes().contains(DiscordScope.EMAIL)) {
            return this.getGson().fromJson(retrieveJson(this, token.getAccessToken(), DiscordScope.EMAIL), Email.class);
        } else throw new IllegalArgumentException("The token doesn't have access to /users/@me (DiscordScope: email)");
    }

    public Identify getUser(String code) {
        return getUser(retrieveToken(this, code));
    }

    public Identify getUser(Token token) {
        if (token.getScopes().contains(DiscordScope.IDENTIFY)) {
            return this.getGson().fromJson(retrieveJson(this, token.getAccessToken(), DiscordScope.IDENTIFY), Identify.class);
        } else
            throw new IllegalArgumentException("The token doesn't have access to /users/@me (DiscordScope: identify)");
    }


    public Guild[] getUserGuilds(String code) {
        return getUserGuilds(retrieveToken(this, code));
    }

    public Guild[] getUserGuilds(Token token) {
        if (token.getScopes().contains(DiscordScope.GUILDS)) {
            return this.getGson().fromJson(retrieveJson(this, token.getAccessToken(), DiscordScope.GUILDS), Guild[].class);
        } else
            throw new IllegalArgumentException("The token doesn't have access to /users/@me/guilds (DiscordScope: guilds)");
    }

    public DiscordConnection[] getUserConnections(String code) {
        return getUserConnections(retrieveToken(this, code));
    }

    public DiscordConnection[] getUserConnections(Token token) {
        if (token.getScopes().contains(DiscordScope.CONNECTIONS)) {
            return this.getGson().fromJson(retrieveJson(this, token.getAccessToken(), DiscordScope.CONNECTIONS), DiscordConnection[].class);
        } else
            throw new IllegalArgumentException("The token doesn't have access to /users/@me/connections (DiscordScope: connections)");
    }


    public static class Builder {
        private String botId;
        private String botSecret;
        private String botToken;

        private String botName;
        private String websiteUrl;
        private String redirectUrl;
        private String version = "unversioned";

        public DiscordAuthenticator build() {
            if (botId != null && botSecret != null && botToken != null && botName != null && redirectUrl != null) {
                return new DiscordAuthenticator(botId, botSecret, botToken, botName, websiteUrl, redirectUrl, version);
            } else
                throw new IllegalArgumentException("One or more of the following fields are null: botId, botSecret, " +
                        "botToken, botName or redirectUrl");
        }

        public Builder setBotConfiguration(String botName, String botId, String botSecret, String botToken) {
            this.botId = botId;
            this.botSecret = botSecret;
            this.botToken = botToken;
            this.botName = botName;
            return this;
        }

        public Builder setOauthConfiguration(String websiteUrl, String redirectUrl) {
            this.websiteUrl = websiteUrl;
            this.redirectUrl = redirectUrl;
            return this;
        }

        public Builder setBotVersion(String version) {
            this.version = version;
            return this;
        }
    }

    public String getBotId() {
        return botId;
    }

    public String getBotSecret() {
        return botSecret;
    }

    public String getBotToken() {
        return botToken;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public Gson getGson() {
        return gson;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBotName() {
        return botName;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}
