# AlmosaferReportTask

- [Screenshots](#Screenshots)
- [Implementation Details](#implementation-details)
- [Unit Testing](#unit-testing)
- [Running the Example](#run-sample)
- [Requirements](#requirements)
- [Dependencies](#Dependencies)

## Screenshots

| Idle  |  Listing | Details |
|---|---|---|
| <img src="screenshots/screenshot_1.jpg" width="200">  | <img src="screenshots/screenshot_3.jpg" width="200">|<img src="screenshots/screenshot_2.jpg" width="200">

## Implementation Details
  ### Architecture
 I introduced clean architecture as follows:
   * Domain
   * Data
   * Presentation

![image](screenshots/architecture.png)

### Tech stack
 * `kotlin coroutines` for concurrency 
 * `Flow` for UI Observation
 * `Hilt` Dependency injection


 ### Folder structure
 * DataAcess
    
   This layer is responsible to collect data from sources in our case we have a single remote data source `RemoteMovieDataSource` to call API requests and `MovieRepositoryImpl` to collect data from `RemoteMovieDataSource` and re-map it to the `domain` layer using `DTO` mapper `MovieListResponseToMovieListMapper`
    ``` 
        |____datasource
        | |____RemoteMovieDataSource.kt
        |____MovieRepositoryImpl.kt
        |____di
        | |____MovieFeatureModule.kt
        | |____RemoteMovieDataSourceModule.kt
        |____response
        | |____MovieResponse.kt
        |____mapper
        | |____MovieListResponseToMovieListMapper.kt
    ```
* Domain

    This layer is responsible to get data from `MovieRepository` and apply the business logic if needed and re-map data to be ready to the `UI` layer.
    ``` 
        |____entity
        | |____Movie.kt
        |____MovieRepository.kt
        |____usecase
        | |____GetMovieListUseCase.kt
    ```
* UI

   This layer is just a presentation including `UIComponents`, `screens` and `ViewModel` for communication between domain layer and UI

    ```
        |____MainActivity.kt
        |____MovieType.kt
        |____movielist
        | |____MovieListAdapter.kt
        | |____MovieListFragment.kt
        | |____MovieViewModel.kt
        | |____PaginationScrollListener.kt
        |____moviedetails
        | |____MovieDetailsFragment.kt
    ```
## Unit Testing
  there is 3 test cases to test the loading ,empty and error states
  use the below command to run the test cases:
  ```bash
   ./gradlew  :features:movie:presentation:testDevelopmentDebugUnitTest --tests com.task.almosaferassignment.feature.movie.presentation.MovieListViewModelTest
  ```


## Dependencies
- [Flipper](https://fbflipper.com/) - trace API call 
- [Coil](https://github.com/coil-kt/coil) - loading image 
- [Mockk](https://github.com/mockk/mockk) - unit testing
- [Shimmer](https://github.com/facebook/shimmer-android) - loading state for API call 