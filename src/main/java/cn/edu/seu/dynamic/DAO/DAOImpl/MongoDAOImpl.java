package cn.edu.seu.dynamic.DAO.DAOImpl;

import cn.edu.seu.dynamic.DAO.MongoDAO;
import cn.edu.seu.dynamic.Info.AppInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class MongoDAOImpl implements MongoDAO
{
    private MongoOperations mongoOperation;

    @Autowired public void setMongoOperation(MongoOperations mongoOperation)
    {
        this.mongoOperation = mongoOperation;
    }

    @Override public long getCount()
    {
        Query query = new Query();
        return mongoOperation.count(query, AppInfo.class);
    }

    @Override public void insertAllAppInfos(List<AppInfo> items)
    {
        mongoOperation.insert(items, AppInfo.class);

    }

    @Override public List<AppInfo> readAppInfoPage(int pageNo, int pageSize)
    {
        Query query = new Query();
        query.skip(pageNo * pageSize);
        query.limit(pageSize);
        List<AppInfo> result = mongoOperation.find(query, AppInfo.class);
        return result;
    }

    @Override public boolean deleteAll()
    {
        Query query = new Query();
        mongoOperation.findAllAndRemove(query, AppInfo.class);
        return true;
    }

    @Override public AppInfo findInstanceById(String id)
    {

        return mongoOperation.findById(id, AppInfo.class);
    }

    @Override public List<AppInfo> findAppByName(String key, String value)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(key).is(value));
        query.fields().include("name");
        List<AppInfo> re = mongoOperation.find(query, AppInfo.class);
        return re;
    }

}
