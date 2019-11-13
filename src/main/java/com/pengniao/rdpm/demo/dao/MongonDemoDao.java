package com.pengniao.rdpm.demo.dao;

import com.pengniao.rdpm.entity.Demo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/** 功能描述: MongonDemo 数据访问层接口
 * @param null
* @return:
* @Author: lj
* @Date: 2019/9/27 16:33
*/
public interface MongonDemoDao {
    /** 功能描述: 新增
     * @param demo
    * @return: void
    * @Author: lj
    * @Date: 2019/9/27 16:35
    */
    public void addMongonDemo(Demo demo) throws Exception;

    public List<Demo> getMongoDemo(Query query, Pageable pageable);

    public Long queryMongoDemoCount(Query query);

    public  void deleteMongoDemo(Query query)throws Exception;
}
