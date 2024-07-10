# android-tv

### Steps to run
- Request for gradle.properties file (Stores the TMDB API KEY)


### API Endpoints
- Popular TV Series List: https://api.themoviedb.org/3/tv/popular?language=en-US&page=1 (language, page number) -> 20 series per page
- Popular Movie List: https://api.themoviedb.org/3/movie/popular?language=en-US&page=1 (language, page number) -> 20 movies per page
- Movie Details: https://api.themoviedb.org/3/movie/movie_id?language=en-US (Movie Id)
- TV Series Details: https://api.themoviedb.org/3/tv/series_id?language=en-US (Series ID)
- Movies Trending: https://api.themoviedb.org/3/trending/movie/day?language=en-US (By Day basis) -> All movies result based on trending
- TV Series Trending: https://api.themoviedb.org/3/trending/tv/day?language=en-US (By Day basis) -> All tv series result based on trending