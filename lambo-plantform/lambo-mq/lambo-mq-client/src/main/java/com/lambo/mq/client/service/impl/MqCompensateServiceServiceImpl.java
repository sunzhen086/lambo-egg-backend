package com.lambo.mq.client.service.impl;

import com.lambo.mq.client.dao.api.MqCompensateMapper;
import com.lambo.mq.client.model.MqCompensate;
import com.lambo.mq.client.service.api.MqCompensateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MqCompensateServiceService实现
 *
 */
@Service("mqCompensateService")
public class MqCompensateServiceServiceImpl implements MqCompensateService {

    @Autowired
    MqCompensateMapper mqCompensateMapper;

    @Override
    public void insertMqCompenstate(MqCompensate mqCompensate) {
        mqCompensateMapper.insert(mqCompensate);
    }
}
