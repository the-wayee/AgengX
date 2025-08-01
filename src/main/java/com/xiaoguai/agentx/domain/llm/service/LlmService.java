package com.xiaoguai.agentx.domain.llm.service;


import com.xiaoguai.agentx.domain.llm.callback.StreamResponseHandler;
import com.xiaoguai.agentx.domain.llm.model.LlmRequest;
import com.xiaoguai.agentx.domain.llm.model.LlmResponse;

import java.util.List;

/**
 * @Author: the-way
 * @Verson: v1.0
 * @Date: 2025-07-23 20:40
 * @Description: LlmService
 */
public interface LlmService {


    /**
     * 聊天
     */
    LlmResponse chat(LlmRequest request);

    /**
     * 流式输出(假)
     */
    List<String> chatStreamList(LlmRequest request);

    /**
     * 流式输出
     */ 
    void chatStream(LlmRequest request, StreamResponseHandler callback);
    /**
     * 获取供应商名称
     */
    String getProviderName();

    /**
     * 获取默认模型
     */
    String getDefaultModel();
}
