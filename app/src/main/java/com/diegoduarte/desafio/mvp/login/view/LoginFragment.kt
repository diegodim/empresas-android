package com.diegoduarte.desafio.mvp.login.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.diegoduarte.desafio.R
import com.diegoduarte.desafio.base.BaseFragment
import com.diegoduarte.desafio.base.BasePresenter
import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.databinding.FragmentLoginBinding
import com.diegoduarte.desafio.mvp.login.LoginContract
import com.diegoduarte.desafio.utils.Errors
import javax.inject.Inject

class LoginFragment : BaseFragment<FragmentLoginBinding>(),
    LoginContract.View {
    // Inject the object of presenter
    @Inject
    lateinit var presenter: LoginContract.Presenter

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    // Instance all view objects
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login, container, false)

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

        return binding.root
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
        requireView().findNavController()
            .navigate(LoginFragmentDirections
                .actionLoginFragmentToHomeFragment(token))

    }


    override var dataBiding: FragmentLoginBinding?
        get() = binding
        set(value) {
            _binding = value
        }


}