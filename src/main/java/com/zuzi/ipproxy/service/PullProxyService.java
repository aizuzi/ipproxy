package com.zuzi.ipproxy.service;


import com.zuzi.ipproxy.dao.ProxyDao;
import com.zuzi.ipproxy.dao.UserDao;
import com.zuzi.ipproxy.model.ProxyModel;
import com.zuzi.ipproxy.model.User;
import com.zuzi.ipproxy.util.HttpClientGenerator;
import com.zuzi.ipproxy.util.Site;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
public class PullProxyService {

    private final Logger logger = LoggerFactory.getLogger(PullProxyService.class);

    private List<BasePullService> pullServices = new ArrayList<>();


    @Autowired
    private ProxyDao proxyRepository;

    public PullProxyService() {
        pullServices.add(new KuaidailiService());
    }

    public void pullProxy() {

        int size = pullServices.size();
        List<ProxyModel> proxyModels = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<ProxyModel> models = pullServices.get(i).onPull();
            if (models != null) {
                proxyModels.addAll(models);
            }
        }
        size = proxyModels.size();


        for (int i = size - 1; i >= 0; i--) {

            ProxyModel proxyModel = proxyModels.get(i);
            ProxyModel proxyModel1 = new ProxyModel();
            proxyModel1.setHost(proxyModel.getHost());
            if(proxyRepository.exists(Example.of(proxyModel1))){
                proxyModels.remove(i);
            }
        }

        if(proxyModels.size() > 0) {
            List<ProxyModel> saves = proxyRepository.saveAll(new Iterable<ProxyModel>() {
                @Override
                public Iterator<ProxyModel> iterator() {
                    return proxyModels.iterator();
                }
            });
        }
    }

}
