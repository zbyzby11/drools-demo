package cn.edu.seu.dynamic.service.Transform;

import cn.edu.seu.dynamic.service.drools.ReturnData;
import cn.edu.seu.dynamic.service.drools.Triple;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.edu.seu.dynamic.Info.Apps;

/**
 * Created by Xul on 2018/10/15.
 */
public class TransformApps {

    public static List<List<Triple>> transformAppsFromString(List<List<String>> apps) {
        List<List<Triple>> res = new ArrayList<List<Triple>>();
        for (List<String> ls : apps) {
            List<Triple> tmp = new ArrayList<>();
            for (String s : ls) {
                String[] s_list = s.split("###\t###");
                tmp.add(new Triple(s_list[0], s_list[1], s_list[2]));
            }
            res.add(tmp);
        }

        return res;
    }

    public static String transformToTriples(List<List<Triple>> triples) {
        List<ReturnData> ls = new ArrayList<>();

        for (List<Triple> l : triples) {
            ls.add(new ReturnData(l.get(0).getSubject(), l.get(0).getPredicate(), l.get(0).getObject(), l.get(1).getObject()));
        }

        String jsonStr = new Gson().toJson(ls);

        return jsonStr;
    }
}
