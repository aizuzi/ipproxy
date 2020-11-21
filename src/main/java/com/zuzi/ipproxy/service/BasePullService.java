package com.zuzi.ipproxy.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.List;

public abstract class BasePullService implements BasePull{

    protected Connection setDefaultData(Connection connection) {
        return connection.userAgent(userAgent)
                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                ;
    }

    protected Connection getConnection(String url) {
        Connection connection = Jsoup.connect(url);
        setDefaultData(connection);
        return connection;
    }

}
