package com.diegoduarte.desafio

import com.diegoduarte.desafio.data.model.Enterprise
import com.diegoduarte.desafio.data.model.Enterprises
import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.data.source.Repository
import com.diegoduarte.desafio.mvp.home.HomeContract
import com.diegoduarte.desafio.mvp.home.HomePresenter
import com.diegoduarte.desafio.utils.schedulers.TestSchedulerProvider
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mockito
import retrofit2.Response

class HomePresenterUnitTest {

    private val viewMock: HomeContract.View = Mockito.mock(HomeContract.View::class.java)
    private val repository: Repository = Mockito.mock(Repository::class.java)
    private lateinit var objectUnderTest: HomeContract.Presenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        objectUnderTest =  HomePresenter(repository, viewMock, TestSchedulerProvider(), Token())
    }

    @Test
    fun shouldShowSearch(){
        //given
        val name = "name"
        val enterprises = Enterprises(listOf<Enterprise>(Enterprise(), Enterprise()))
        given(repository.getEnterprise(Token(),name))
            .willReturn(Observable.just(Response.success(enterprises)))

        //when
        objectUnderTest.searchByName(name)

        //then
        then(viewMock).should(Mockito.times(1))
            .showEnterprises(enterprises.enterprises)
        then(viewMock).shouldHaveNoMoreInteractions()
    }

    @Test
    fun shouldShowInvalidToken(){
        //given
        val name = "name"
        val response = "".toResponseBody()
        given(repository.getEnterprise(Token(),name))
            .willReturn(Observable.just(Response.error(401, response)))

        //when
        objectUnderTest.searchByName(name)

        //then
        then(viewMock).should(Mockito.times(1))
            .returnToLogin()
        then(viewMock).shouldHaveNoMoreInteractions()
    }

    @Test
    fun shouldShowEmptySearch(){
        //given
        val name = "name"
        val enterprises = Enterprises(ArrayList<Enterprise>())
        given(repository.getEnterprise(Token(),name))
            .willReturn(Observable.just(Response.success(enterprises)))

        //when
        objectUnderTest.searchByName(name)

        //then
        then(viewMock).should(Mockito.times(1))
            .showEmptySearch()
        then(viewMock).shouldHaveNoMoreInteractions()
    }

}