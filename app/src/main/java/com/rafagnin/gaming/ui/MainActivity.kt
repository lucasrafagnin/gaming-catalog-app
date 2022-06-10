package com.rafagnin.gaming.ui

import android.app.Activity
import android.os.Bundle
import com.rafagnin.gaming.adapter.DemoAdapter
import com.rafagnin.gaming.databinding.MainBinding
import com.rafagnin.gaming.model.Demo

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val demos = ArrayList<Demo>()
        for (i in 0..100) demos.add(Demo(title = "Demo $i", url = "https://picsum.photos/id/$i/200/300"))
        binding.list.adapter = DemoAdapter(demos)
    }
}