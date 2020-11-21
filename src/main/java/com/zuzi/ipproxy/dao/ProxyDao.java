package com.zuzi.ipproxy.dao;

import com.zuzi.ipproxy.model.ProxyModel;
import com.zuzi.ipproxy.model.User;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProxyDao extends JpaRepository<ProxyModel,Long> {

//    @SQLInsert(sql = "")
//    int aaa();
}