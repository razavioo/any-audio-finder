## Introduction

![any-audio-finder-cover](cover.png)

Any Audio Finder is a web server that allows users to search for and download audio files from the internet.
It uses a combination of web scraping and search engine APIs to locate audio files online, and stores them
in a database for easy access from any client.

## Technology

- `Ktor`: a framework for building server-side applications in Kotlin
- `Exposed`: a library for handling database interactions using JDBC
- `Kotlin Coroutines`: a library for asynchronous programming in Kotlin
- `Jsoup`: a library for parsing and manipulating HTML and XML documents
- `Koin`: a dependency injection framework for Kotlin

## API Reference

The API for this project exposes the following endpoints:

### `POST /login`

Logs in a user with the given username and password.

#### Parameters

- `username` (required): The username of the user.
- `password` (required): The password of the user.

#### Response

A JSON object with the following fields:

- `token`: Your JWT token (used for /search end-point)

```bash
curl -X POST \
  'https://any-audio-finder.iran.liara.run/login' \
  -H 'Content-Type: application/json' \
  -d '{
  "username": "<USERNAME>",
  "password": "<PASSWORD>"
}'
```

```json
{
   "token": "YOUR_JWT_TOKEN"
}
```

### `GET /search`

Searches for audio files with the given query.

#### Parameters

- `query` (required): The search query.

#### Headers

- `Authorization` (required): A JSON Web Token (JWT) for authentication.

#### Response

A JSON object with the following fields:

- `message`: An array of objects representing the search results, with the following fields:
   - `id`: The ID of the audio file.
   - `page_url`: The URL of the page where the audio file was found.
   - `download_url`: The URL of the audio file.

```bash
curl -X GET \
'https://any-audio-finder.iran.liara.run/search?query=<QUERY>' \
-H 'Authorization: Bearer <YOUR_JWT_TOKEN>'
```

```json
{
   "message": [
      {
         "id": 12,
         "page_url": "https://music-fa.com/download-song/3955/",
         "download_url": "https://any-audio-finder.iran.liara.run/audio/2b2edda4c1709c20ee7a69f6c9322440"
      },
      {
         "id": 35,
         "page_url": "https://mahanmusic.net/mohammadreza-shajryan-morghe-sahar/",
         "download_url": "https://any-audio-finder.iran.liara.run/audio/3abc37a8984d8e5c2113227b5d079aef"
      }
   ]
}
```

## Environment Variables

The following environment variables are used by this app:

- `DEBUG_MODE`: : A boolean value indicating whether the app is running in debug mode (true) or release mode (false).
  In debug mode, the app uses an in-memory H2 database for storage. In release mode,
  the app connects to a SQL database such as PostgreSQL.

- `API_URL`: The URL of the domain where the app is running,
  for example https://any-audio-finder.iran.liara.run/.

- `POSTGRES_DB_USER`, `POSTGRES_DB_PASSWORD`, `POSTGRES_DB_HOST`, `POSTGRES_DB_PORT`, `POSTGRES_DB_NAME`:
  These variables are used to connect to a PostgreSQL database. They contain the username, password, hostname, port, and
  database name, respectively.

## License

This project is licensed under the [MIT License](LICENSE.md).
This means that you are free to use and modify the code for any purpose,
as long as you include the original copyright and license notice in any copies of the software.

## Contributing

We welcome contributions to this project! If you have an idea for a feature or a bug fix,
please open an issue or submit a pull request.

## Credits

[razavioo]("https://github.com/razavioo"): creator and maintainer of the project.

## Acknowledgments

The developers and maintainers of the technologies used in this project
(Ktor, Exposed, Kotlin Coroutines, Jsoup, and Koin) for their hard work
and dedication to creating and maintaining high-quality tools.

## Contact

If you have any questions or suggestions about this project,
please feel free to contact me at razavioo@gmail.com.
I'm always happy to hear from you!
