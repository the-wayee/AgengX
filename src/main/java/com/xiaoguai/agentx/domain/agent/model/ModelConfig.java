package com.xiaoguai.agentx.domain.agent.model;


/**
 * @Author: the-way
 * @Verson: v1.0
 * @Date: 2025-07-28 16:11
 * @Description: Agent模型配置类，用于表示大语言模型的相关配置参数
 */
public class ModelConfig {
    /**
     * 模型名称，如：gpt-4-0125-preview, claude-3-opus-20240229等
     */
    private String modelName;

    /**
     * 温度参数，范围0-2，值越大创造性越强，越小则越保守
     */
    private Double temperature;

    /**
     * Top P参数，范围0-1，控制输出的多样性
     */
    private Double topP;

    /**
     * 最大令牌数，控制生成的内容长度
     */
    private Integer maxTokens;

    /**
     * 是否启用记忆功能
     */
    private Boolean loadMemory;

    /**
     * 系统消息（仅对特定模型有效）
     */
    private String systemMessage;

    public ModelConfig() {
    }

    public ModelConfig(String modelName, Double temperature, Double topP, Integer maxTokens, Boolean loadMemory, String systemMessage) {
        this.modelName = modelName;
        this.temperature = temperature;
        this.topP = topP;
        this.maxTokens = maxTokens;
        this.loadMemory = loadMemory;
        this.systemMessage = systemMessage;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTopP() {
        return topP;
    }

    public void setTopP(Double topP) {
        this.topP = topP;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Boolean getLoadMemory() {
        return loadMemory;
    }

    public void setLoadMemory(Boolean loadMemory) {
        this.loadMemory = loadMemory;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public void setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
    }

    /**
     * 创建默认配置
     */
    public static ModelConfig createDefault() {
        ModelConfig config = new ModelConfig();
        config.setModelName("THUDM/GLM-4-9B-0414");
        config.setTemperature(0.7);
        config.setTopP(1.0);
        config.setMaxTokens(2000);
        config.setLoadMemory(true);
        return config;
    }
}
