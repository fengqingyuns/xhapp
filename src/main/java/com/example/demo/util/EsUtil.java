package com.example.demo.util;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.update.UpdateRequest;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;

import com.example.demo.modules.film.entity.Film;

public class EsUtil {

    
    public static <T> UpdateQuery filedValueAdd(String id, Class<T> class1, Map<String, Object> params) {
        //构建UpdataRequest对象
        UpdateRequest updateRequest = new UpdateRequest();
        
        //设置参数
        updateRequest.doc(params);
     //   Map<String, Object> params = new HashMap<>();
     //   params.put("incr", addParam);
        //构架UpdateQueryBuilder 对象
        UpdateQueryBuilder updateQueryBuilder = new UpdateQueryBuilder();
        //设置要修改的_id
        updateQueryBuilder.withId(id);
        //设置updateRequest对象
        updateQueryBuilder.withUpdateRequest(updateRequest);
        //设置 class 对象,其实这里用class对象就是要区实体类标注的 index 和 type 也可以直接设置index 和 type
        /*
        //可以替换成
        updateQueryBuilder.withIndexName("your index");
        updateQueryBuilder.withType("yourType");
        */
        updateQueryBuilder.withClass(class1);
        UpdateQuery build = updateQueryBuilder.build();
        return build;
    }

}
