package com.fodala.mapper;

import com.fodala.pojo.Campaign;
import com.fodala.pojo.CampaignHistory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignMapper {

    List<Campaign> all();

    Campaign findById(@Param("id") Integer id);

    void insert(@Param("campaign") Campaign campaign);

    void update(@Param("campaign") Campaign campaign);

    void delete(@Param("id") Integer id);

    List<CampaignHistory> history(@Param("id") Integer id);
}
