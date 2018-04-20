package com.adamratzman.oauth.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

public class Token {
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
    @SerializedName("scope")
    @Expose
    private String scope;

    public Token(String accessToken, String tokenType, Integer expiresIn, String refreshToken, String scope) {
        super();
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.scope = scope;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public ArrayList<DiscordScope> getScopes() {
        ArrayList<DiscordScope> scopes = new ArrayList<>();
        String[] args = scope.split(" ");
        Arrays.stream(args).forEach(arg -> {
            if (arg.equalsIgnoreCase("connections")) scopes.add(DiscordScope.CONNECTIONS);
            else if (arg.equalsIgnoreCase("identify")) scopes.add(DiscordScope.IDENTIFY);
            else if (arg.equalsIgnoreCase("email")) scopes.add(DiscordScope.EMAIL);
            else if (arg.equalsIgnoreCase("guilds")) scopes.add(DiscordScope.GUILDS);
        });
        return scopes;
    }
}