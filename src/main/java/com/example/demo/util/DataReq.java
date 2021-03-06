package com.example.demo.util;

import java.util.Arrays;

public class DataReq {
    //修改者
    private Integer puserid;
    //被修改者
    private Integer userid;

    private Integer[] ids;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Integer getPuserid() {
        return puserid;
    }

    public void setPuserid(Integer puserid) {
        this.puserid = puserid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "DataReq [puserid=" + puserid + ", userid=" + userid + ", ids=" + Arrays.toString(ids) + "]";
    }

   

   
}
