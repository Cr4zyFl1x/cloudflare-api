# Cloudflare API v4 Java SDK
[![License][license-img]][license-url]
![CI](https://ci.rz.kleine-vorholt.eu/app/rest/builds/buildType:(id:CloudflareApiSdk_GeneralBuild)/statusIcon)

This Cloudflare API Library / Java SDK communicates with the [Cloudflare API v4](https://api.cloudflare.com/) and
allows you to manage components like dns records, zones, user settings and more*.

*Currently only record and zone management is implemented. Feel free to contribute.

## Build configuration
You can easily integrate this library into your existing project by defining it as a __Maven__ or __Gradle__ dependency

To see the **available versions**, just take a look at the **nexus artifacts [here](https://nexus.rz.kleine-vorholt.eu/#browse/browse:maven-opensource:nrw%2Fflorian%2Fsdk%2Fcloudflare-api)**.

**Maven**
```xml
<dependency>
    <groupId>nrw.florian.sdk</groupId>
    <artifactId>cloudflare-api</artifactId>
    <version>%VERSION_TAG_HERE%</version>
</dependency>
	
<repositories>
  <repository>
    <id>nexus.rz.kleine-vorholt.eu</id>
    <url>https://nexus.rz.kleine-vorholt.eu/repository/maven-opensource/</url>
  </repository>
</repositories>
```

**Gradle DSL**
```groovy
dependencies {
  compile 'nrw.florian.sdk:cloudflare-api:%VERSION_TAG_HERE%'
}

repositories {
  maven {
    url =  'https://nexus.rz.kleine-vorholt.eu/repository/maven-opensource/'
  }
}
```

## Features
This project is still in progress. You're welcome to contribute to this project.
- Management of Zones
    - Deletion, Creation
- Management of DNS-Records
    - Getting them (filtered, unfiltered by properties)
    - Bulk deletion and creation
    - Save or Update functionality
    - Single delete

## Getting started
First, create the CloudflareCredentials object.

Either use the configurable (with scope) API-Token: 
```java
final String CF_API_TOKEN = "YOUR_API_TOKEN_HERE";
final CloudflareCredentials credentials = new CloudflareCredentials(CF_API_TOKEN);
```

Or use the global API-Key in combination with your account mail:
```java
final String CF_API_KEY  = "YOUR_API_KEY_HERE";
final String CF_API_MAIL = "YOUR_MAIL_HERE";
final CloudflareCredentials credentials = new CloudflareCredentials(CF_API_KEY, CF_API_MAIL); 
```

After that you can create the instance of the CloudflareClient:
```java
final CloudflareCredentials credentials = ...;
final CloudflareClient client = CloudflareClient.of(credentials);
```

After that, for example you can get a list of all zones in your account:
```java
final List<Zone> zones = client.zone().getAll();
```

To make ist easy using this SDK, we're always aimed to provide JavaDocs whereas they're needed.

## Other
- Check out the [wiki][wiki-url].

### License
Licensed under GNU General Public License v3.0. See the [LICENSE](LICENSE) file for details.