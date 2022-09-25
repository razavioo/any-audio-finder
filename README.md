# About

Server-side project for any music finder APIs.

# Technology

Technologies used for this project are as follows.
Ktor(server-side), Exposed(database handler with JDBC support), Kotlin Coroutines, Selenium, Koin (DI)

# Docker configuration

To run Selenium grid docker image, run the following command:

```docker
sudo docker run -d -p 4444:4444 --shm-size="2g" selenium/standalone-chrome:4.4.0-20220812
```

For further information, please
visit [https://github.com/SeleniumHQ/docker-selenium](https://github.com/SeleniumHQ/docker-selenium).

# Debug mode

To enable debug mode, add these two environment variables while running app:

``DEBUG_MODE=true``<br>``SELENIUM_URL=127.0.0.1``