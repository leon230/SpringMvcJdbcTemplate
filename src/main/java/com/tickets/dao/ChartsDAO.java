package com.tickets.dao;

import com.tickets.model.ChartKeyValue;
import java.util.List;

/**
 * Created by lukasz.homik on 2016-08-18.
 */
public interface ChartsDAO {
    public List<ChartKeyValue> getProgressData();

    public List<ChartKeyValue> getPriorityData();

}
