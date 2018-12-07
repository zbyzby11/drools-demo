package cn.edu.seu.dynamic.DAO;

import cn.edu.seu.dynamic.Info.AppInfo;

import java.util.List;

public interface MongoDAO
{
    public long getCount();

    public void insertAllAppInfos(List<AppInfo> items);

    public List<AppInfo> readAppInfoPage(int pageNo, int pageSize);

    public boolean deleteAll();

    public AppInfo findInstanceById(String id);

    public List<AppInfo> findAppByName(String key, String value);
//
//    public long getInstanceCount(String dbName);
//
//    public List<Instance> readInstancePage(int pageNo, int pageSize);
//
//    public List<Instance> readInstancePageByType(int pageNo, int pageSize);
//
//    public long getRequiredTypesInstanceCount(String dbName);

}
