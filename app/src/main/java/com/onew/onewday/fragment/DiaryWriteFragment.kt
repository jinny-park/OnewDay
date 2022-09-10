package com.onew.onewday.fragment

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import com.onew.onewday.R
import com.onew.onewday.databinding.FragmentDiaryWriteBinding

class DiaryWriteFragment : Fragment() {
    private lateinit var binding: FragmentDiaryWriteBinding
    private val c: Calendar = Calendar.getInstance()
    private val d = c.get(Calendar.DAY_OF_MONTH)
    private val m = c.get(Calendar.MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_diary_write, container, false)
        binding = FragmentDiaryWriteBinding.bind(view)
        binding.month.text = (m+1).toString()
        binding.day.text = d.toString()

        binding.datePick.setOnClickListener {
            datePickerDialog()
        }


        return view
    }

    private fun datePickerDialog(){
        val dialog = AlertDialog.Builder(requireContext()).create()
        val edialog : LayoutInflater = LayoutInflater.from(context)
        val mView : View = edialog.inflate(R.layout.custom_datepicker,null)

        val month : NumberPicker = mView.findViewById(R.id.monthpicker_datepicker)
        val day : NumberPicker = mView.findViewById(R.id.daypicker_datepicker)
        val cancel : Button = mView.findViewById(R.id.cancel_button)
        val save : Button = mView.findViewById(R.id.ok_button)

        //  editText 설정 해제
        month.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        day.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

        //  최소값 설정
        month.minValue = 1
        day.minValue=1

        //  최대값 설정
        month.maxValue = 12
        day.maxValue = 31

        month.value = c.get(Calendar.MONTH)+1
        day.value = c.get(Calendar.DAY_OF_MONTH)

        //  취소 버튼 클릭 시
        cancel.setOnClickListener {
            dialog.dismiss()
            dialog.cancel()
        }

        //  완료 버튼 클릭 시
        save.setOnClickListener {
            binding.month.text = (month.value).toString()
            binding.day.text = (day.value).toString()

            dialog.dismiss()
            dialog.cancel()
        }

        dialog.setView(mView)
        dialog.create()
        dialog.show()


    }

}