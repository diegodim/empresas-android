package com.diegoduarte.desafio.mvp.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.diegoduarte.desafio.R
import com.diegoduarte.desafio.base.BaseActivity
import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.databinding.ActivityLoginBinding
import com.diegoduarte.desafio.mvp.home.view.HomeActivity
import com.diegoduarte.desafio.mvp.login.LoginContract
import com.diegoduarte.desafio.utils.Errors
import javax.inject.Inject

class LoginActivity : BaseActivity(),
    LoginContract.View {
    // Inject the object of presenter
    @Inject
    lateinit var presenter: LoginContract.Presenter

    private lateinit var binding: ActivityLoginBinding

    // Instance all view objects
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)


        binding.editTextEmail.editText?.addTextChangedListener {
            binding.editTextEmail.error = ""
            binding.editTextPassword.error = ""
        }
        binding.editTextPassword.editText?.addTextChangedListener {
            binding.editTextEmail.error = ""
            binding.editTextPassword.error = ""
        }
        binding.buttonEnter.setOnClickListener{
            binding.editTextEmail.error = ""
            binding.editTextPassword.error = ""
            presenter.login(binding.editTextEmail.editText?.text.toString(),
                binding.editTextPassword.editText?.text.toString())
        }

    }


    // Set the presenter on hte BaseActivity
    override fun getPresenter(): BasePresenter = presenter as BasePresenter

    // Show the Loading Dialog
    override fun showLoadingDialog() {
        binding.buttonEnter.isEnabled = false
        binding.layoutLoading.bringToFront()
        binding.layoutLoading.visibility = View.VISIBLE
        binding.editTextEmail.isEnabled = false
        binding.editTextPassword.isEnabled = false
    }

    // Hide the Loading Dialog
    override fun hideLoadingDialog() {
        binding.editTextEmail.isEnabled = true
        binding.editTextPassword.isEnabled = true
        binding.buttonEnter.isEnabled = true
        binding.layoutLoading.visibility = View.GONE
    }

    // Show the error
    override fun showError(error: Errors) {
        binding.editTextEmail.error = " "
        when(error){
            Errors.INTERNET_ERROR->binding.editTextPassword.error = getString(R.string.error_message_internet)
            Errors.LOGIN_ERROR->binding.editTextPassword.error = getString(R.string.error_message_login)
        }
    }

    // Call the HomeActivity
    override fun attemptLogin(token: Token) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(HomeActivity.INTENT_EXTRA_TOKEN, token)
        startActivity(intent)
    }

    override fun onStop() {
        hideLoadingDialog()
        super.onStop()
    }
}