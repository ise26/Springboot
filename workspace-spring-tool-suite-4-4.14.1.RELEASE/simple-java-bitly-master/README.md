# Simple java client for Bit.ly

A very simple URL shortener and expander for Java using Bit.ly API.

## Prerequisite

* You need to have an account on *bitly.com*. If you don't, you can create an account [here](https://bitly.com/a/sign_up).

* Then you need to generate a access token [there](https://bitly.com/a/oauth_apps). You will need this token to use this lib. 

## Installation with Maven

> pom.xml

```xml
<dependency>
	<groupId>com.github.romain-warnan</groupId>
	<artifactId>simple-java-bitly</artifactId>
	<version>1.1</version>
</dependency>
```

## Usage

You need the access token to get an instance of Bitly object.
```java
String access_token = "?"
Bitly bitly = Bit.ly(access_token);
```
Once you have an instance of Bitly, you can easily shorten or expand an URL:
```java
String shortUrl = bitly.shorten("https://github.com/romain-warnan/simple-java-bitly");
String longUrl = bitly.expand("http://bit.ly/2cNk0Gp");
```
> Note that the long URL doesn't has to be URL encoded to be shorten.

Of course if you don't need to use the instance later, there is a one-liner:
```java
String shortUrl = Bit.ly(access_token)
	.shorten("https://github.com/romain-warnan/simple-java-bitly");
```

## Usage with a proxy

Either:
```java
Bit.ly(access_token)
	.proxyUri("http://proxy.host.com:port")
	.shorten(longUrl);
```

Or:
```java
Bit.ly(access_token)
	.proxyUri("http://proxy.host.com:port")
	.proxyUsername("username")
	.proxyPassword("password")
	.shorten(longUrl);
```
