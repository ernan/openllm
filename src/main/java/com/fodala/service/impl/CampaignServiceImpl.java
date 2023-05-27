package com.fodala.service.impl;

import com.fodala.mapper.CampaignMapper;
import com.fodala.pojo.Campaign;
import com.fodala.pojo.CampaignHistory;
import com.fodala.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private CampaignMapper campaignMapper;

    @Override
    public List<Campaign> all() {
        return campaignMapper.all();
    }

    @Override
    public Campaign findById(Integer id) {
        return campaignMapper.findById(id);
    }

    @Override
    public void insert(Campaign Campaign) {
        campaignMapper.insert(Campaign);
    }

    @Override
    public void update(Campaign campaign) {
        campaignMapper.update(campaign);
    }

    @Override
    public void delete(Integer id) {
        campaignMapper.delete(id);
    }

    @Override
    public List<CampaignHistory> history(Integer id) {
        return campaignMapper.history(id);
    }

    @Override
    public Campaign createEmpty() {
        Random r = new Random();
        Campaign Campaign = new Campaign();
        String name = "Campaign" + r.nextInt(10000);
        Campaign.setName(name);
        return Campaign;
    }

}