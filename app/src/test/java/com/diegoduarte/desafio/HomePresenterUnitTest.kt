package com.diegoduarte.desafio

import com.diegoduarte.desafio.data.model.LoginResponse
import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.data.source.Repository
import com.diegoduarte.desafio.home.HomeContract
import com.diegoduarte.desafio.home.HomePresenter
import com.diegoduarte.desafio.login.LoginContract
import com.diegoduarte.desafio.login.LoginPresenter
import com.diegoduarte.desafio.utils.schedulers.TestSchedulerProvider
import io.reactivex.rxjava3.core.Observable
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito
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
    fun shouldCreate(){
        /*//given
        val email = ""
        val password = ""
        val token = Token("123456", "321", "98745")
        val body = LoginResponse(true, ArrayList())
        val a = MockResponse()
            .addHeader("access-token", token.access_token )
            .addHeader("uid", token.uid)
            .addHeader("client", token.client)
            .setResponseCode(200)
        BDDMockito.given(repository.login(email, password))
            .willReturn(Observable.just(Response.success(body, a.headers)))

        //when
        objectUnderTest.login(email, password)

        //then
        BDDMockito.then(viewMock).should(Mockito.times(1)).showLoadingDialog()
        BDDMockito.then(viewMock).should(Mockito.times(1)).attemptLogin(token)
        BDDMockito.then(viewMock).should(Mockito.times(1)).hideLoadingDialog()
        BDDMockito.then(viewMock).shouldHaveNoMoreInteractions()*/
    }

}