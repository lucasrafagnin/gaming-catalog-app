package com.rafagnin.gaming.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rafagnin.gaming.adapter.DemoAdapter
import com.rafagnin.gaming.databinding.MainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    private val adapter = DemoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.list.adapter = adapter

        viewModel.getGames()
        viewModel.items.observe(this) {
            it?.let {
                adapter.update(it)
            }
        }
    }
}