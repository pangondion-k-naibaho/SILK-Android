package com.silk.client.ui.activity.dashboard

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
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

        setUpView()
    }

    private fun setUpView(){
        binding.dashboardToolbar.navigationIcon = ContextCompat.getDrawable(this@DashboardActivity, R.drawable.ic_burger_menu)

        val toggle = ActionBarDrawerToggle(
            this@DashboardActivity, binding.drawerDashboard, binding.dashboardToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        binding.drawerDashboard.addDrawerListener(toggle)
        toggle.syncState()

        binding.dashboardToolbar.setNavigationOnClickListener {
            if (binding.drawerDashboard.isDrawerOpen(GravityCompat.START)) {
                binding.drawerDashboard.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerDashboard.openDrawer(GravityCompat.START)
            }
        }
    }
}