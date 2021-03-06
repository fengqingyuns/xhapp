package com.example.demo.modules.film.entity;

import java.util.List;

public class ReqInfo {

    private String filmId;
    
    private List<FilmInfo> infos;

    public List<FilmInfo> getList() {
        return infos;
    }

    public void setList(List<FilmInfo> infos) {
        this.infos = infos;
    }

    public List<FilmInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<FilmInfo> infos) {
        this.infos = infos;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    @Override
    public String toString() {
        return "ReqInfo [filmId=" + filmId + ", infos=" + infos + "]";
    }

   
  
    
}
