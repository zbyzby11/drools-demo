package test.dao;

import cn.edu.seu.dynamic.DAO.DAOImpl.MongoDAOImpl;
import cn.edu.seu.dynamic.Info.AppInfo;
import cn.edu.seu.dynamic.util.FileRW;
import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.base.BaseTest;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class MongoDAOImplTest extends BaseTest
{
    @Autowired private MongoDAOImpl dao;

    @Test public void getCount() throws Exception
    {
        Long s = dao.getCount();
        System.out.print(s);
    }

    @Test public void testInsert() throws Exception
    {
        List<AppInfo> arrayList = new ArrayList<>();

        Gson gson = new Gson();

        String filepath = "/Users/duanshangfu/OneDrive/DynamicApp/sample.json";
        String s = new FileRW().readFile(filepath);


        JSONArray arr = new JSONArray(s);
        System.out.println(arr.length());
        for (int i = 0; i < arr.length(); i++)
        {
            String post_id = arr.getJSONObject(i).toString();
            System.out.println(post_id);
            AppInfo a = gson.fromJson(post_id, AppInfo.class);
            arrayList.add(a);

            if (i % 1000 == 0)
            {
                System.out.println(i);
            }
        }

        dao.insertAllAppInfos(arrayList);

        System.out.println("end");

    }

    @Test public void testFindById() throws Exception
    {
        AppInfo a = dao.findInstanceById("375380948");
        System.out.println("aa");
    }

    @Test public void testFindByName() throws Exception
    {
        List<AppInfo> a = dao.findAppByName("category", "社交");
        Gson gson = new Gson();
        String s = gson.toJson(a);
        System.out.println(s);
    }

    @Test public void tetsDeleteAll() throws Exception
    {
        dao.deleteAll();
        System.out.println("ed");
    }
}
