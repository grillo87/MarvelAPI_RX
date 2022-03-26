<b>Marvel API</b>

<img src="https://github.com/grillo87/MarvelAPI-Android/blob/master/images/ic_app.png" alt="" width="240" height="240">

<body>
<b>Marvel API developed by José Grillo for Android</b></br></br>

This is a an app developed using Kotlin for Android.It uses Model View ViewModel (MVVM) as architectural pattern.</br>
The project is structured in 4 modules:</br>
- <b>App:</b> Module in charge of view displaying elements (Activities and ViewModels), it also has it owns entities, ending with the sufix VO, and mapper to transform the data from underneath layer (Domain)</br>
- <b>DI:</b> This modules is used for the app layer to initialize the dependencies for the Data layer, but it encapsulates it, so the classes and element for Data Layer are not accesible for classes on app layer.</br>
- <b>Domain:</b> Module in charge of bussiness logic, this one operates with the differents UseCases. It also contains the entities that hold the logic on the project, and the definition for the Repository</br>
- <b>Data:</b> Module in charge of data sources access (Locally and remotly). It follow the pattern of Datasource, making transaprent for Repositories classes the origin of the data. It has one table to store the id from the character that user add/remove pressing on Spiderman Button on detail view. It also have Paging3 Google's Library, to paginate the results from the Marvel API in groups of 20.<b>Important, for the app be able to comunicate with Marvel API, you must update inside the gradle file on Data module the values of MARVEL_PRIVATE_API_KEY and MARVEL_PUBLIC_API_KEY values as described on Marvel documentation</b></br></br>

For the web service area, it was integrated with <a href="https://developer.marvel.com/docs">Marvel API</a>, this API is consumed with Retrofit2 and using RXAndroid.</br>

Also the app includes dependency injections being used Koin for that part.</br>

The app implements pagination with the implementation of recomended Google Library Paging3</br>

The project includes Unit Testing for mappers, usecase, repository and UI</br>

The information displayed of the characters comes from the mentionated API.</br>

The app uses the following libraries:</br></br>

- <a href="https://insert-koin.io/">Koin</a></br>
- <a href="https://github.com/square/retrofit">Retrofit2</a></br>
- <a href="https://github.com/ihsanbal/LoggingInterceptor">LoggingInterceptor - Interceptor for OkHttp3 with pretty logger</a></br>
- <a href="https://github.com/bumptech/glide">Glide</a></br>
- <a href="https://github.com/androidx-releases/Room">Room</a></br>
- <a href="https://developer.android.com/topic/libraries/architecture/paging/v3-overview?hl=es-419">Paging3</a></br>
- <a href="https://developer.android.com/guide/navigation/navigation-getting-started">Navigation Component</a></br>
- <a href="https://github.com/ReactiveX/RxAndroid">RX Android</a></br>


<b>Screenshots</b></br></br>
<table>
<tr>
<th>Home</th>
<th>Detail Character (No favorite selected)</th>
<th>Detail Character (Favorite selected)</th>
<th>Error message while scrolling</th>
<th>Error message on Main and Detail at open</th>
</tr>
<tr>
<td>
<img src="https://github.com/grillo87/MarvelAPI_RX/blob/master/images/1.png">
</td>
<td>
<img src="https://github.com/grillo87/MarvelAPI_RX/blob/master/images/2.png">
</td>
<td>
<img src="https://github.com/grillo87/MarvelAPI_RX/blob/master/images/3.png">
</td>
<td>
<img src="https://github.com/grillo87/MarvelAPI_RX/blob/master/images/4.png">
</td>
<td>
<img src="https://github.com/grillo87/MarvelAPI_RX/blob/master/images/5.png">
</td>
</tr>
</table>


</body>
</html>