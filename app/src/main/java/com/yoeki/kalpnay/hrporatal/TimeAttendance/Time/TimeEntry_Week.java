package com.yoeki.kalpnay.hrporatal.TimeAttendance.Time;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.yoeki.kalpnay.hrporatal.Login.Api;
import com.yoeki.kalpnay.hrporatal.Login.ApiInterface;
import com.yoeki.kalpnay.hrporatal.R;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.Model.TimeAttendance_Info.TimeEntryData;
import com.yoeki.kalpnay.hrporatal.TimeAttendance.Model.TimeAttendance_Info.WeekTIme_Entries_Recieve;
import com.yoeki.kalpnay.hrporatal.setting.preferance;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by IACE on 05-Oct-18.
 */

public class TimeEntry_Week extends AppCompatActivity implements WeekView.EventClickListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener, MonthLoader.MonthChangeListener {
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;
    Button menu_Entries,Pro_back;
    ApiInterface apiInterface;
    String[] CmngstartTime, EndTime, date;
    String description;
    int i = 0, yeaR, montH;

    List<WeekTIme_Entries_Recieve.ListEmpTimeSheet> dataofWeekEntries;
    private final SparseArray<List<WeekViewEvent>> loadedEvents = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_entry_week);


        dataofWeekEntries = new ArrayList<WeekTIme_Entries_Recieve.ListEmpTimeSheet>();
        serverCode();
        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) findViewById(R.id.weekView);
        menu_Entries = (Button) findViewById(R.id.menu_Entries);
        Pro_back = (Button) findViewById(R.id.Pro_back);
        // Show a toast message about the touched event.
        // mWeekView.setOnEventClickListener(this);

        Pro_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TimeEntry.class);
                startActivity(intent);
                finish();
            }
        });

        menu_Entries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context wrapper = new ContextThemeWrapper(getBaseContext(), R.style.popupMenuStyle);
                PopupMenu popup = new PopupMenu(wrapper, menu_Entries);
                popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        int id = item.getItemId();
                        setupDateTimeInterpreter(id == R.id.action_week_view);
                        switch (id) {
//                            case R.id.action_today:
//                                serverCode();
//                                mWeekView.goToToday();
//                                return true;
                            case R.id.action_day_view:
                                if (mWeekViewType != TYPE_DAY_VIEW) {
                                    serverCode();
                                    item.setChecked(!item.isChecked());
                                    mWeekViewType = TYPE_DAY_VIEW;
                                    mWeekView.setNumberOfVisibleDays(1);

                                    // Lets change some dimensions to best fit the view.
                                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                                }
                                return true;
                            case R.id.action_three_day_view:
                                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                                    serverCode();
                                    item.setChecked(!item.isChecked());
                                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                                    mWeekView.setNumberOfVisibleDays(3);

                                    // Lets change some dimensions to best fit the view.
                                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                                }
                                return true;
                            case R.id.action_week_view:
                                if (mWeekViewType != TYPE_WEEK_VIEW) {
                                    serverCode();
                                    item.setChecked(!item.isChecked());
                                    mWeekViewType = TYPE_WEEK_VIEW;
                                    mWeekView.setNumberOfVisibleDays(7);

                                    // Lets change some dimensions to best fit the view.
                                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                                }
                        }
                        return true;
                    }
                });
                popup.show();//showing popup menu
            }
        });

        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);
        // Set long press listener for empty view
        mWeekView.setEmptyViewLongPressListener(this);
        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),TimeEntry.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(this, getEventTitle(time), Toast.LENGTH_SHORT).show();
    }


    public void serverCode() {
        String user_id = null;
        user_id = preferance.getInstance(getApplicationContext()).getUserId();
        final ProgressDialog pd = new ProgressDialog(getApplicationContext());
        pd.setCancelable(true);
        apiInterface = Api.getClient().create(ApiInterface.class);

        TimeEntryData timeEntryData = new TimeEntryData(user_id);
        Call<WeekTIme_Entries_Recieve> call2 = apiInterface.idUser(timeEntryData);

        call2.enqueue(new Callback<WeekTIme_Entries_Recieve>() {
            @Override
            public void onResponse(Call<WeekTIme_Entries_Recieve> call, Response<WeekTIme_Entries_Recieve> response) {
                pd.dismiss();
                WeekTIme_Entries_Recieve weekTIme_entries_recieve = response.body();
                try {
                    String status = weekTIme_entries_recieve.getStatus();
                    String mess = weekTIme_entries_recieve.getMessage();
                    dataofWeekEntries = weekTIme_entries_recieve.getListEmpTimeSheet();

                    if (dataofWeekEntries.size() > 0) {
                        String full_date = dataofWeekEntries.get(0).getDate();
                        String[] full_month = full_date.split("/");
                        montH = Integer.parseInt(full_month[0]);
                        String[] full_Year = full_month[2].split("\\s+");
                        yeaR = Integer.parseInt(full_Year[0]);

                        for (int i = 0; i < dataofWeekEntries.size(); i++) {
                            CmngstartTime = dataofWeekEntries.get(i).getStartTime().split(":");
                            EndTime = dataofWeekEntries.get(i).getEndTime().split(":");
                            description = dataofWeekEntries.get(i).getDescription();
                            date = dataofWeekEntries.get(i).getDate().split("/");
                        }
                        mWeekView.notifyDatasetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<WeekTIme_Entries_Recieve> call, Throwable t) {
                pd.show();
                serverCode();
            }
        });
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        final List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

                    if (dataofWeekEntries.size()> 0) {
                        String full_date = dataofWeekEntries.get(0).getDate();
                        String[] full_month = full_date.split("/");
                        montH = Integer.parseInt(full_month[0]);
                        String[] full_Year = full_month[2].split("\\s+");
                        yeaR = Integer.parseInt(full_Year[0]);

                        BigInteger resultHour;

                        for (int i = 0; i < dataofWeekEntries.size(); i++) {
                            WeekViewEvent weekViewEvent = new WeekViewEvent();

                            CmngstartTime = dataofWeekEntries.get(i).getStartTime().split(":");
                            EndTime = dataofWeekEntries.get(i).getEndTime().split(":");
                            description = dataofWeekEntries.get(i).getDescription();
                            date = dataofWeekEntries.get(i).getDate().split("/");

                            int startHour = Integer.parseInt(CmngstartTime[0]);
                            int startMinute = Integer.parseInt(CmngstartTime[1]);

                            int startDateDate = Integer.parseInt(date[1]);
                            resultHour = BigInteger.valueOf(Long.parseLong(EndTime[0])).subtract(BigInteger.valueOf(Long.parseLong(CmngstartTime[0])));
                            int endHour = resultHour.intValue();
                            int endMinute = Integer.parseInt(EndTime[1]);
//
                            Calendar startTime = Calendar.getInstance();
                            startTime.set(Calendar.HOUR_OF_DAY, startHour); //Hours
                            startTime.set(Calendar.MINUTE, startMinute);      //Minute
                            // startTime.set(Calendar.MONTH, newMonth - 1);
                            startTime.set(Calendar.DATE, startDateDate);                //Date
                            //  startTime.set(Calendar.YEAR, newYear);
                            Calendar endTime = (Calendar) startTime.clone();
                            endTime.add(Calendar.HOUR, endHour);               //EndTime-startTime = Remaining hours time
                            endTime.set(Calendar.MINUTE, endMinute);
                            //Remaining Time
                            endTime.set(Calendar.MONTH, newMonth - 1);

                            WeekViewEvent event = new WeekViewEvent(i, description, startTime, endTime);
                            events.add(event);

                        }
                        dataofWeekEntries.clear();

                    }

        return events;
    }
}

