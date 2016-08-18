package com.tickets.data;

import com.tickets.model.ChartKeyValue;

import java.util.ArrayList;
import java.util.List;

public class PieChartData {

    private static final List<ChartKeyValue> pieDataList;

    static {
        pieDataList = new ArrayList<ChartKeyValue>();
        pieDataList.add(new ChartKeyValue("Russia", "17098242"));
        pieDataList.add(new ChartKeyValue("Canada", "9984670"));
        pieDataList.add(new ChartKeyValue("USA", "9826675"));
        pieDataList.add(new ChartKeyValue("China", "9596961"));
        pieDataList.add(new ChartKeyValue("Brazil", "8514877"));
        pieDataList.add(new ChartKeyValue("Australia", "7741220"));
        pieDataList.add(new ChartKeyValue("India", "3287263"));
    }

    public static List<ChartKeyValue> getPieDataList() {
        return pieDataList;
    }



}