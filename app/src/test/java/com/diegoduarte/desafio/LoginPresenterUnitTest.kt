package com.diegoduarte.desafio

import com.diegoduarte.desafio.data.model.LoginResponse
import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.data.source.Repository
import com.diegoduarte.desafio.mvp.login.LoginContract
import com.diegoduarte.desafio.mvp.login.LoginPresenter
import com.diegoduarte.desafio.utils.Errors
import com.diegoduarte.desafio.utils.schedulers.TestSchedulerProvider
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import retrofit2.Response



class LoginPresenterUnitTest {


    private val viewMock: LoginContract.View = mock( LoginContract.View::class.java)
    private val repository: Repository = mock( Repository::class.java)
    private lateinit var objectUnderTest: LoginContract.Presenter


    @Before
    @Throws(Exception::class)
    fun setUp() {
        objectUnderTest =  LoginPresenter(repository, viewMock, TestSchedulerProvider())
    }
    @Test
    fun shouldLogin(){
        //given
        val email = ""
        val password = ""
        val token = Token("123456", "321", "98745")
        val body = LoginResponse(true, ArrayList())
        val a = MockResponse()
            .addHeader("access-token", token.accessToken )
            .addHeader("uid", token.uid)
            .addHeader("client", token.client)
            .setResponseCode(200)
        given(repository.login(email, password))
            .willReturn(Observable.just(Response.success(body, a.headers)))

        //when
        objectUnderTest.login(email, password)

        //then
        then(viewMock).should(times(1)).showLoadingDialog()
        then(viewMock).should(times(1)).attemptLogin(token)
        then(viewMock).shouldHaveNoMoreInteractions()
    }


    @Test
    fun shouldNotLogin(){
        //given
        val email = ""
        val password = ""
        val response = "".toResponseBody()
        given(repository.login(email, password))
            .willReturn(Observable.just(Response.error(401, response)))

        //when
        objectUnderTest.login(email, password)

        //then
        then(viewMock).should(times(1)).showLoadingDialog()
        then(viewMock).should(times(1)).showError(Errors.LOGIN_ERROR)
        then(viewMock).should(times(1)).hideLoadingDialog()
        then(viewMock).shouldHaveNoMoreInteractions()
    }


}