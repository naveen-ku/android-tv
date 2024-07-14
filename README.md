# android-tv

The Stage project is an Android TV application built using Kotlin. It includes key features such as user login, displaying TV/movies series, and video playback.

## Features
- **Login**: Secure user authentication.
- **Show TV/Movie Series**: Fetch and display a list of TV series and movies from an API.
- **Play Video**: Stream video content directly within the app.

### Video Demo 


https://github.com/user-attachments/assets/b7e159f4-c936-41b4-8593-9c7ec21efc8e



 [Drive Link](https://drive.google.com/file/d/1J2wgWDy0EPsUtkqHeQ4jKw8QwtMU8Q9L/view?usp=drive_link)

## Steps to Run the Project
1. **Download Android Studio**: Install Android Studio from the [official Android Developer website](https://developer.android.com/studio).
2. **Download SDK/NDK Tools**: Ensure that you have the necessary SDK and NDK tools installed in Android Studio.
3. **Get `gradle.properties` File**:
   - Request the `gradle.properties` file that contains the TMDB API KEY.
   - Alternatively, create an API key on [TMDB](https://www.themoviedb.org/) and add it to the `gradle.properties` file.

## Tasks Achieved

###  [x] Login
- **Objective**: Implement secure user authentication to ensure that only authorized users can access the app's features.
- **Implementation**: Used Firebase Authentication to handle user sign-in and sign-out processes securely. This included handling phone number verification using Firebase's Phone Auth API.

### 2. Fetch TV/Movie Data
- **Objective**: Retrieve and display a list of TV series and movies from an external API (The Movie Database - TMDB).
- **Implementation**: 
  - **API Integration**: Used Retrofit library to make HTTP requests to TMDB API endpoints to fetch popular TV series and movies.
  - **Data Parsing**: Parsed JSON responses received from TMDB API using Gson library to extract relevant information such as titles, posters, and details.

### 3. Play Video
- **Objective**: Enable users to watch video content directly within the application.
- **Implementation**: 
  - Integrated ExoPlayer library to stream videos from online sources.
  - Managed video playback controls (play, pause, seek) and provided a seamless user experience for watching trailers and other video content related to movies and TV series.

### 4. Follow MVVM Architecture
- **Objective**: Structure the Android application using the Model-View-ViewModel (MVVM) architectural pattern.
- **Implementation**: 
  - **Model Layer**: Defined data models such as `User`, `ItemThumbnail`, `MoviesList`, and `TvSeriesList` to represent entities retrieved from the API and stored in the local database.
  - **View Layer**: Activities and Fragments were used to create the user interface and handle user interactions.
  - **ViewModel Layer**: Utilized ViewModel classes to manage UI-related data in a lifecycle-conscious way, separate from the UI controllers (Activities/Fragments).

### 5. Store Data in DB
- **Objective**: Implement local data storage to provide offline support and improve app performance.
- **Implementation**: 
  - Utilized Room Persistence Library to create a local SQLite database.
  - Stored fetched TV series and movie data locally to allow users to access content offline.
  - Implemented DAO (Data Access Object) classes to perform CRUD operations (Create, Read, Update, Delete) on local database entities.

### 6. Offline Support
- **Objective**: Ensure app functionality even when the device is not connected to the internet.
- **Implementation**: 
  - Cached API responses and stored data locally using Room database.
  - Implemented logic to check for network connectivity and fall back to locally stored data when offline.
  - Utilized Glide library for efficient image caching to enhance app performance and reduce data usage.

### 7. Cache Using Glide
- **Objective**: Optimize image loading and caching to enhance app performance and user experience.
- **Implementation**: 
  - Integrated Glide library for image loading and caching.
  - Cached images fetched from TMDB API to display movie and TV series posters efficiently within RecyclerViews and detail screens.
  - Configured Glide to cache images both in memory and on disk to minimize network requests and improve loading times.

These tasks collectively contribute to building a robust Android TV application that offers a seamless experience for users while ensuring data security, offline accessibility, and efficient content presentation. Each task leverages modern Android development practices and libraries to meet the project's requirements effectively.


## API Endpoints
1. **Popular TV Series List**:
   - Endpoint: `https://api.themoviedb.org/3/tv/popular?language=en-US&page=1`
   - Parameters: `language`, `page`
   - Response: 20 TV series per page

2. **Popular Movie List**:
   - Endpoint: `https://api.themoviedb.org/3/movie/popular?language=en-US&page=1`
   - Parameters: `language`, `page`
   - Response: 20 movies per page

3. **Movie Details**:
   - Endpoint: `https://api.themoviedb.org/3/movie/{movie_id}?language=en-US`
   - Parameters: `movie_id`, `language`
   - Response: Details of a specific movie

4. **TV Series Details**:
   - Endpoint: `https://api.themoviedb.org/3/tv/{series_id}?language=en-US`
   - Parameters: `series_id`, `language`
   - Response: Details of a specific TV series

5. **Movies Trending**:
   - Endpoint: `https://api.themoviedb.org/3/trending/movie/day?language=en-US`
   - Parameters: `language`
   - Response: List of trending movies based on the day

6. **TV Series Trending**:
   - Endpoint: `https://api.themoviedb.org/3/trending/tv/day?language=en-US`
   - Parameters: `language`
   - Response: List of trending TV series based on the day

For any further information or assistance, please refer contact me via mail
