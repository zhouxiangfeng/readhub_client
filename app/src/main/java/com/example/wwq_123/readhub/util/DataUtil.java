package com.example.wwq_123.readhub.util;

import com.example.wwq_123.readhub.model.bean.DataItem;
import com.example.wwq_123.readhub.model.bean.JobDataItem;
import com.example.wwq_123.readhub.model.bean.NewsDataItem;
import com.example.wwq_123.readhub.model.bean.TopicDataItem;
import com.example.wwq_123.readhub.model.retrofit.bean.BlockchainData;
import com.example.wwq_123.readhub.model.retrofit.bean.Data;
import com.example.wwq_123.readhub.model.retrofit.bean.JobData;
import com.example.wwq_123.readhub.model.retrofit.bean.NewsData;
import com.example.wwq_123.readhub.model.retrofit.bean.TechData;
import com.example.wwq_123.readhub.model.retrofit.bean.TopicData;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    public List<? extends DataItem> extractData(Data data,int type){
        List<? extends DataItem> list;
        if (type==0){
            TopicData topicData = (TopicData)data;
            list = extractTopic(topicData);
        }else if (type==1){
            NewsData newsData = (NewsData)data;
            list = extractNews(newsData);
        }else if (type==2){
            TechData techData = (TechData)data;
            list = extractTech(techData);
        }else if (type==3){
            BlockchainData blockchain = (BlockchainData)data;
            list = extractBlockchain(blockchain);
        }else {
            JobData jobData = (JobData)data;
            list = extractJob(jobData);
        }
        return list;
    }
    //从topic中提取所需的数据
    public List<TopicDataItem> extractTopic(TopicData topic){
        List<TopicDataItem> list = new ArrayList<>();
        for (TopicData.DataBean bean: topic.getData()) {
            TopicDataItem item = new TopicDataItem();
            item.setUrl(bean.getId());
            item.setCreatedAt(bean.getCreatedAt());
            item.setOrder(bean.getOrder());
            item.setPublishDate(bean.getPublishDate());
            item.setSummary(bean.getSummary());
            item.setTitle(bean.getTitle());
            item.setUpdatedAt(bean.getUpdatedAt());
            item.setTimeline(bean.getTimeline());
            List<NewsDataItem> newsDataItemList = new ArrayList<>();
            for (TopicData.DataBean.NewsArrayBean newsBean:bean.getNewsArray()) {
                NewsDataItem newsItem = new NewsDataItem();
                newsItem.setId(newsBean.getId());
                newsItem.setTitle(newsBean.getTitle());
                newsItem.setUrl(newsBean.getUrl());
                newsItem.setMobileUrl(newsBean.getMobileUrl());
                newsItem.setSiteName(newsBean.getSiteName());
                newsItem.setAuthorName(newsBean.getAuthorName());
                newsItem.setPublishDate(newsBean.getPublishDate());
                newsItem.setSiteSlug(newsBean.getSiteSlug());
                newsDataItemList.add(newsItem);
            }
            list.add(item);
        }
        return list;
    }
    //从news中提取所需的数据
    public List<NewsDataItem> extractNews(NewsData news){
        List<NewsDataItem> list = new ArrayList<>();
        for (NewsData.DataBean bean:news.getData()) {
            NewsDataItem item = new NewsDataItem();
            item.setId(bean.getId());
            item.setTitle(bean.getTitle());
            item.setSummary(bean.getSummary());
            item.setSummaryAuto(bean.getSummaryAuto());
            item.setUrl(bean.getUrl());
            item.setMobileUrl(bean.getMobileUrl());
            item.setSiteName(bean.getSiteName());
            item.setAuthorName(bean.getAuthorName());
            item.setLanguage(bean.getLanguage());
            item.setPublishDate(bean.getPublishDate());
            item.setSiteSlug(bean.getSiteSlug());
            list.add(item);
        }
        return list;
    }
    //从tech中提取所需的数据
    public List<DataItem> extractTech(TechData tech){
        List<DataItem> list = new ArrayList<>();
        for (TechData.DataBean bean:tech.getData()) {
            DataItem item = new DataItem();
            item.setId(bean.getId());
            item.setTitle(bean.getTitle());
            item.setSummary(bean.getSummary());
            item.setSummaryAuto(bean.getSummaryAuto());
            item.setUrl(bean.getUrl());
            item.setMobileUrl(bean.getMobileUrl());
            item.setSiteName(bean.getSiteName());
            item.setAuthorName(bean.getAuthorName());
            item.setLanguage(bean.getLanguage());
            item.setPublishDate(bean.getPublishDate());
            list.add(item);
        }
        return list;
    }

    public List<DataItem> extractBlockchain(BlockchainData blockchain){
        List<DataItem> list = new ArrayList<>();
        for (BlockchainData.DataBean bean:blockchain.getData()) {
            DataItem item = new DataItem();
            item.setId(bean.getId());
            item.setTitle(bean.getTitle());
            item.setSummary(bean.getSummary());
            item.setSummaryAuto(bean.getSummaryAuto());
            item.setUrl(bean.getUrl());
            item.setMobileUrl(bean.getMobileUrl());
            item.setSiteName(bean.getSiteName());
            item.setAuthorName(bean.getAuthorName());
            item.setLanguage(bean.getLanguage());
            item.setPublishDate(bean.getPublishDate());
            list.add(item);
        }
        return list;
    }

    public List<JobDataItem> extractJob(JobData job){
        List<JobDataItem> list = new ArrayList<>();
        for (JobData.DataBean bean:job.getData()) {
            JobDataItem item = new JobDataItem();
            item.setId(bean.getId());
            item.setUuid(bean.getUuid());
            item.setJobTitle(bean.getJobTitle());
            item.setJobCount(bean.getJobCount());
            item.setCompanyCount(bean.getCompanyCount());
            item.setSalaryLower(bean.getSalaryLower());
            item.setSalaryUpper(bean.getSalaryUpper());
            item.setExperienceLower(bean.getExperienceLower());
            item.setExperienceUpper(bean.getExperienceUpper());
            item.setPublishDate(bean.getPublishDate());
            item.setCreatedAt(bean.getCreatedAt());
            List<JobDataItem.JobsArrayBean> beanList = new ArrayList<>();
            for (JobData.DataBean.JobsArrayBean arrayBean:bean.getJobsArray()) {
                JobDataItem.JobsArrayBean array = new JobDataItem.JobsArrayBean();
                array.setId(arrayBean.getId());
                array.setCity(arrayBean.getCity());
                array.setCompany(arrayBean.getCompany());
                array.setUrl(arrayBean.getUrl());
                array.setTitle(arrayBean.getTitle());
                array.setSponsor(arrayBean.isSponsor());
                array.setSiteName(arrayBean.getSiteName());
                array.setSalaryLower(arrayBean.getSalaryLower());
                array.setSalaryUpper(arrayBean.getSalaryUpper());
                array.setExperienceLower(arrayBean.getExperienceLower());
                array.setExperienceUpper(arrayBean.getExperienceUpper());
                beanList.add(array);
            }
            item.setJobsArray(beanList);
            list.add(item);
        }
        return list;
    }
}