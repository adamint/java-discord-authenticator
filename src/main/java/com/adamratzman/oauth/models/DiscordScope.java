package com.adamratzman.oauth.models;

public enum DiscordScope {
    CONNECTIONS("/users/@me/connections"),
    EMAIL("/users/@me"),
    IDENTIFY("/users/@me"),
    GUILDS("/users/@me/guilds"),
    BOT_INFORMATION("/oauth2/applications/@me");

    private String route;

    DiscordScope(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}

