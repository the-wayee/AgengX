package com.xiaoguai.agentx.application.conversation.service;


import com.xiaoguai.agentx.domain.conversation.model.SessionDTO;
import com.xiaoguai.agentx.domain.conversation.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: the-way
 * @Verson: v1.0
 * @Date: 2025-07-25 11:33
 * @Description: 会话应用服务，用于适配域层的会话服务
 */
@Service
public class SessionAppService {

    private final SessionService sessionService;

    public SessionAppService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * 创建新会话
     */
    public SessionDTO createSession(String title, String userId, String description) {
        return sessionService.createSession(title, userId, description);
    }

    /**
     * 获取会话信息
     */
    public SessionDTO getSession(String sessionId) {
        return sessionService.getSession(sessionId);
    }

    /**
     * 获取用户的所有会话
     */
    public List<SessionDTO> getUserSessions(String userId) {
        return sessionService.getUserSessions(userId);
    }

    /**
     * 获取用户的活跃会话
     */
    public List<SessionDTO> getUserActiveSessions(String userId) {
        return sessionService.getUserActiveSessions(userId);
    }

    /**
     * 获取用户的归档会话
     */
    public List<SessionDTO> getUserArchivedSessions(String userId) {
        return sessionService.getUserArchivedSessions(userId);
    }

    /**
     * 更新会话信息
     */
    public SessionDTO updateSession(String sessionId, String title, String description) {
        return sessionService.updateSession(sessionId, title, description);
    }

    /**
     * 归档会话
     */
    public SessionDTO archiveSession(String sessionId) {
        return sessionService.archiveSession(sessionId);
    }

    /**
     * 恢复归档会话
     */
    public SessionDTO activeSession(String sessionId) {
        return sessionService.activeSession(sessionId);
    }

    /**
     * 删除会话
     */
    public void deleteSession(String sessionId) {
        sessionService.deleteSession(sessionId);
    }

    /**
     * 搜索会话
     */
    public List<SessionDTO> searchSessions(String userId, String keyword) {
        return sessionService.searchSessions(userId, keyword);
    }
}
