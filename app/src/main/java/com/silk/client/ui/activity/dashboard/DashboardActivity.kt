package com.silk.client.ui.activity.dashboard

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.silk.client.R
import com.silk.client.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private val TAG = DashboardActivity::class.java.simpleName
    private lateinit var binding: ActivityDashboardBinding

    companion object{
        fun newIntent(context: Context): Intent = Intent(context, DashboardActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}