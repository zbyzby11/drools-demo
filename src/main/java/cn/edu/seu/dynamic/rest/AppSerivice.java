//package cn.edu.seu.dynamic.rest;
//
//import cn.edu.seu.dynamic.Info.Apps;
//import cn.edu.seu.dynamic.service.Transform.TransformApps;
//import cn.edu.seu.dynamic.service.drools.Drools_tutorial_data_from_agraph;
//import cn.edu.seu.dynamic.service.drools.Triple;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class AppSerivice {
//    @RequestMapping(value = "/request", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
//    public @ResponseBody
//    String getSensitiveApps(
//            @RequestBody Map<String, Object> jsonApps) {
//        List<List<String>> apps =  (List<List<String>>)jsonApps.get("data");
//        // gson将字符串apps转化成list<list>
//        List<List<Triple>> input_apps = TransformApps.transformAppsFromString(apps);
//
//        List<List<Triple>> sensitiveApps = new Drools_tutorial_data_from_agraph().getAppSensitiveRate(input_apps);
//        String res = TransformApps.transformToTriples(sensitiveApps);
//        return res;
//    }
//}
