package com.silk.client.ui.activity.dashboard

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.silk.client.R
import com.silk.client.data.Constants.PREFERENCES.Companion.APP_PREFERENCES
import com.silk.client.data.Constants.PREFERENCES.Companion.TOKEN_KEY
import com.silk.client.databinding.ActivityDashboardBinding
import com.silk.client.ui.activity.authentication.AuthenticationActivity

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

        setSupportActionBar(binding.dashboardToolbar)


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

        binding.btnLogout.setOnClickListener {
            val appPreferences = this@DashboardActivity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

            val editor = appPreferences.edit()

            editor.remove(TOKEN_KEY)

            if(editor.commit()){
                Toast.makeText(this@DashboardActivity, getString(R.string.toastLogoutSuccess), Toast.LENGTH_SHORT).show()
                startActivity(AuthenticationActivity.newIntent(this@DashboardActivity))
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menuCart -> {
                Toast.makeText(this@DashboardActivity, getString(R.string.toastUnderDev), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menuNotification -> {
                Toast.makeText(this@DashboardActivity, getString(R.string.toastUnderDev), Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}