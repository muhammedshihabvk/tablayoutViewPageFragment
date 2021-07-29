package com.shabs.tablayoutpageviewfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shabs.tablayoutpageviewfragment.fragments.MovieList

class MainActivity : AppCompatActivity() {

    lateinit var tablayout: TabLayout
    lateinit var viewPager: ViewPager2
    var arraylist = ArrayList<Fragment>()
    var pageTitles = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tablayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        arraylist.add(MovieList.getInstance("Batman"))
        arraylist.add(MovieList.getInstance("spiderman"))
        arraylist.add(MovieList.getInstance("ironman"))

        pageTitles.add("Batman")
        pageTitles.add("spiderman")
        pageTitles.add("ironman")


        var viewPageAdapter = ViewPageAdapter(supportFragmentManager, lifecycle)
        viewPageAdapter.setInitialValues(3, arraylist)

        viewPager.adapter = viewPageAdapter
        TabLayoutMediator(tablayout, viewPager, object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.text = pageTitles.get(position)
            }
        }).attach()


    }
}