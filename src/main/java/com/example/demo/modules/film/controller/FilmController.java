package com.example.demo.modules.film.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.init.Post;
import com.example.demo.modules.controller.AbstractController;
import com.example.demo.modules.film.entity.Actor;
import com.example.demo.modules.film.entity.Film;
import com.example.demo.modules.film.entity.FilmInfo;
import com.example.demo.modules.film.entity.ReqInfo;
import com.example.demo.modules.film.service.ActorService;
import com.example.demo.modules.film.service.FilmService;
import com.example.demo.modules.user.controller.UserController;
import com.example.demo.modules.user.entity.User;
import com.example.demo.modules.user.service.UserService;
import com.example.demo.modules.util.MyUploadFile;
import com.example.demo.modules.util.PageResult;
import com.example.demo.modules.util.R;
import com.example.demo.modules.zgdream.PostController;
import com.example.demo.util.BusinessIdUtils;
import com.example.demo.util.EsUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import static com.example.demo.common.ResultStatus.*;
@Controller
@RequestMapping("/film")
public class FilmController extends AbstractController{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private FilmService filmService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActorService actorService;
    
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @RequestMapping("/index")
    public String filmindex(String name,Model model) {
        if(StringUtils.isNoneBlank(name)) {
            model.addAttribute("actorname",name);
        }
        return "film/index";
    }
    @RequestMapping("/mebmain")
    public String mebmain() {
        return "film/mebmain";
    }
    
    @RequestMapping("/main")
    public String filmMain(String name,Model model) {
        if(StringUtils.isNoneBlank(name)) {
            model.addAttribute("actorname",name);
        }
        return "film/main";
    }
    @RequestMapping("/actor")
    public String filmActor() {
        return "film/actor";
    }
    
    @RequestMapping("/memberfilminfo")
    public String memberfilminfo(String id,Model model) {
        model.addAttribute("filmId", id);
        return "film/memberfilminfo";
    }
    
    @RequestMapping("/list")
    @ResponseBody
    public R filmList(int pageno,int pagesize) {
        PageHelper.startPage(pageno, pagesize);
        List<Film> list = filmService.list();
        PageInfo<Film> page = new PageInfo<>(list);
        LOGGER.info("page : {}", page);
        LOGGER.info("list : {}", page.getList());
        LOGGER.info("pageNum : {}", page.getPageNum());
        LOGGER.info("pageSize:{}", page.getPageSize());
        LOGGER.info("total:{}", page.getTotal());
        PageResult pageResult = PageResult.pageResult(page);
        return R.page(pageResult).put("list", list);
    }
    @RequestMapping("/filmList")
    @ResponseBody
    public R listFilm(String queryText,String actorname,@PageableDefault Pageable pageable) {
        LOGGER.info("queryText:{}", queryText); LOGGER.info("actorname:{}", actorname);
        QueryBuilder query = null;SearchQuery squery = null;
        boolean indexExists = elasticsearchTemplate.indexExists(Film.class);
        if(!indexExists) {
            return R.ok("数据为空");
        }
            if(StringUtils.isBlank(queryText) && StringUtils.isBlank(actorname)) {
                query = QueryBuilders.matchAllQuery();
            }else {
                if(StringUtils.isNoneBlank(queryText)) {
                    query = QueryBuilders.queryStringQuery(queryText);
                }else if(StringUtils.isNoneBlank(actorname)){
                    LOGGER.info("actorname---:{}", actorname);
                    query = QueryBuilders.matchPhraseQuery("actor", actorname);
                }
            }
            squery = new NativeSearchQueryBuilder().
                    withQuery(query).
                    withPageable(pageable).
                    build();
            AggregatedPage<Film> page = elasticsearchTemplate.queryForPage(squery, Film.class);
            PageResult pageResult = PageResult.pageResult(page);
            LOGGER.info("film:{}", page.getContent());
            return R.page(pageResult).put("list", page.getContent());
        
      
    }
    
   
    @RequestMapping("/add")
    public ModelAndView add(Film film,@RequestParam("files") MultipartFile[] files){
        
         List<String> upload_image = MyUploadFile.upload_image(files);
         film.setId(BusinessIdUtils.genFilmId());
         film.setPicture(upload_image.get(0));
         film.setCreateTime(new Date());
         film.setCreateUserId(getUserId());
         film.setCreateUserName(userService.getById(getUserId()).getUsername());
         LOGGER.info("film:{}", film);
         IndexQuery query = new IndexQueryBuilder().
                 withObject(film).
                 build();
        String index = elasticsearchTemplate.index(query);
         LOGGER.info("index:{}", index);
        ModelAndView mv = new ModelAndView("redirect:/film/index");
        return mv;
    }
    
    
    @RequestMapping("/del")
    @ResponseBody
    public R delFilm(String id) {
        LOGGER.info("id:{}", id);
        if(StringUtils.isBlank(id)) {
            boolean index = elasticsearchTemplate.deleteIndex(Film.class);
            boolean indext = elasticsearchTemplate.deleteIndex(FilmInfo.class);
            LOGGER.info("index:{}", index); LOGGER.info("index:{}", indext);
        }else {
            SearchQuery query = new NativeSearchQueryBuilder().
                    withQuery(QueryBuilders.
                            matchQuery("id", id)).
                    build();
            List<Film> list = elasticsearchTemplate.queryForList(query, Film.class);
            LOGGER.info("list:{}", list);
            Film film = null;
            if(list != null && list.size()>0) {
                 film = list.get(0);
                 if(film.getCreateUserId() != getUserId()) {
                     return R.error(PERMISSION_ERROR.getMsg());
                 }
                 //刪除電影信息
                 SearchQuery queryInfo = new NativeSearchQueryBuilder().
                         withQuery(QueryBuilders.
                                 matchQuery("id", id)).
                         build();
                 List<Film> listInfo = elasticsearchTemplate.queryForList(queryInfo, Film.class);
                 if(listInfo != null && listInfo.size()>0) {
                     //查詢子類
                     SearchQuery querys = new NativeSearchQueryBuilder().
                             withQuery(QueryBuilders.termQuery("filmId", id)).
                             build();
                     AggregatedPage<FilmInfo> page = elasticsearchTemplate.queryForPage(querys, FilmInfo.class);
                     List<FilmInfo> content = page.getContent();
                     if(content != null && content.size()>0) {
                         return R.error(FILM_INFOS_NOT_EMPTY.getMsg());
                     }
                 }
            }
            String delete = elasticsearchTemplate.delete("film", "film", id);
            LOGGER.info("delete:{}", delete);
        }
          return R.ok(SUCCESS.getMsg());
      }
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "film/add";
    }
    @RequestMapping("/toAddInfo")
    public String toAddInfo(String id,Model model) {
        model.addAttribute("filmId",id);
        LOGGER.info("filmId:{}", id);
        return "film/addinfo";
    }
    
    @RequestMapping("/addInfos")
    @ResponseBody
    public R addInfos(ReqInfo infos) {
        
        List<FilmInfo> list = infos.getList();
        if(list != null && list.size()>0) {
            int count = 0;String filmId = infos.getFilmId();
            ArrayList<IndexQuery> queries = new ArrayList<>();
            for (FilmInfo info : list) {
                info.setHot(0);
                info.setFilmId(filmId);
                info.setCreateUserId(getUserId());
                info.setId(BusinessIdUtils.genFilmInfoId());
                User user = userService.getById(getUserId());
                info.setCreateUserName(user.getUsername());
                IndexQuery query = new IndexQuery();
                query.setId(info.getId());
                query.setObject(info);
                query.setIndexName("filminfo");
                query.setType("filminfo");
                queries.add(query);
                if(count % 5 == 0) {
                    elasticsearchTemplate.bulkIndex(queries);
                    queries.clear();
                }
                count ++;
            }
            if(queries.size()>0) {
                elasticsearchTemplate.bulkIndex(queries);
            }
        }
        return R.ok();
    }
    
    @RequestMapping("/filmInfo")
    public String filmInfo( String filmid,Model model) {
        model.addAttribute("filmId", filmid);
        return "film/filminfo";
    }
    
    @RequestMapping("/filmInfoList")
    @ResponseBody
    public R filmInfoList(String filmId, Pageable pageable) {
        SearchQuery query = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.
                        matchQuery("id", filmId)).
                build();
        List<Film> list = elasticsearchTemplate.queryForList(query, Film.class);
        LOGGER.info("list:{}", list);
        Film film = null;
        if(list != null && list.size()>0) {
             film = list.get(0);
          /*   if(film.getCreateUserId() != getUserId()) {
                 return R.error(PERMISSION_ERROR.getMsg());
             }*/
             film.setHot(film.getHot()+1);
             Map<String, Object> params = new HashMap<>();
             // 其中某一个属性
             params.put("hot", film.getHot());
             UpdateRequest updateRequest = new UpdateRequest();
             updateRequest.doc(params);
             UpdateQueryBuilder updateQueryBuilder = new UpdateQueryBuilder();
             updateQueryBuilder.withId(film.getId());
             updateQueryBuilder.withUpdateRequest(updateRequest);
             updateQueryBuilder.withClass(Film.class);
             UpdateQuery build = updateQueryBuilder.build();
             UpdateResponse update1 = elasticsearchTemplate.update(build);
           //  EsUtil.filedValueAdd(film.getId(), Film.class, params);
        }
        //不对传来的值分词，去找完全匹配的 
        SearchQuery querys = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.matchQuery("filmId", filmId)).
                withPageable(pageable).
                build();
        AggregatedPage<FilmInfo> page = elasticsearchTemplate.queryForPage(querys, FilmInfo.class);
        PageResult pageResult = PageResult.pageResult(page);
        LOGGER.info("film:{}", page.getContent());
        return R.page(pageResult).put("list", page.getContent());
    }
    @RequestMapping("/delInfo")
    @ResponseBody
    public R delInfo(String id) {
        LOGGER.info("id:{}", id);
            SearchQuery query = new NativeSearchQueryBuilder().
                    withQuery(QueryBuilders.
                            matchQuery("id", id)).
                    build();
            List<FilmInfo> list = elasticsearchTemplate.queryForList(query, FilmInfo.class);
            LOGGER.info("list:{}", list);
            FilmInfo info = null;String filmId = "";
            if(list != null && list.size()>0) {
                 info = list.get(0);
                 filmId = info.getFilmId();
                 if(info.getCreateUserId() != getUserId()) {
                     return R.error(PERMISSION_ERROR.getMsg());
                 }
            }
            String delete = elasticsearchTemplate.delete("filminfo", "filminfo", id);
            LOGGER.info("delete:{}", delete);
          return R.ok(SUCCESS.getMsg()).put("id", filmId);
    }
    
    @RequestMapping("/actorlist")
    @ResponseBody
    public R actorlist(int page,int size,String queryText) {
        LOGGER.info("actor name:{}", queryText);
        QueryWrapper<Actor> wrapper = new QueryWrapper<>();
        if(StringUtils.isNoneBlank(queryText)) {
           wrapper.like("name", queryText);
        }
        
        PageHelper.startPage(page,size);
        List<Actor> list = actorService.list(wrapper);
        PageInfo<Actor> pages = new PageInfo<>(list);
        LOGGER.info("actor pages:{}", pages);
        PageResult pageResult = PageResult.pageResult(pages);
        return R.page(pageResult).put("list", list);
    }
    
    @RequestMapping("/actordel")
    @ResponseBody
    public R actordel(int id) {
        LOGGER.info("actor id:{}", id);
        Actor actor = actorService.getById(id);
        SearchQuery querys = new NativeSearchQueryBuilder().
                withQuery(QueryBuilders.termQuery("name", actor.getName())).
                build();
        AggregatedPage<FilmInfo> page = elasticsearchTemplate.queryForPage(querys, FilmInfo.class);
        List<FilmInfo> list = page.getContent();
        if(list != null && list.size()>0) {
            return R.error(FILM_INFOS_NOT_EMPTY.getMsg());
        }
        boolean byId = actorService.removeById(id);
        LOGGER.info("del actor byId:{}", byId);
        return R.ok();
    }
    @RequestMapping("/toAddActor")
    public String toAddActor() {
        return "film/addactor";
    }
    

    @RequestMapping("/addactor")
    public ModelAndView addActor(Actor actor,@RequestParam("files") MultipartFile[] files){
        
         List<String> upload_image = MyUploadFile.upload_image(files);
         actor.setPicture(upload_image.get(0));
         actor.setDebutTime(new Date());
         actor.setCreateUserId(getUserId());
         actor.setCreateUserName(userService.getById(getUserId()).getUsername());
         LOGGER.info("actor:{}", actor);
         boolean save = actorService.save(actor);
         LOGGER.info("save actor:{}", save);
        ModelAndView mv = new ModelAndView("redirect:/film/actor");
        return mv;
    }
}
