package com.pamella.moviesapp.ui.activitys

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pamella.moviesapp.ui.adapter.ViewPagerAdapter
import com.pamella.moviesapp.ui.adapter.ViewPagerAdapter.Companion.ALL_MOVIES_POSITION
import com.pamella.moviesapp.ui.adapter.ViewPagerAdapter.Companion.FAVORITE_MOVIES_POSITION
import com.pamella.moviesapp.ui.fragment.SearchMoviesFragment
import com.pamella.moviesapp.R


class HomeActivity : AppCompatActivity() {

    private var searchTxt: EditText? = null
    private lateinit var searchButton: ImageButton
    private lateinit var gIcon: ImageView
    private lateinit var searchModeTxt: TextView
    private lateinit var backHomeButton: TextView
    private lateinit var tbLytOptions: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var fragmentContainer: FrameLayout
    private lateinit var movieSearched: String
    private var searchFragment: SearchMoviesFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bindViews()
    }

    private fun bindViews() {
        searchTxt = findViewById(R.id.searchMovieBox)
        searchButton = findViewById(R.id.searchButton)
        tbLytOptions = findViewById(R.id.tabLytOptions)
        viewPager = findViewById(R.id.viewPager)
        gIcon = findViewById(R.id.greenIcon)
        searchModeTxt = findViewById(R.id.searchModeText)
        backHomeButton = findViewById(R.id.backHomeButton)
        fragmentContainer = findViewById(R.id.searchFragmentContainer)

        viewPager.adapter = ViewPagerAdapter(this)
        viewPager.isUserInputEnabled = false
        TabLayoutMediator(tbLytOptions, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()


//          Teste de erro Crashlytics
//        val crashButton = Button(this)
//        crashButton.text = "Test Crash"
//        crashButton.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash
//        }
//
//        addContentView(crashButton, ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT))



        searchTxt?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                visibilitySearchMode()
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s.isEmpty()) {
                        viewPager.setCurrentItem(ALL_MOVIES_POSITION, false)
                        visibilityNotSearchMode()
                    }
                }
            }
        })

        searchTxt?.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    movieSearched = searchTxt?.text.toString()
                    if (searchFragment == null) {
                        searchFragment = SearchMoviesFragment.newInstance(movieSearched)
                        searchFragment?.let {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.searchFragmentContainer, it)
                                .commit()
                        }
                    } else {
                        searchFragment?.updateQuery(movieSearched.toUri())
                    }
                    true
                }
                else -> false
            }
        }

        backHomeButton.setOnClickListener {
            visibilityNotSearchMode()
            searchTxt?.text?.clear()
        }
    }


    private fun visibilitySearchMode() {
        tbLytOptions.visibility = View.GONE
        viewPager.visibility = View.GONE
        gIcon.visibility = View.VISIBLE
        searchModeTxt.visibility = View.VISIBLE
        backHomeButton.visibility = View.VISIBLE
        fragmentContainer.visibility = View.VISIBLE
    }

    private fun visibilityNotSearchMode() {
        tbLytOptions.visibility = View.VISIBLE
        viewPager.visibility = View.VISIBLE
        fragmentContainer.visibility = View.GONE
        gIcon.visibility = View.GONE
        searchModeTxt.visibility = View.GONE
        backHomeButton.visibility = View.GONE
    }


    private fun getTabTitle(position: Int): String {
        return when (position) {
            ALL_MOVIES_POSITION -> "Todos os filmes"
            FAVORITE_MOVIES_POSITION -> "Favoritos"
            else -> ""
        }
    }
}