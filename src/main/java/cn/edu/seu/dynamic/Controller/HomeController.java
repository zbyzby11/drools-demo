package cn.edu.seu.dynamic.Controller;

//import cn.edu.seu.dynamic.DAO.DAOImpl.MongoDAOImpl;
//import cn.edu.seu.dynamic.Info.AppInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller public class HomeController
{

//    @Autowired private MongoDAOImpl dao;

    @RequestMapping("/index") public String hello()
    {
        System.out.println("ceshi");
        return "index";
    }

//    @RequestMapping(value = "/searchById", method = RequestMethod.POST, produces = "application/json; charset=utf-8") public @ResponseBody String findById(
//            @RequestBody Map<String, String> searchValueMap,
//            HttpServletRequest request, HttpServletResponse response)
//    {
//        Map<String, String> result = new HashMap<String, String>();
//        Gson gson = new Gson();
//
//        System.out.println("search");
//
//        try
//        {
//            // todo : ID detection/verification
//            String id = searchValueMap.get("ID");
//
//            AppInfo a = dao.findInstanceById(id);
//
//            return gson.toJson(a);
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            result.put("state", "-1");
//            return gson.toJson(result);
//        }

//    }

}
