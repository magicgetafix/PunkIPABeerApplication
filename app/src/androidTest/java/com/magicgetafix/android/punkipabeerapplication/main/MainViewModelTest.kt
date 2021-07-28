package com.magicgetafix.android.punkipabeerapplication.main

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.example.punkbeerapplication.database.BeerDao
import com.magicgetafix.android.punkipabeerapplication.AndroidTestDataFactory
import com.magicgetafix.android.punkipabeerapplication.api.BeerApi
import com.magicgetafix.android.punkipabeerapplication.api.response.Beer
import com.magicgetafix.android.punkipabeerapplication.database.BeerDatabase
import com.magicgetafix.android.punkipabeerapplication.services.*
import com.moneypenny.telephoneanswering.schedulers.ISchedulers
import com.moneypenny.telephoneanswering.schedulers.TestSchedulers
import io.reactivex.rxjava3.core.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.whenever
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class MainViewModelTest {

    lateinit var db: BeerDatabase
    lateinit var mainViewModel: MainViewModel
    lateinit var beerDao: BeerDao

    lateinit var beerRepository: IBeerRepository
    var apiFactory: IApiFactory = mock(IApiFactory::class.java)
    var databaseProvider: IDatabaseProvider = mock(IDatabaseProvider::class.java)
    var beerApi: BeerApi = mock(BeerApi::class.java)


    var schedulers: ISchedulers = TestSchedulers()

    //rule to make Android Components like LiveData execute instantly on main thread
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, BeerDatabase::class.java)
            .allowMainThreadQueries() //only for testing
            .build()
        beerDao = db.beerDao()
        whenever(databaseProvider.getDatabase()).thenReturn(db)

        whenever(apiFactory.getBeerApi()).thenReturn(beerApi)

        var listOfBeers: ArrayList<Beer> = arrayListOf()
        listOfBeers.add(AndroidTestDataFactory.getPaleAle())
        listOfBeers.add(AndroidTestDataFactory.getBelgianBeer())
        listOfBeers.add(AndroidTestDataFactory.getGermanImperialBeer())
        listOfBeers.add(AndroidTestDataFactory.getJapaneseGermanBeer())
        listOfBeers.add(AndroidTestDataFactory.getBeerBlankName())
        listOfBeers.add(AndroidTestDataFactory.getBeerNullName())
        listOfBeers.add(AndroidTestDataFactory.getBeerEmptyDescription())
        listOfBeers.add(AndroidTestDataFactory.getBeerLongDescription())
        listOfBeers.add(AndroidTestDataFactory.getBeerNullDescription())
        val listOfList: ArrayList<ArrayList<Beer>> = arrayListOf()
        listOfList.add(listOfBeers)
        whenever(beerApi.getAllBeers()).thenReturn(Observable.fromIterable(listOfList))
        beerRepository = BeerRepository(databaseProvider, apiFactory, schedulers)

        mainViewModel = MainViewModel(context, beerRepository, schedulers)

    }

    @Test
    fun getGermanBeers(){
        mainViewModel.getGermanBeersLiveData()
        assertEquals(2, mainViewModel.getGermanBeersLiveData().value?.size)
    }

    @Test
    fun getStrongBeers(){
        mainViewModel.getStrongBeersLiveData()
        assertEquals(4, mainViewModel.getStrongBeersLiveData().value?.size)
    }

    @Test
    fun checkOrder(){
        val lastBeer = mainViewModel.getStrongBeersLiveData().value?.last()
        assertEquals("Berliner Weisse With Yuzu - B-Sides", lastBeer?.name)
        val firstBeer = mainViewModel.getStrongBeersLiveData().value?.first()
        assertEquals("Trashy Blonde", firstBeer?.name)
    }

    @Test
    fun getEuropeanBeers(){
        mainViewModel.getEuropeanBeersLiveData()
        assertEquals(3, mainViewModel.getEuropeanBeersLiveData().value?.size)
    }

    @Test
    fun getBelgianBeers(){
        mainViewModel.getBelgianBeersLiveData()
        assertEquals(1, mainViewModel.getBelgianBeersLiveData().value?.size)
    }

    /**
     * This test should return all the beers which passed validation and were stored in the database
     */
    @Test
    fun getAllBeers(){
        mainViewModel.getAllBeersLiveData()
        assertEquals(4, mainViewModel.getAllBeersLiveData().value?.size)
    }

    @Test
    fun findBeer(){
        val beer = mainViewModel.getBeerFromList(4)
        assertEquals("Trashy Blonde", beer?.name)
        assertEquals("You Know You Shouldn't.", beer?.tagline)
    }

    @Test
    fun failToFindIncompleteBeer(){
        val beer = mainViewModel.getBeerFromList(9)
        assertNull(beer)
    }

    @Test
    fun failToFindBeer(){
        val beer = mainViewModel.getBeerFromList(38)
        assertNull(beer)
    }

    @Test
    fun failToFindBeerAgain(){
        val beer = mainViewModel.getBeerFromList(0)
        assertNull(beer)
    }

    @Test
    fun failToFindBeerNegative(){
        val beer = mainViewModel.getBeerFromList(-1)
        assertNull(beer)
    }


    @After
    fun tearDown() {
        db.close()
    }

}