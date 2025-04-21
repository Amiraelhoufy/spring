package org.agcodes.eazyschool.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import org.agcodes.eazyschool.model.Contact;
import org.agcodes.eazyschool.rowmappers.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ContactRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int saveContactMsg(Contact contact) {
    String sql = "INSERT INTO CONTACT_MSG(NAME,MOBILE_NUM,EMAIL,SUBJECT,MESSAGE,STATUS,CREATED_AT,CREATED_BY) "
        + "values(?,?,?,?,?,?,?,?)";
    return jdbcTemplate.update(sql, contact.getName(), contact.getMobileNum(),contact.getEmail()
        ,contact.getSubject(), contact.getMessage(),contact.getStatus()
        ,contact.getCreatedAt(),contact.getCreatedBy());
  }

  public List<Contact> findMsgsWithOpenStatus(String status) {
    String sql = "SELECT * FROM CONTACT_MSG WHERE STATUS = ?";
    return jdbcTemplate.query(sql, new PreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, status);
      }
    },new ContactRowMapper());
}

  public int updateMsgStatus(int contactId, String status, String updatedBy) {
    String sql = "UPDATE CONTACT_MSG SET STATUS = ?,UPDATED_AT = ?,UPDATED_BY = ? WHERE CONTACT_ID = ?";
    return jdbcTemplate.update(sql, new PreparedStatementSetter() {
      @Override
      public void setValues(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, status);
        preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
        preparedStatement.setString(3, updatedBy);
        preparedStatement.setInt(4, contactId);
      }
    });
  }
}
