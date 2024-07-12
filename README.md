# android-tv

Stage project to build android TV app using Kotlin with features:
- Login
- Show TV/Movie series
- Play video


### Video Demo 


https://github.com/user-attachments/assets/b7e159f4-c936-41b4-8593-9c7ec21efc8e



 [Drive Link](https://drive.google.com/file/d/1J2wgWDy0EPsUtkqHeQ4jKw8QwtMU8Q9L/view?usp=drive_link)

### Steps to run
- Download Android Studio
- Download SDK/NDK tools
- Request for gradle.properties file (Stores the TMDB API KEY) to run app directly or simply create API on [TMBD](https://www.themoviedb.org/)

### Tasks Achieved
- [x] Login
- [x] Fetch TV/Movie using API
- [x] Play Video
- [x] Follow MVVM architecture


### API Endpoints
- Popular TV Series List: https://api.themoviedb.org/3/tv/popular?language=en-US&page=1 (language, page number) -> 20 series per page
- Popular Movie List: https://api.themoviedb.org/3/movie/popular?language=en-US&page=1 (language, page number) -> 20 movies per page
- Movie Details: https://api.themoviedb.org/3/movie/movie_id?language=en-US (Movie Id)
- TV Series Details: https://api.themoviedb.org/3/tv/series_id?language=en-US (Series ID)
- Movies Trending: https://api.themoviedb.org/3/trending/movie/day?language=en-US (By Day basis) -> All movies result based on trending
- TV Series Trending: https://api.themoviedb.org/3/trending/tv/day?language=en-US (By Day basis) -> All tv series result based on trending
