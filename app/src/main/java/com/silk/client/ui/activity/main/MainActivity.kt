package com.silk.client.ui.activity.main

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.silk.client.R
import com.silk.client.data.Constants.PREFERENCES.Companion.APP_PREFERENCES
import com.silk.client.data.Constants.PREFERENCES.Companion.TOKEN_KEY
import com.silk.client.ui.activity.authentication.AuthenticationActivity
import com.silk.client.ui.activity.dashboard.DashboardActivity

class MainActivity : ComponentActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appPreferences = this@MainActivity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val retrievedToken = appPreferences.getString(TOKEN_KEY, null)

        if(retrievedToken.isNullOrEmpty()){
            startActivity(AuthenticationActivity.newIntent(this@MainActivity))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }else{
            startActivity(DashboardActivity.newIntent(this@MainActivity))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
    }
}