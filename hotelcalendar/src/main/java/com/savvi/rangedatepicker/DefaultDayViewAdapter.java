package com.savvi.rangedatepicker;

import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
