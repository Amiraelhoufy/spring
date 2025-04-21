package org.agcodes.eazyschool.repository;

import java.util.List;
import org.agcodes.eazyschool.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HolidaysRepository {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public HolidaysRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Holiday> findAllHolidays() {
    String sql = "SELECT * FROM HOLIDAYS";
    // using BeanPropertyRowMapper instead of RowMapper
    // when the column names match the property names
    var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
    return jdbcTemplate.query(sql, rowMapper);
  }

}
