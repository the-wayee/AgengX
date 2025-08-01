package com.xiaoguai.agentx.domain.conversation.service;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xiaoguai.agentx.application.conversation.assembler.SessionAssembler;
import com.xiaoguai.agentx.infrastrcture.exception.BusinessException;
import com.xiaoguai.agentx.domain.conversation.dto.SessionDTO;
import com.xiaoguai.agentx.domain.conversation.model.SessionEntity;
import com.xiaoguai.agentx.domain.conversation.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: the-way
 * @Verson: v1.0
 * @Date: 2025-08-02 14:20
 * @Description: SessionDomainService
 */
@Service
public class SessionDomainService {

    private final SessionRepository sessionRepository;

    public SessionDomainService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /**
     * 创建会话
     */
    public SessionDTO createSession(String agentId, String userId) {
        SessionEntity session = new SessionEntity();
        session.setAgentId(agentId);
        session.setUserId(userId);
        session.setTitle("新会话");
        sessionRepository.insert(session);
        return SessionAssembler.toDTO(session);
    }

    /**
     * 更新会话信息
     */
    public void updateSession(String sessionId, String userId, String title) {
        SessionEntity session = new SessionEntity();
        session.setId(sessionId);
        session.setUserId(userId);
        session.setTitle(title);

        sessionRepository.update(session, Wrappers.<SessionEntity>lambdaUpdate()
                .eq(SessionEntity::getId, sessionId).eq(SessionEntity::getUserId, userId));
    }

    /**
     * 删除会话
     */
    public boolean deleteSession(String sessionId, String userId) {
        return sessionRepository.delete(Wrappers.<SessionEntity>lambdaQuery()
                .eq(SessionEntity::getId, sessionId).eq(SessionEntity::getUserId, userId)) > 0;

    }

    /**
     * 根据agentId 获取会话列表
     */
    public List<SessionDTO> getSessionsByAgentId(String agentId, String userId) {
        List<SessionEntity> sessions = sessionRepository.selectList(Wrappers.<SessionEntity>lambdaQuery()
                .eq(SessionEntity::getAgentId, agentId)
                .eq(SessionEntity::getUserId, userId)
                .orderByDesc(SessionEntity::getCreatedAt));
        return sessions.stream().map(SessionAssembler::toDTO).collect(Collectors.toList());
    }

    /**
     * 检查会话是否存在
     */
    public void checkSessionExist(String id, String userId) {
        SessionEntity session = sessionRepository.selectOne(Wrappers.<SessionEntity>lambdaQuery()
                .eq(SessionEntity::getId, id).eq(SessionEntity::getUserId, userId));
        if (session == null) {
            throw new BusinessException("会话不存在");
        }
    }

    /**
     * 获取单个会话
     */
    public SessionEntity find(String sessionId, String userId) {
        SessionEntity session = sessionRepository.selectOne(Wrappers.<SessionEntity>lambdaQuery()
                .eq(SessionEntity::getId, sessionId).eq(SessionEntity::getUserId, userId));

        return session;
    }

    /**
     * 批量删除会话
     */
    public void deleteSessions(List<String> sessionIds) {
        sessionRepository.delete(Wrappers.<SessionEntity>lambdaQuery()
                .in(SessionEntity::getId, sessionIds));
    }
}
