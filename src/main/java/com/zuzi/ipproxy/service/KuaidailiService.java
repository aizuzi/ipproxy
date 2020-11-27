package com.zuzi.ipproxy.service;


import com.zuzi.ipproxy.model.ProxyModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class KuaidailiService extends BasePullService {

    public List<ProxyModel> test() {
        List<ProxyModel> proxyModels = new ArrayList<>();

        proxyModels.add(createProxyModel("61.135.185.156", "80"));

        return proxyModels;
    }

    private ProxyModel createProxyModel(String h, String p) {
        ProxyModel proxyModel = new ProxyModel();
        proxyModel.setHost(h);
        proxyModel.setPort(p);
        proxyModel.setType("kuaidaili");
        proxyModel.setStatus(0);

        return proxyModel;
    }
    @Override
    public List<ProxyModel> onPull() {
//        if(1+1==2) {
//            return test();
//        }
        try {
            Document d = getConnection("https://www.kuaidaili.com/free/")
                    .get();
            String html = d.body().html();

            JXDocument underTest = JXDocument.create(html);
            String xpath = "//*[@id=\"list\"]/table/tbody/tr";

            List<JXNode> trs = underTest.selN(xpath);
            List<ProxyModel> proxyModels = new ArrayList<>();
            int size = trs.size();
            for (int i = 0; i < size; i++) {
                JXNode ips = underTest.selNOne("//*[@id=\"list\"]/table/tbody/tr[" + (i + 1) + "]/td[1]");
                JXNode ports = underTest.selNOne("//*[@id=\"list\"]/table/tbody/tr[" + (i + 1) + "]/td[2]");
                if (ips == null || ports == null) {
                    continue;
                }

                ProxyModel proxyModel = new ProxyModel();
                proxyModel.setHost(ips.asElement().text());
                proxyModel.setPort(ports.asElement().text());
                proxyModel.setType("kuaidaili");
                proxyModel.setStatus(0);

                proxyModels.add(proxyModel);
            }

            return proxyModels;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
