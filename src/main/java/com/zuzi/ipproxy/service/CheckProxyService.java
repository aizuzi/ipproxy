package com.zuzi.ipproxy.service;


import com.zuzi.ipproxy.dao.ProxyDao;
import com.zuzi.ipproxy.model.ProxyModel;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
public class CheckProxyService extends BasePullService {

    private final Logger logger = LoggerFactory.getLogger(CheckProxyService.class);

//    private List<BasePullService> pullServices = new ArrayList<>();


    @Autowired
    private ProxyDao proxyRepository;

    public CheckProxyService() {
    }

    public void checkProxy() {

        ProxyModel selectModel = new ProxyModel();
        selectModel.setStatus(0);

        List<ProxyModel> proxyModels = proxyRepository.findAll(Example.of(selectModel));

        int size = proxyModels.size();

        List<ProxyModel> successId = new ArrayList<>();
        List<ProxyModel> failId = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ProxyModel proxyModel = proxyModels.get(i);
            try {
                System.err.println("check  h "+proxyModel.getHost()+" p "+Integer.valueOf(proxyModel.getPort()));
                Document d = getConnection("http://baidu.com")
                        .proxy(proxyModel.getHost(), Integer.valueOf(proxyModel.getPort()))
                        .get();
                if (d != null && d.body() != null) {
                    //成功
                    proxyModel.setStatus(1);

                    successId.add(proxyModel);

                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            proxyModel.setStatus(2);
            failId.add(proxyModel);
        }

        if(failId != null) {
            proxyRepository.saveAll(new Iterable<ProxyModel>() {
                @Override
                public Iterator<ProxyModel> iterator() {
                    return failId.iterator();
                }
            });
        }

        if(successId != null) {
            proxyRepository.saveAll(new Iterable<ProxyModel>() {
                @Override
                public Iterator<ProxyModel> iterator() {
                    return successId.iterator();
                }
            });
        }
        System.err.println("failId " + failId.size()+" successId "+successId.size() + "  size " + size);

    }

    @Override
    public List<ProxyModel> onPull() {
        return null;
    }
}
