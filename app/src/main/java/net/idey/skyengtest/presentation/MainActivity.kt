package net.idey.skyengtest.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.idey.skyengtest.R
import net.idey.skyengtest.presentation.search.SearchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance())
                .commit()
    }
}