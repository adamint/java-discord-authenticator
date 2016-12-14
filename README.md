# Java-Discord-OAuth-Librarysadf
OAuth Library for easy Authentication and Querying

First, create an OAuthManager. You need to pass a BotSettings object to the manager.
If you want a correct `User-Agent`, pass the OAuthSettings object to the manager constructor as well, like this.

```java

 Settings.OAuthSettings oAuthSettings = new OAuthSettings("MyBot", "http://mywebsite.com", "http://yourredirecturl.com", "1.0 BETA");
 BotSettings botSettings = new BotSettings("clientId", "clientSecret", "botToken");



```
