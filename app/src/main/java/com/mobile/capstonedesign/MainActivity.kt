package com.mobile.capstonedesign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobile.capstonedesign.ui.campaign.CampaignFragment
import com.mobile.capstonedesign.ui.community.CommunityFragment
import com.mobile.capstonedesign.ui.home.HomeFragment
import com.mobile.capstonedesign.ui.records.RecordsFragment
import kotlinx.android.synthetic.main.fragment_community.*

private const val TAG_HOME_FRAGMENT = "home_fragment"
private const val TAG_RECORDS_FRAGMENT = "records_fragment"
private const val TAG_COMMUNITY_FRAGMENT = "community_fragment"
private const val TAG_CAMPAIGN_FRAGMENT = "campaign_fragment"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        // Default Fragment of MainActivity
        setFragment(TAG_HOME_FRAGMENT, HomeFragment())

        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> setFragment(TAG_HOME_FRAGMENT, HomeFragment())
                R.id.navigation_records -> setFragment(TAG_RECORDS_FRAGMENT, RecordsFragment())
                R.id.navigation_community -> setFragment(TAG_COMMUNITY_FRAGMENT, CommunityFragment())
                R.id.navigation_campaign -> setFragment(TAG_CAMPAIGN_FRAGMENT, CampaignFragment())
            }

            true
        }
    }

    /* Fragment State 유지 함수 */
    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null) {
            ft.add(R.id.nav_fragment, fragment, tag)
        }

        val home = manager.findFragmentByTag(TAG_HOME_FRAGMENT)
        val records = manager.findFragmentByTag(TAG_RECORDS_FRAGMENT)
        val community = manager.findFragmentByTag(TAG_COMMUNITY_FRAGMENT)
        val campaign = manager.findFragmentByTag(TAG_CAMPAIGN_FRAGMENT)

        // Hide all Fragment
        if (home != null) {
            ft.hide(home)
        }
        if (records != null) {
            ft.hide(records)
        }
        if (community != null) {
            ft.hide(community)
        }
        if (campaign != null) {
            ft.hide(campaign)
        }

        // Show  current Fragment
        if (tag == TAG_HOME_FRAGMENT) {
            if (home != null) {
                ft.show(home)
            }
        }
        if (tag == TAG_RECORDS_FRAGMENT) {
            if (records != null) {
                ft.show(records)
            }
        }
        if (tag == TAG_COMMUNITY_FRAGMENT) {
            if (community != null) {
                ft.show(community)
            }
        }
        if (tag == TAG_CAMPAIGN_FRAGMENT) {
            if (campaign != null) {
                ft.show(campaign)
            }
        }

        ft.commitAllowingStateLoss()
    }
}
