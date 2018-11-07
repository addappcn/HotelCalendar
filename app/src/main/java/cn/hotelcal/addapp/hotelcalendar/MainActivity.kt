package cn.hotelcal.addapp.hotelcalendar

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cn.hotelcal.addapp.hotelcalendar.Common.FORMATTER_MM_DD
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.Utils
import com.savvi.rangedatepicker.CalendarPickerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var selectDates = ArrayList<Date>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_select_date.setOnClickListener {
            initCalAndShow(Date())
        }
    }

    fun initCalAndShow(currentDate: Date) {
        Utils.init(this)
        val dialog = CommonDialog(this, R.layout.cal_dialog_calendar)
        dialog.setDialogHeight(ScreenUtils.getScreenHeight() / 4 * 3)
        dialog.setAnimation(R.style.AnimBottom)
        dialog.setGravity(Gravity.BOTTOM)
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.getScreenHeight() / 4 * 3)
        dialog.rootView.layoutParams = params
        val calendarMax = Calendar.getInstance()
        val calendarCurrent = Calendar.getInstance()
        calendarCurrent.time = currentDate
        calendarCurrent.set(Calendar.HOUR, 0)//小时设置为0
        calendarCurrent.set(Calendar.MINUTE, 0)//分钟设置为0
        calendarCurrent.set(Calendar.SECOND, 0)//秒设置为0

        calendarMax.time = currentDate
        calendarMax.set(Calendar.HOUR, 0)//小时设置为0
        calendarMax.set(Calendar.MINUTE, 0)//分钟设置为0
        calendarMax.set(Calendar.SECOND, 0)//秒设置为0
        calendarMax.add(Calendar.DAY_OF_MONTH, 180)//可选180天
        //        calendarMax.add(Calendar.MONTH, 6);//可选6个月
        //        lastYear.add(Calendar.MONTH, -1);

        val  calendarView = dialog.rootView.findViewById(R.id.calendar_view) as CalendarPickerView
        //        ArrayList<Integer> list = new ArrayList<>();
        //        list.add(3);
        //        list.add(4);
        ////        calendar.deactivateDates(list);//表格第三列不可选择
        //        ArrayList<Date> arrayList = new ArrayList<>();
        //        try {
        //            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        //            String strdate = "22-2-2018";
        //            String strdate2 = "26-2-2018";
        //            Date newdate = dateformat.parse(strdate);
        //            Date newdate2 = dateformat.parse(strdate2);
        //            arrayList.add(newdate);
        //            arrayList.add(newdate2);
        //        } catch (ParseException e) {
        //            e.printStackTrace();
        //        }

        val selectedDates = ArrayList<Date>()//上次选择的时间范围
        if (selectDates.size >= 2) {
            selectedDates.add(selectDates.get(0))
            selectedDates.add(selectDates.get(selectDates.size - 1))
        }
        calendarView.init(calendarCurrent.time, calendarMax.time, Common.FORMATTER_yyyy_mm) //
            .inMode(CalendarPickerView.SelectionMode.RANGE) //
            //.withSelectedDate(new Date())//选中一天
            //.withDeactivateDates(list)////表格第三列不可选择
            .withSelectedDates(selectedDates)//选中范围

        calendarView.setOnDateSelectedListener(object : CalendarPickerView.OnDateSelectedListener {
            @SuppressLint("SetTextI18n")
            override fun onDateSelected(date: Date) {
                val selDates = calendarView.getSelectedDates()
                selectDates = calendarView.getSelectedDates() as ArrayList<Date>
                if (selDates.size > 1) {
                    Handler().postDelayed({ dialog.dismiss() }, 600)
                    tv_select_date.text = FORMATTER_MM_DD.format(selDates.get(0)) + "到" +
                            FORMATTER_MM_DD.format(selDates[selDates.size - 1])
                }
            }
            override fun onDateUnselected(date: Date) {}
        })
        dialog.show()
    }
}
