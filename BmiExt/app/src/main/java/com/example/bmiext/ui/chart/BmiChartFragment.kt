package com.example.bmiext.ui.chart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bmiext.databinding.FragmentBmiChartBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter

class BmiChartFragment : Fragment() {
    private var _binding: FragmentBmiChartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBmiChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bmiEntries = listOf(
            Pair("2023-05-01", 22f),
            Pair("2023-05-02", 23f),
            Pair("2023-05-03", 22.5f),
            Pair("2023-05-04", 22.7f),
            Pair("2023-05-05", 22.2f)
        )

        val entries = bmiEntries.mapIndexed { index, data ->
//            val data = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(data.first)
            Entry(index.toFloat(), data.second)
        }

        val lineDataSet = LineDataSet(entries, "BMI over time").apply {
            color = Color.BLUE
            valueTextColor = Color.BLACK
            lineWidth = 2f
            circleRadius = 5f
            setDrawCircleHole(false)
        }

        val lineData = LineData(lineDataSet)
        binding.lineChart.data = lineData

        val xAxis = binding.lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IAxisValueFormatter { value, _ ->
            val index = value.toInt()
            if (index >= 0 && index < bmiEntries.size) {
                bmiEntries[index].first
            } else {
                ""
            }
        }
        xAxis.granularity = 1f
        xAxis.setLabelCount(entries.size, true)

        binding.lineChart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}