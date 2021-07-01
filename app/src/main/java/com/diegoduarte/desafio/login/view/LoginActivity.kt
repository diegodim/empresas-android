package com.diegoduarte.desafio.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import com.diegoduarte.desafio.R
import com.diegoduarte.desafio.base.BaseActivity
import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.home.view.HomeActivity
import com.diegoduarte.desafio.login.LoginContract
import com.diegoduarte.desafio.utils.Errors
import com.google.android.material.textfield.TextInputLayout
import javax.inject.Inject

class LoginActivity : BaseActivity(),
    LoginContract.View {
    // Inject the object of presenter
    @Inject
    lateinit var presenter: LoginContract.Presenter

    private lateinit var buttonEnter: Button
    private lateinit var editTextEmail: TextInputLayout
    private lateinit var editTextPassword: TextInputLayout
    private lateinit var loadingLayout: View

    // Instance all view objects
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadingLayout = findViewById(R.id.loading_layout)
        editTextEmail = findViewById(R.id.login_et_email)
        editTextEmail.editText?.addTextChangedListener {
            editTextEmail.error = ""
            editTextPassword.error = ""
        }
        editTextPassword = findViewById(R.id.login_et_password)
        editTextPassword.editText?.addTextChangedListener {
            editTextEmail.error = ""
            editTextPassword.error = ""
        }
        buttonEnter = findViewById(R.id.login_bt_enter)
        buttonEnter.setOnClickListener{
            editTextEmail.error = ""
            editTextPassword.error = ""
            presenter.login(editTextEmail.editText?.text.toString(),
                editTextPassword.editText?.text.toString())
        }

    }

    // Set the content id for on BaseActivity
    override fun getContent(): Int = R.layout.activity_login

    // Set the presenter on hte BaseActivity
    override fun getPresenter(): BasePresenter = presenter as BasePresenter

    // Show the Loading Dialog
    override fun showLoadingDialog() {
        buttonEnter.isEnabled = false
        loadingLayout.bringToFront()
        loadingLayout.visibility = View.VISIBLE
        editTextEmail.isEnabled = false
        editTextPassword.isEnabled = false
    }

    // Hide the Loading Dialog
    override fun hideLoadingDialog() {
        buttonEnter.isEnabled = true
        loadingLayout.visibility = View.GONE
        editTextEmail.isEnabled = true
        editTextPassword.isEnabled = true
    }

    // Show the error
    override fun showError(error: Errors) {
        editTextEmail.error = " "
        when(error){
            Errors.INTERNET_ERROR->editTextPassword.error = getString(R.string.error_message_internet)
            Errors.LOGIN_ERROR->editTextPassword.error = getString(R.string.error_message_login)
        }
    }

    // Call the HomeActivity
    override fun attemptLogin(token: Token) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(HomeActivity.INTENT_EXTRA_TOKEN, token)
        startActivity(intent)
    }
}