package cn.edu.seu.dynamic.Info;

import cn.edu.seu.dynamic.service.drools.Triple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xul on 2018/10/15.
 */
public class App {
    private List<String> app;

    public App() {
        app = new ArrayList<>();
    }

    public List<Triple> getAppTriple() {
        List<Triple> res = new ArrayList<>();
        for (String s : app) {
            String[] a = s.split(" ");
            res.add(new Triple(a[0], a[1], a[2]));
        }
        return res;
    }
}

