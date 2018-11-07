package cn.addapp.hotelcalendar;

import android.view.ContextThemeWrapper;
import android.widget.TextView;
import com.savvi.rangedatepicker.R;

public class DefaultDayViewAdapter implements DayViewAdapter {
  @Override
  public void makeCellView(CalendarCellView parent) {
      TextView textView = new TextView(
              new ContextThemeWrapper(parent.getContext(), R.style.CalendarCell_CalendarDate));
      textView.setDuplicateParentStateEnabled(true);
//      textView.setGravity(Gravity.TOP);
//      textView.setPadding(0,0,0,5);
      parent.addView(textView);
      parent.setDayOfMonthTextView(textView);
  }
}
