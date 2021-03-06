package com.example.demo.modules.zgdream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.init.Post;
import com.example.demo.modules.util.R;

@RestController
@RequestMapping("/post")
public class PostController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    /**
     * 
     * @author lit
     * @desc:  单字符串查询，默认排序。将从所有字段中查找包含传来的word分词后字符串的数据集 
     * @date:  2020年12月31日 下午8:25:36  
     *
     * @param word
     * @param pageable
     * @return
     */
    @RequestMapping("/single")
    public R singleTitle(String word,@PageableDefault Pageable pageable) {
        LOGGER.info("pageable:{}", pageable);
        //构建searchQuery 
        SearchQuery searchQuery = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.queryStringQuery(word)).
                withPageable(pageable).
                build();
        List<Post> list = elasticsearchTemplate.queryForList(searchQuery, Post.class);
        LOGGER.info("list:{}", list);
        return R.ok().put("list", list);
    }
    /**
     * 
     * @author lit
     * @desc:   按照weight从大到小排序 
     * @date:  2020年12月31日 下午8:39:39  
     * @请求:   http://192.168.1.10:8081/post/singleSort?word="浣溪沙"&size=2&page=0    page为第几页  
     * @param word
     * @param pageable
     * @return
     */
    @RequestMapping("/singleSort")
    public R singleSort(String word,@PageableDefault(sort = "weight",direction = Sort.Direction.DESC) Pageable pageable) {
        LOGGER.info("pageable:{}", pageable);
        SearchQuery query = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.queryStringQuery(word)).
                withPageable(pageable).
                build();
        AggregatedPage<Post> page = elasticsearchTemplate.
                queryForPage(query, Post.class);
        LOGGER.info("page:{}", page);
        return R.ok().put("page",page );
    }
    /**
     * 
     * @author lit
     * @desc: 单字符串模糊查询
     * @date:  2020年12月31日 下午8:55:07  
     *
     * @param content
     * @param userId
     * @param pageable
     * @return
     */
    @RequestMapping("/singleMach")
    public R singleMacth(String content,Integer userId,@PageableDefault Pageable pageable) {
        SearchQuery query = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.
                        matchQuery("content", content)).
                withQuery(QueryBuilders.matchQuery("userId", userId)).
                withPageable(pageable).
                build();
        AggregatedPage<Post> page = elasticsearchTemplate.queryForPage(query, Post.class);
        LOGGER.info("page:{}", page);
        return R.ok().put("page", page);
    }
    /**
     * 
     * @author lit
     * @desc: 单字段对某短语进行匹配查询
     * 和match查询类似，match_phrase查询首先解析查询字符串来产生一个词条列表。
     * 然后会搜索所有的词条，但只保留包含了所有搜索词条的文档，并且词条的位置要邻接。
     * 一个针对短语“中华共和国”的查询不会匹配“中华人民共和国”，
     * 因为没有含有邻接在一起的“中华”和“共和国”词条。
     *这种完全匹配比较严格，类似于数据库里的“%落日熔金%”这种，使用场景比较狭窄。
     *如果我们希望能不那么严格，譬如搜索“中华共和国”，
     *希望带“我爱中华人民共和国”的也能出来，就是分词后，中间能间隔几个位置的也能查出来，可以使用slop参数
     * @date:  2020年12月31日 下午9:23:50  
     * @param slop 代表分词移动一个词条几次可以匹配
     * @param content
     * @param pageable
     * @return
     */
    @RequestMapping("/phrase")
    public R singlePhraseMatch(String content,@PageableDefault Pageable pageable) {
        
        SearchQuery query = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.matchPhraseQuery("content", content).slop(2)).
                withPageable(pageable).
                build();
        AggregatedPage<Post> page = elasticsearchTemplate.queryForPage(query, Post.class);
        
        return R.ok().put("page", page);
    }
    /**
     * 
     * @author lit
     * @desc:  term匹配，即不分词匹配，你传来什么值就会拿你传的值去做完全匹配
     * 我们可以用这个来做那种需要==查询的操作，当传userId=1时，会查询出来所有userId为1的集合。
     * 通常情况下，我们不会使用term查询，绝大部分情况我们使用ES的目的就是为了使用它的分词模糊查询功能。
     * 而term一般适用于做过滤器filter的情况，譬如我们去查询title中包含“浣溪沙”且userId=1时，
     * 那么就可以用termQuery("userId", 1)作为查询的filter。
     * @date:  2021年1月1日 下午6:10:45  
     *
     * @param userId
     * @param pageable
     * @return
     */
    @RequestMapping("/term")
    public R singleTerm(Integer userId,@PageableDefault Pageable pageable) {
        //不对传来的值分词，去找完全匹配的 
        SearchQuery query = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.termQuery("userId", userId)).
                withPageable(pageable).
                build();
        AggregatedPage<Post> page = elasticsearchTemplate.queryForPage(query, Post.class);
        
        return R.ok().put("page",page);
    }
    /**
     * 
     * @author lit
     * @desc:  如果我们希望title，content两个字段去匹配传过来的某个字符串，只要任何一个字段包括该字符串即可，就可以使用multimatch
     * @date:  2021年1月1日 下午6:15:59  
     *
     * @param title
     * @param pageable
     * @return
     */
    @RequestMapping("/multi")
    public R multiMacth(String title, @PageableDefault Pageable pageable) {
        
        SearchQuery query = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.multiMatchQuery(title, "content","title")).
                withPageable(pageable).
                build();
        AggregatedPage<Post> page = elasticsearchTemplate.queryForPage(query, Post.class);
        LOGGER.info("page :{}", page);
        return R.ok().put("page", page);
    }
    /**
     * 
     * @author lit
     * @desc:   之前的查询中，当我们输入“我天”时，ES会把分词后所有包含“我”和“天”的都查询出来，
     * 如果我们希望必须是包含了两个字的才能被查询出来，那么我们就需要设置一下Operator。
     * @date:  2021年1月1日 下午6:35:11  
     *
     * @param content
     * @param pageable
     * @return
     */
    @RequestMapping("/contain")
    public R contain(String content,@PageableDefault Pageable pageable) {
        
        SearchQuery query = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.matchQuery("content", content).
                        operator(MatchQueryBuilder.DEFAULT_OPERATOR.AND)).
                withPageable(pageable).
                build();
        AggregatedPage<Post> page = elasticsearchTemplate.queryForPage(query, Post.class);
        
        return R.ok().put("post", page);
    }
    /**
     * 
     * @author lit
     * @desc: 无论是matchQuery(单字符串模糊查询)，multiMatchQuery(前台传过来的字符串和title或content其中一个匹配上即可)，queryStringQuery等，都可以设置operator。
     * 默认为Or，设置为And后，就会把符合包含所有输入的才查出来。
     * 如果是and的话，譬如用户输入了5个词，但包含了4个，也是显示不出来的。我们可以通过设置精度来控制。
     * @date:  2021年1月1日 下午6:48:22  
     *
     * @param content
     * @param pageable
     * @return
     */
    @RequestMapping("/minimumShouldMatch")
    public R minimumShouldMatch(String content,@PageableDefault Pageable pageable) {
        
        SearchQuery query = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.matchQuery("content", content).
                        operator(MatchQueryBuilder.DEFAULT_OPERATOR.AND).
                        minimumShouldMatch("75%")).
                build();
        AggregatedPage<Post> page = elasticsearchTemplate.queryForPage(query, Post.class);
        return R.ok().put("page", page);
    }
    /**
     * 
     * @author lit
     * @desc: 合并查询即boolQuery，可以设置多个条件的查询方式。
     * 它的作用是用来组合多个Query，有四种方式来组合，must，mustnot，filter，should。
     * must代表返回的文档必须满足must子句的条件，会参与计算分值；
     * filter代表返回的文档必须满足filter子句的条件，但不会参与计算分值；
     * should代表返回的文档可能满足should子句的条件，也可能不满足，
     * 有多个should时满足任何一个就可以，通过minimum_should_match设置至少满足几个。
     * mustnot代表必须不满足子句的条件。譬如我想查询title包含“XXX”，
     * 且userId=“1”，且weight最好小于5的结果。那么就可以使用boolQuery来组合。
     * @date:  2021年1月2日 下午5:50:07  
     *
     * @param title
     * @param userId
     * @param weight
     * @param pageable
     * @return
     */
    @RequestMapping("/bool")
    public R bool(String title,Integer userId,Integer weight,@PageableDefault Pageable pageable) {
        
        SearchQuery query = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.boolQuery().
                        must(QueryBuilders.
                                matchQuery("title", title)).
                        must(QueryBuilders.
                                termQuery("userId", userId)).
                        should(QueryBuilders.
                                rangeQuery("weight").lt(weight))).
                withPageable(pageable).
                build();
        AggregatedPage<Post> page = elasticsearchTemplate.queryForPage(query, Post.class);
        return R.ok().put("page", page);
    }
    /**
     * 
     * @author lit
     * @desc:  如果某个字段需要匹配多个值，譬如userId为1，2，3任何一个的，类似于mysql中的in，
     * 那么可以使用termsQuery("userId", ids).
如果某字段是字符串，我建议空的就设置为null，不要为""空串，貌似某些版本的ES，使用matchQuery空串会不生效
     * @date:  2021年1月2日 下午7:08:29  
     *
     * @param userIdOne
     * @param userIdTwo
     * @return
     */
    @RequestMapping("/terms")
    public R termsMacth(Integer userIdOne,Integer userIdTwo) {
        Integer[] userId = {userIdOne,userIdTwo};
        SearchQuery query = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.
                        termsQuery("userId", userId)).
                build();
        List<Post> list = elasticsearchTemplate.queryForList(query, Post.class);
        return R.ok().put("list", list);
    }
    
    public static <T>UpdateQuery filedValueAdd(String id, Class<T> clazz, HashMap<String,Object> params) {
        //构建UpdataRequest对象
        UpdateRequest updateRequest = new UpdateRequest();
        //设置参数
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
        updateQueryBuilder.withClass(clazz);
        UpdateQuery build = updateQueryBuilder.build();
        return build;
    }

} 

