# Java Discord OAuth Library Version 1.0
OAuth Library for easy Authentication and Querying, mainly for web server authentication.

# Jitpack gradle link is below
https://jitpack.io/#adrat205/DiscordOAuth-Java/-SNAPSHOT

First, create an OAuthManager. You need to pass a BotSettings object to the manager.
# YOU MUST CREATE BOTH BotSettings AND OAuthSettings. Your OAuthSettings redirect URL MUST BE the same as defined in your application. HTTP OR HTTPS IS REQUIRED!!!!!

```java

Settings.OAuthSettings oAuthSettings = new OAuthSettings("MyBot", "http://mywebsite.com", "http://yourredirecturl.com", "1.0 BETA");
BotSettings botSettings = new BotSettings("clientId", "clientSecret", "botToken");

OAuthManager manager = new OAuthManager(oAuthSettings, botSettings);
```
Make sure your Discord OAuth URL redirects back to your redirect url. For example, mine is 
https://discordapp.com/oauth2/authorize?client_id=247093143160356865&scope=identify&redirect_uri=http://ardentbot.tk/portal&response_type=code

Your response type NEEDS to be code!

Now, you have access to all of the convenience methods. However, you MUST make sure that the scopes you identified in your URL match the methods you are calling. For example, if you only have the `identify` scope, you cannot call `getUserWithEmail`, but you can call `getUser`, which returns an `Identify` object. **You will get an OAuthException if anything goes wrong, which should tell you what you have wrong.**

`manager.getApplicationInfo()` returns an `Application` object with description, owner, name, etc. fields

You have two options to use the rest of the methods.
  1. Use the code sent in the GET parameters by Discord. However, you can ONLY use any generated code once if you use the code as a parameter.
  2. A much smarter method is to retrieve a `Token` object first. You can check when a token will expire by comparing the creation time with the `expires_in` (integer) field.
  
The following methods are included in the beta:
- `getUser(Token OR code accepted)` -> returns a `Identify` object, which is a user's information, just without email. This is the most useful for authentication. [requires the identify scope]
- `getUserWithEmail(Token OR code accepted)` -> returns a `User` object, which is identical to the above method, but also includes an email field.
- `getUserGuilds(Token OR code accepted)` -> returns a List of Guilds that the user is in. Good for a web panel or user statistics.
- `getUserConnections(Token OR code accepted)` -> returns a List of the connections that a user has (youtube, steam, twitch, etc).

# Again, this is a barebones library, mainly meant just for authentication.
