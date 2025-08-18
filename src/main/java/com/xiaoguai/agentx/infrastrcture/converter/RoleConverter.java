package com.xiaoguai.agentx.infrastrcture.converter;

import com.xiaoguai.agentx.domain.conversation.constants.Role;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 模型类型转换器
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Role.class)
public class RoleConverter extends BaseTypeHandler<Role> {
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Role parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.name());
    }
    
    @Override
    public Role getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return value == null ? null : Role.fromCode(value);
    }
    
    @Override
    public Role getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return value == null ? null : Role.fromCode(value);
    }
    
    @Override
    public Role getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return value == null ? null : Role.fromCode(value);
    }
} 