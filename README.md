# PunkIPABeerApplication

Sample App using data from the Punk IPA api at https://punkapi.com/documentation/v2

App follows an MVVM design pattern with one Main Activity and a ViewModel which is scoped to it, which provides data 
for fragments, adapters and custom views. All views are found in the ui package.

Retrofit is used to parse data from the api, and the BeerRepository class provides a layer between the Api, Room database and views.
Data is transmitted from the Repository to the View Layer using a combination of RxKotlin and LiveData. Dependencies are injected into the ViewModel using Hilt.

Tests integrating the Database, Api and ViewModel can be found and run from the AndroidTest package.

App can be run on any Android phone from Android 7.0 (Nougat) onwards.

Improvements I would make given more time:

I would either replace the Room database with Realm, as it is easier to watch for changes in the database, or work out why the Rx Flowable Wrapper isn't observing and reacting to changes in the Room Database (This is something I have implemented in several projects before without any problems, although usually using RxJava or RxJava2)

I would implement more extensive and exhaustive test cases, and also add some UI tests.




