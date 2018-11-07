package cn.addapp.hotelcalendar;

import android.view.ContextThemeWrapper;
import android.widget.TextView;
import com.savvi.rangedatepicker.R;

public class CustomDayViewAdapter implements DayViewAdapter {
  @Override
  public void makeCellView(CalendarCellView parent) {
      TextView textView = new TextView(
              new ContextThemeWrapper(parent.getContext(), R.style.CalendarCell_CalendarDate));
      textView.setDuplicateParentStateEnabled(true);
      parent.addView(textView);
      parent.setDayOfMonthTextView(textView);


//      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day,null);
//      TextView tvDay = view.findViewById(R.id.tv_day);
//      TextView tvDayUp = view.findViewById(R.id.tv_day_up);
//      TextView tvDayDown = view.findViewById(R.id.tv_day_down);
//      tvDay.setDuplicateParentStateEnabled(true);
//      parent.addView(view);
//      parent.setDayOfMonthTextView(tvDay);
//      parent.setDayUpTextView(tvDayUp);
//      parent.setDayDownTextView(tvDayDown);
  }
}
