package com.onew.onewday.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendarview.ui.ViewContainer
import com.onew.onewday.DiaryList.DiaryListAdapter
import com.onew.onewday.DiaryList.DiaryModel
import com.onew.onewday.databinding.FragmentDiaryListBinding
import com.vivekkaushik.datepicker.DatePickerTimeline
import com.vivekkaushik.datepicker.OnDateSelectedListener
import java.time.LocalDate
import java.util.*
import com.shrikanthravi.collapsiblecalendarview.data.Day

import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar

import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import com.onew.onewday.R


class DiaryListFragment : Fragment() {
    private lateinit var binding: FragmentDiaryListBinding
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val dataList = mutableListOf<DiaryModel>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDiaryListBinding.bind(view)
        recyclerView = binding.nav1RecyclerView
        viewManager = LinearLayoutManager(context, VERTICAL,false)
        recyclerView.layoutManager = viewManager
        viewAdapter = DiaryListAdapter(dataList)

        dataList.add(DiaryModel("2022.09.04","오늘의 일기","이거 진짜 말 줄임표 기능 되려나? 그렇게 설정해두긴 했는데 잘 모르겠다. "))
        dataList.add(DiaryModel("2022.09.03","추운 일기","떡볶이 맛있긴 한데 다른것도 먹고싶다. 예를들어서 라면 맛있다."))
        dataList.add(DiaryModel("2022.09.02","테스트 일기","떡볶이 맛있다."))
        dataList.add(DiaryModel("2022.09.01","타코야끼","한강믈 온도 api 써야하나 말아야하나 고민이군"))

        if(dataList.isEmpty()){
            binding.bookmarkLayout.visibility = View.VISIBLE
        }

        viewAdapter.notifyDataSetChanged()
        recyclerView.adapter = viewAdapter


        val collapsibleCalendar: CollapsibleCalendar = binding.calendarView
        collapsibleCalendar.setCalendarListener(object : CollapsibleCalendar.CalendarListener {
            override fun onDaySelect() {
                val day: Day? = collapsibleCalendar.selectedDay
//                val day: Day = viewCalendar.getSelectedDay()
                if (day != null) {
                    Log.i(
                        javaClass.name, "Selected Day: "
                                + day.year + "/" + (day.month + 1) + "/" + day.day
                    )
                }
            }

            override fun onItemClick(view: View) {
            }
            override fun onClickListener() {
                TODO("Not yet implemented")
            }

            override fun onDataUpdate() {}
            override fun onDayChanged() {
                TODO("Not yet implemented")
            }

            override fun onMonthChange() {}
            override fun onWeekChange(i: Int) {}
        })

    }




    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { datePicker, y, m, d->
            Toast.makeText(requireContext(), "$y-$m-$d", Toast.LENGTH_SHORT).show()
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show()
    }


    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        binding = FragmentDiaryListBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_diary_list, container, false)
    }
}