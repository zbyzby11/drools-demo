package cn.edu.seu.dynamic.Info;

import cn.edu.seu.dynamic.service.drools.Triple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xul on 2018/10/15.
 */
public class Apps {
    private List<App> apps;

    public Apps(){
        apps = new ArrayList<App>();
    }

    public List<App> getApps() {
        return apps;
    }
}

