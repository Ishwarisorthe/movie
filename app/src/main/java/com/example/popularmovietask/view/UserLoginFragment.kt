package com.example.popularmovietask.view

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.popularmovietask.R
import com.example.popularmovietask.databinding.FragmentUserLoginBinding
import com.example.popularmovietask.ktx.*

import com.example.popularmovietask.usecase.UserValidationUseCase
import com.example.popularmovietask.utils.BaseFragment
import com.example.popularmovietask.viewmodel.PopularMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserLoginFragment : BaseFragment<FragmentUserLoginBinding, PopularMovieViewModel>() {
    private lateinit var enterEmailUseCase: UserValidationUseCase

    override fun initViewModel(): PopularMovieViewModel =
        ViewModelProvider(this).get(PopularMovieViewModel::class.java)

    override fun initBinding(): FragmentUserLoginBinding =
        FragmentUserLoginBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterEmailUseCase = viewModel.getEmailUseCase()
    }

    override fun initViews() {
        binding.apply {
            //Email validation
            userEmailValidation()

            //Password validation
            userPasswordValidation()

            buttonSubmit.setOnClickListener {
                if (enterPasswordEdittext.text.isNotEmpty() && enterEmailEdittext.text.isNotEmpty()) {
                    navigateUser(R.id.popularMovieListFragment)
                }
            }

        }
    }

    private fun userPasswordValidation() {
        binding.apply {
            enterPasswordEdittext.doAfterTextChanged {
                if (it.isNotEmpty()) {
                    enterEmailUseCase.validatePassword(it)
                }
            }
            enterEmailUseCase.passwordErrorLiveData.observe(viewLifecycleOwner, Observer {
                if (it) {
                    passwordErrorMessageTextview.hide()
                } else {
                    passwordErrorMessageTextview.show()
                }
                enableSubmitButton()
            })
        }
    }

    private fun userEmailValidation() {
        binding.apply {
            enterEmailEdittext.showKeyboard()

            enterEmailEdittext.doAfterTextChanged {
                if (it.isNotEmpty())
                    enterEmailUseCase.validateEmail(it)
            }
            enterEmailUseCase.emailErrorLiveData.observe(viewLifecycleOwner, Observer {
                if (it) {
                    emailErrorTextview.show()
                } else {
                    emailErrorTextview.hide()
                }
                enableSubmitButton()
            })
        }
    }

    private fun enableSubmitButton() {
        binding.apply {
            if (!emailErrorTextview.isVisible && emailErrorTextview.text.isNotEmpty() &&
                !passwordErrorMessageTextview.isVisible && enterPasswordEdittext.text.isNotEmpty()
            ) {
                buttonSubmit.enable()
            } else
                buttonSubmit.disable()
        }

    }
}