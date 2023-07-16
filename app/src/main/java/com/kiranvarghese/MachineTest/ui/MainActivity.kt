package com.kiranvarghese.MachineTest.ui

import MyApi
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiranvarghese.MachineTest.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var passengersViewModel: PassengersViewModel
    lateinit var passengersAdapter: PassengersAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupViewModel()
        setupList()
        setupView()

    }

    private fun setupViewModel() {
        val factory = PassengersViewModelFactory(MyApi())
        passengersViewModel = ViewModelProvider(this, factory).get(PassengersViewModel::class.java)
    }

    private fun setupList() {
        passengersAdapter = PassengersAdapter(PassengersAdapter.OnClickListener{ kirr ->

            intent = Intent(this, DetailedView::class.java)
            intent.putExtra("firstnameandlastname", kirr.firstName+ " " + kirr.lastName)
            intent.putExtra("domain", kirr.domain)
            intent.putExtra("birthDate", kirr.birthDate)
            intent.putExtra("bloodGroup", kirr.bloodGroup)
            intent.putExtra("ein", kirr.ein)
            intent.putExtra("email", kirr.email)
            intent.putExtra("eyeColor", kirr.eyeColor)
            intent.putExtra("maidenName", kirr.maidenName)
            intent.putExtra("phone", kirr.phone)
            intent.putExtra("ssn", kirr.ssn)
            intent.putExtra("height", kirr.height)
            intent.putExtra("image", kirr.image)

            startActivity(intent)
           })
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = passengersAdapter.withLoadStateHeaderAndFooter(
                header = PassengersLoadStateAdapter { passengersAdapter.retry() },
                footer = PassengersLoadStateAdapter { passengersAdapter.retry() }
            )
            setHasFixedSize(true)
        }


    }

    private fun setupView() {
        lifecycleScope.launch {
            passengersViewModel.passengers.collectLatest { pagedData ->
                passengersAdapter.submitData(pagedData)
            }
        }
    }

}