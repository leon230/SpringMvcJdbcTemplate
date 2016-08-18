package com.tickets.dao;

import com.tickets.data.PieChartData;
import com.tickets.model.ChartKeyValue;

import java.util.List;

/**
 * Created by lukasz.homik on 2016-08-18.
 */
public class ChartsDAOImpl implements ChartsDAO {

    @Override
    public List<ChartKeyValue> getPieChartData(){
        return PieChartData.getPieDataList();
    }
}
