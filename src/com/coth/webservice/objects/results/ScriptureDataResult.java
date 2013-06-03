package com.coth.webservice.objects.results;

import com.coth.webservice.objects.ScriptureBasicDto;

import java.util.Date;
import java.util.List;

/**
 * Created by yanga on 2013/05/26.
 */
public class ScriptureDataResult extends ServerResult{
    public List<ScriptureBasicDto> Scriptures;
    public Date TimeStamp;
    public Integer TotalPossibleResultsCount;
}