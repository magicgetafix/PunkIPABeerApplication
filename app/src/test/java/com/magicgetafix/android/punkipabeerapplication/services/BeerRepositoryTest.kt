package com.magicgetafix.android.punkipabeerapplication.services

import com.example.punkbeerapplication.database.BeerDao
import com.magicgetafix.android.punkipabeerapplication.TestDataFactory
import com.magicgetafix.android.punkipabeerapplication.api.BeerApi
import com.magicgetafix.android.punkipabeerapplication.api.response.Beer
import com.magicgetafix.android.punkipabeerapplication.database.BeerDatabase
import com.magicgetafix.android.punkipabeerapplication.database.models.BeerDbModel
import com.moneypenny.telephoneanswering.schedulers.ISchedulers
import com.moneypenny.telephoneanswering.schedulers.TestSchedulers
import io.reactivex.Flowable
import io.reactivex.rxjava3.core.Observable
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.internal.matchers.Any
import org.mockito.internal.verification.Times
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class BeerRepositoryTest : TestCase() {


    lateinit var beerRepository: IBeerRepository
    @Mock
    lateinit var apiFactory: IApiFactory
    @Mock
    lateinit var databaseProvider: IDatabaseProvider
    @Mock
    lateinit var beerApi: BeerApi
    @Mock
    lateinit var database: BeerDatabase
    @Mock
    lateinit var beerDao: BeerDao

    var schedulers: ISchedulers = TestSchedulers()

    @Before
    public override fun setUp(){
        beerRepository = spy(BeerRepository(databaseProvider, apiFactory, schedulers))
        whenever(apiFactory.getBeerApi()).thenReturn(beerApi)

        var listOfBeers: ArrayList<Beer> = arrayListOf()
        listOfBeers.add(TestDataFactory.getPaleAle())
        listOfBeers.add(TestDataFactory.getBelgianBeer())
        listOfBeers.add(TestDataFactory.getGermanImperialBeer())
        listOfBeers.add(TestDataFactory.getJapaneseGermanBeer())
        listOfBeers.add(TestDataFactory.getBeerBlankName())
        listOfBeers.add(TestDataFactory.getBeerNullName())
        listOfBeers.add(TestDataFactory.getBeerEmptyDescription())
        listOfBeers.add(TestDataFactory.getBeerLongDescription())
        listOfBeers.add(TestDataFactory.getBeerNullDescription())
        val listOfList: ArrayList<ArrayList<Beer>> = arrayListOf()
        listOfList.add(listOfBeers)
        whenever(beerApi.getAllBeers()).thenReturn(Observable.fromIterable(listOfList))
        whenever(databaseProvider.getDatabase()).thenReturn(database)
        whenever(database.beerDao()).thenReturn(beerDao)
        whenever(beerDao.getAllBeers()).thenReturn(Flowable.just(arrayListOf()))
    }

    @Test
    fun testApiIsCalledOnGetBeers() {
        beerRepository.getBeers()
        verify(beerApi, Times(1)).getAllBeers()
    }

    @Test
    fun testDatabaseIsCalledOnGetBeers() {
        beerRepository.getBeers()
        verify(beerApi, Times(1)).getAllBeers()
    }

    @Test
    fun insertIntoDatabaseIsCalledOnGetBeers() {
        beerRepository.getBeers()
        verify(beerRepository, Times(1)).insertBeersIntoDatabase(Mockito.anyList())
    }
}