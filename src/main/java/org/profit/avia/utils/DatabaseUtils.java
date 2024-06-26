package org.profit.avia.utils;

import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseUtils {
    public static void setSequenceValue(String sequence, Integer value){
        JdbcTemplate jdbcTemplate = OrmUtils.getJDBCTemplate();
        String sql = "ALTER SEQUENCE %s RESTART WITH %d";
        jdbcTemplate.execute(String.format(sql, sequence, value));
    }

    public static Integer getSequenceNextValue(String sequence){
        JdbcTemplate jdbcTemplate = OrmUtils.getJDBCTemplate();
        String sql = "SELECT nextval(%s) ";
        return jdbcTemplate.queryForObject(String.format(sql, sequence), Integer.class);
    }
}
