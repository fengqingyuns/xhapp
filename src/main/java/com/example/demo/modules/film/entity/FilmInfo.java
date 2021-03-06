package com.example.demo.modules.film.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
//index类似于数据库，使用type允许我们在一个index中存储多种类型的数据,indexStoreType文件存储类型：fs:默认实现，取决于操作系统
//replicas副本数量默认为1,refreshInterval:多久执行一次刷新操作，使得最近的索引更改对搜索可见。默认是1秒。设置为-1表示禁止刷新。
@Document(indexName="filminfo",type="filminfo",indexStoreType = "fs",shards = 5,replicas=1,refreshInterval="-1")
public class FilmInfo {
    @Id
    private String id;
    
    private String filmId;
    
    private int createUserId;
    
    private String createUserName;
    
    private String actor;
    
    private String size;
    
    private String magnet;
    
    private int hot;

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMagnet() {
        return magnet;
    }

    public void setMagnet(String magnet) {
        this.magnet = magnet;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @Override
    public String toString() {
        return "FilmInfo [id=" + id + ", filmId=" + filmId + ", createUserId=" + createUserId + ", createUserName="
                + createUserName + ", actor=" + actor + ", size=" + size + ", magnet=" + magnet + ", hot=" + hot + "]";
    }

    

    
}
