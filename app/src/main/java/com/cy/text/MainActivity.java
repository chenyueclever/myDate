package com.cy.text;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends Activity {

    PinnedSectionListView lv_contact_list;
    DateListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_contact_list = findViewById(R.id.lv_contact_list);
        adapter = new DateListAdapter(this, initDateData());
        lv_contact_list.setAdapter(adapter);
        lv_contact_list.setSelection(adapter.getCount() - 1);

    }


    private List<DateListBean> initDateData() {
        List<DateListBean> list = new ArrayList<>();
        List<DateListDayBean> dayList;
        DateListBean bean;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        for (; ; ) {
            if (list.size() == 0) {
                bean = new DateListBean(year, month);
            } else {
                if (list.get(0).getMonth() != 0) {
                    bean = new DateListBean(list.get(0).getYear(), list.get(0).getMonth() - 1);
                } else {
                    bean = new DateListBean(list.get(0).getYear() - 1, 11);
                }

            }
            list.add(0, bean);

            List<DateListBean> tempList = new ArrayList();


            dayList = new ArrayList<>();
            //加空白
            for (int i = 0; i < getWeek(bean.getYear(), bean.getMonth()) - 1; i++) {
                dayList.add(new DateListDayBean(bean.getYear(), bean.getMonth(), 0));
            }
            for (int i = 1; i <= getMonthLastDay(bean.getYear(), bean.getMonth()); i++) {
                dayList.add(new DateListDayBean(bean.getYear(), bean.getMonth(), i));
                if (dayList.size() == 7) {
                    tempList.add(new DateListBean(bean.getYear(), bean.getMonth(), 1, dayList));
                    dayList = new ArrayList<>();
                }
            }
            int fillNum = 0;
            if (dayList.size() % 7 != 0) {
                fillNum = 7 - dayList.size() % 7;
            }
            for (int i = 0; i < fillNum; i++) {
                dayList.add(new DateListDayBean(bean.getYear(), bean.getMonth(), 0));
                if (dayList.size() == 7) {
                    tempList.add(new DateListBean(bean.getYear(), bean.getMonth(), 1, dayList));
                }
            }


            list.addAll(1, tempList);

            if (bean.getYear() == (year - 3) && bean.getMonth() == month) {
                break;
            }
        }

        return list;

    }


    /*获取星期几*/
    public static int getWeek(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int i = cal.get(Calendar.DAY_OF_WEEK);
        return i;
//        switch (i) {
//            case 1:
//                return "星期日";
//            case 2:
//                return "星期一";
//            case 3:
//                return "星期二";
//            case 4:
//                return "星期三";
//            case 5:
//                return "星期四";
//            case 6:
//                return "星期五";
//            case 7:
//                return "星期六";
//            default:
//                return "";

    }

    /**
     * 得到指定月的天数
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    public class DateListAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {
        private LayoutInflater inflater;
        private Context mContext;
        private List<DateListBean> list;
        int[] before = null;
        int[] after = null;

        public DateListAdapter(Context context, List<DateListBean> list) {
            this.inflater = LayoutInflater.from(context);
            this.list = list;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public DateListBean getItem(int arg0) {
            return list.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int position, View converview, ViewGroup arg2) {
            ViewHolder holder;
            if (converview == null) {
                holder = new ViewHolder();
                converview = inflater.inflate(R.layout.item_date_list, arg2, false);
                holder.tv = converview.findViewById(R.id.tv);
                holder.gv = converview.findViewById(R.id.gv);
                holder.tv1 = converview.findViewById(R.id.tv1);
                holder.tv2 = converview.findViewById(R.id.tv2);
                holder.tv3 = converview.findViewById(R.id.tv3);
                holder.tv4 = converview.findViewById(R.id.tv4);
                holder.tv5 = converview.findViewById(R.id.tv5);
                holder.tv6 = converview.findViewById(R.id.tv6);
                holder.tv7 = converview.findViewById(R.id.tv7);
                converview.setTag(holder);
            } else {
                holder = (ViewHolder) converview.getTag();
            }
            if (list.get(position).getType() == 0) {
                holder.tv.setVisibility(View.VISIBLE);
                holder.gv.setVisibility(View.GONE);
                holder.tv.setText(list.get(position).getYear() + "年" + (list.get(position).getMonth()
                        + 1) + "月");
            } else {
                holder.gv.setVisibility(View.VISIBLE);
                holder.tv.setVisibility(View.GONE);
                setText(holder.tv1, position, 0);
                setText(holder.tv2, position, 1);
                setText(holder.tv3, position, 2);
                setText(holder.tv4, position, 3);
                setText(holder.tv5, position, 4);
                setText(holder.tv6, position, 5);
                setText(holder.tv7, position, 6);
            }
            return converview;
        }

        public int getItemViewType(int arg0) {
            return list.get(arg0).getType();
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public boolean isItemViewTypePinned(int viewType) {
            return viewType == 0;
        }

        private class ViewHolder {
            TextView tv, tv1, tv2, tv3, tv4, tv5, tv6, tv7;
            LinearLayout gv;
        }

        private void setText(TextView tv, final int position, final int index) {
            final int day = list.get(position).getList().get(index).getDay();
            final int month = list.get(position).getMonth();
            final int year = list.get(position).getYear();

            if (day != 0)
                tv.setText(day + "");
            else
                tv.setText("");

            if (before != null && (year == before[0] && month == before[1] && day == before[2])) {
                //等于日期
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundColor(Color.RED);
            } else if (after != null && (year == after[0] && month == after[1] && day == after[2])) {
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundColor(Color.RED);
            } else if ((before != null && after != null) && ((year > before[0] ||
                    (year == before[0] && month > before[1]) ||
                    (year == before[0] && month == before[1] && day > before[2]))
                    && (year < after[0] || (year == after[0] && month < after[1]) ||
                    (year == after[0] && month == after[1] && day < after[2])))) {
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundColor(Color.BLUE);
            } else {
                tv.setTextColor(Color.BLACK);
                tv.setBackgroundColor(Color.WHITE);
            }

//            if (after == null)
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (day == 0)
                            return;
                        if (before == null) {
                            before = new int[]{year, list.get(position)
                                    .getMonth(), day};
                        } else if (year < before[0] || (year == before[0] && month < before[1]) ||
                                (year == before[0] && month == before[1] && day < before[2])) {
                            before = new int[]{year, month, day};
                            after = null;
                        } else {
                            after = new int[]{year, month, day};
                        }
                        notifyDataSetChanged();

                    }
                });
//            else
//                tv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });

        }


    }


}
