package com.mobile.capstonedesign.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.mobile.capstonedesign.AlcoholMeasureActivity
import com.mobile.capstonedesign.R
import com.mobile.capstonedesign.TobaccoMeasureActivity
import com.mobile.capstonedesign.config.JwtConfig
import com.mobile.capstonedesign.dto.request.MonthRecordRequestDTO
import com.mobile.capstonedesign.dto.response.MonthRecordResponseDTO
import com.mobile.capstonedesign.http.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.DecimalFormat

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private var mMonthDrinkRecords = ArrayList<BarEntry>()
    private var mResponseMonthDrinkRecords = ArrayList<MonthRecordResponseDTO>()

    private var mMonthSmokeRecords = ArrayList<BarEntry>()
    private var mResponseMonthSmokeRecords = ArrayList<MonthRecordResponseDTO>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getTotalDrink()
        getTotalSmoke()

        btnMeasureDrink.setOnClickListener {
            val intent = Intent(activity, AlcoholMeasureActivity::class.java)
            startActivity(intent)
        }

        btnMeasureSmoke.setOnClickListener {
            val intent = Intent(activity, TobaccoMeasureActivity::class.java)
            startActivity(intent)
        }

        for (i in 1 .. 31){
            mMonthDrinkRecords.add(BarEntry(i.toFloat(), 0f))
        }

        for (i in 1 .. 31){
            mMonthSmokeRecords.add(BarEntry(i.toFloat(), 0f))
        }

        initDrinkLineChart()
        setDrinkChart(mMonthDrinkRecords)

        initSmokeLineChart()
        setSmokeChart(mMonthSmokeRecords)

        getMonthDrinkRecords(6)
        getMonthSmokeRecords(6)

//        init()
    }

    private fun getTotalDrink() {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL).getTotalDrink(JwtConfig.USER_ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                tvTotalDrinkPrice.text = modifyFormat(items.data.totalPrice)
                tvTotalDrinkGlass.text = modifyFormat(items.data.totalGlass)
            }, {
                Toast.makeText(
                    activity,
                    "토탈 값을 불러오는 데 실패했습니다. \n" + it.message + "\n" + it.cause,
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

    private fun getTotalSmoke() {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL).getTotalSmoke(JwtConfig.USER_ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                tvTotalSmokePrice.text = modifyFormat(items.data.totalPrice)
                tvTotalSmokePiece.text = modifyFormat(items.data.totalPiece)
            }, {
                Toast.makeText(
                    activity,
                    "토탈 값을 불러오는 데 실패했습니다. \n" + it.message + "\n" + it.cause,
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

    private fun getMonthDrinkRecords(month: Int) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL)
            .getMonthDrinkRecords(MonthRecordRequestDTO(JwtConfig.USER_ID, month))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                setMonthDrink(month, items.data)
            }, {
                Toast.makeText(
                    activity,
                    "토탈 값을 불러오는 데 실패했습니다. \n" + it.message + "\n" + it.cause,
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

    private fun getMonthSmokeRecords(month: Int) {
        val BASE_URL = resources.getString(R.string.server_http_port) // 서버
        val disposable = HttpClient().getApi(BASE_URL)
            .getMonthSmokeRecords(MonthRecordRequestDTO(JwtConfig.USER_ID, month))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                setMonthSmoke(month, items.data)
            }, {
                Toast.makeText(
                    activity,
                    "토탈 값을 불러오는 데 실패했습니다. \n" + it.message + "\n" + it.cause,
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

    private fun setMonthDrink(month: Int, response: ArrayList<MonthRecordResponseDTO>) {
        var str: String
        var x: String
        for (data in response) {
            str = data.date
            x = str.substring(str.length-2, str.length)
            mMonthDrinkRecords.add(BarEntry(x.toFloat(),data.total_amount.toFloat()))
        }

        initDrinkLineChart()
        setDrinkChart(mMonthDrinkRecords)
    }

    private fun setMonthSmoke(month: Int, response: ArrayList<MonthRecordResponseDTO>) {
        var str: String
        var x: String
        for (data in response) {
            str = data.date
            x = str.substring(str.length-2, str.length)
            mMonthSmokeRecords.add(BarEntry(x.toFloat(),data.total_amount.toFloat()))
        }

        initSmokeLineChart()
        setSmokeChart(mMonthSmokeRecords)
    }

    private fun modifyFormat(money: Int): String {
        val formatter = DecimalFormat("###,###,###")

        return formatter.format(money)
    }

    private fun initDrinkLineChart() {
        val xAxis = chartDrink.xAxis
        xAxis.setDrawLabels(true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f

        val rightYAxis = chartDrink.axisRight
        rightYAxis.setDrawLabels(false)
    }

    private fun setDrinkChart(listData: ArrayList<BarEntry>) {
        val dataSet = BarDataSet(listData, "음주량")
        dataSet.color = ContextCompat.getColor(requireContext(), R.color.colorPink)
        dataSet.valueTextColor = ContextCompat.getColor(requireContext(), android.R.color.black)

        val lineData = BarData(dataSet)
        chartDrink.setFitBars(true)
        chartDrink.data = lineData
        chartDrink.invalidate()
    }

    private fun initSmokeLineChart() {
        val xAxis = chartSmoke.xAxis
        xAxis.setDrawLabels(true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f

        val rightYAxis = chartSmoke.axisRight
        rightYAxis.setDrawLabels(false)
    }

    private fun setSmokeChart(listData: ArrayList<BarEntry>) {
        val dataSet = BarDataSet(listData, "흡연량")
        dataSet.color = ContextCompat.getColor(requireContext(), R.color.colorSky)
        dataSet.valueTextColor = ContextCompat.getColor(requireContext(), android.R.color.black)

        val lineData = BarData(dataSet)
        chartSmoke.setFitBars(true)
        chartSmoke.data = lineData
        chartSmoke.invalidate()
    }

//    private fun init() {
//        vpAlcoholChart?.adapter = object : FragmentStateAdapter(this) {
//            override fun createFragment(position: Int): Fragment {
//                return ChartPagerFragment.newInstance()
//            }
//
//            override fun getItemCount(): Int {
//                return 12
//            }
//        }
//
//        TabLayoutMediator(tlAlcoholCalendar, this.vpAlcoholChart) { tab, position ->
//            tab.text = when (position) {
//                0 -> (position + 1).toString() + "월"
//                1 -> (position + 1).toString() + "월"
//                2 -> (position + 1).toString() + "월"
//                3 -> (position + 1).toString() + "월"
//                4 -> (position + 1).toString() + "월"
//                5 -> (position + 1).toString() + "월"
//                6 -> (position + 1).toString() + "월"
//                7 -> (position + 1).toString() + "월"
//                8 -> (position + 1).toString() + "월"
//                9 -> (position + 1).toString() + "월"
//                10 -> (position + 1).toString() + "월"
//                11 -> (position + 1).toString() + "월"
//                else -> (position + 1).toString() + "월"
//            }
//        }.attach()
//    }
}
