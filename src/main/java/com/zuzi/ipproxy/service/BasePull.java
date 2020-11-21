package com.zuzi.ipproxy.service;

import com.zuzi.ipproxy.model.ProxyModel;

import java.util.List;

public interface BasePull {
    String userAgent ="Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0";

    //拉取
    List<ProxyModel> onPull();

}
