package com.example.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.SignupRequest
import com.example.myapplication.mvvm.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SignupViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            if(binding.edtFirstName.text!!.isEmpty() || binding.edtFirstName.text!!.isBlank()){
                Toast.makeText(this@SignupActivity, "First name is mandatory", Toast.LENGTH_LONG).show()
            }else if(binding.edtLastName.text!!.isEmpty() || binding.edtLastName.text!!.isBlank()){
                Toast.makeText(this@SignupActivity, "Last name is mandatory", Toast.LENGTH_LONG).show()
            }else if(binding.edtAge.text!!.isEmpty() || binding.edtAge.text!!.isBlank()){
                Toast.makeText(this@SignupActivity, "Age is mandatory", Toast.LENGTH_LONG).show()
            }else{
                callSignup(binding.edtFirstName.text.toString(), binding.edtLastName.text.toString(), binding.edtAge.text.toString())
            }
        }

        viewModel.signupResult.observe(this) { result ->
            if (!viewModel.isHandled()) {
                result.onSuccess {
                    Toast.makeText(this, "Signup successful for the user: ${it.fName}", Toast.LENGTH_SHORT).show()
                    resetInputField()
                }
                result.onFailure {
                    Toast.makeText(this, "Signup failed: ${it.message}", Toast.LENGTH_SHORT).show()
                }
                viewModel.markHandled()
            }
        }

    }

    private fun resetInputField() {
        binding.edtFirstName.setText("")
        binding.edtLastName.setText("")
        binding.edtAge.setText("")
    }

    private fun callSignup(fName: String, lName: String, age: String) {
        val request = SignupRequest(
            fName = fName,
            lName = lName,
            age = age.toInt()
        )

        viewModel.signup(request)
    }


}