package com.onew.onewday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.onew.onewday.fragment.DiaryListFragment

import com.onew.onewday.databinding.ActivityMainBinding
import com.onew.onewday.fragment.DiaryWriteFragment
import com.onew.onewday.fragment.MyFragment
import com.onew.onewday.fragment.QuoteFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setTheFirstFragment()
        navigationItemSelect()


    }

    private fun navigationItemSelect(){
        binding.chipNav.run {
            setOnItemSelectedListener { item ->
                when (item) {
                    R.id.diaryList -> replaceFragment(DiaryListFragment())
                    R.id.diaryWrite -> replaceFragment(DiaryWriteFragment())
                    R.id.quote -> replaceFragment(QuoteFragment())
                    R.id.my -> replaceFragment(MyFragment())
                }
                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFrag, fragment)
        fragmentTransaction.commit()
    }

    private fun setTheFirstFragment() {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.mainFrag, DiaryListFragment())
        fragmentTransaction.commit()
        binding.chipNav.setItemSelected(R.id.diaryList)

    }
}