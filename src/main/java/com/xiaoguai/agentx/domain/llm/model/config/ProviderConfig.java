package com.xiaoguai.agentx.domain.llm.model.config;


/**
 * @Author: the-way
 * @Verson: v1.0
 * @Date: 2025-08-12 20:34
 * @Description: 服务商配置
 */
public class ProviderConfig {
    private String apiKey;
    private String baseUrl;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
