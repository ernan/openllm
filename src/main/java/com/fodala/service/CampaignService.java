package com.fodala.service;

import com.fodala.pojo.Campaign;
import com.fodala.pojo.CampaignHistory;

import java.util.List;

public interface CampaignService {

    List<Campaign> all();

    Campaign findById(Integer id);

    void insert(Campaign campaign);

    void update(Campaign campaign);

    void delete(Integer id);

    List<CampaignHistory> history(Integer id);

    Campaign createEmpty();
}
