package org.agcodes.eazyschool.repository;

import jakarta.transaction.Transactional;
import java.util.List;
import org.agcodes.eazyschool.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

  List<Contact> findByStatus(String status);
  // Dynamic Pagination
  /* ============== JPQL - Custom Query ==============*/
  /* JPQL: Even the query doesn't provide order By
   the JPA will use the pageable and provide a default order*/
  /* ============== Native SQL - Custom Query ==============*/
//  @Query("SELECT c from Contact c WHERE c.status = :status")

  /* As we're using the Native query I had to update the sorting column for mobileNum to "mobile_num" to match my db column*/
  @Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status" ,nativeQuery = true)
  Page<Contact> findByStatus(@Param("status") String state, Pageable pageable);

 @Transactional
 @Modifying
 @Query("UPDATE Contact c SET c.status = ?2 WHERE c.contactId = ?1")
// @Query(value = "UPDATE contact_msg c SET c.status = :status WHERE contact_id= :contactId",nativeQuery = true)
 int updateStatusById(@Param("contactId") int contactId,@Param("status") String status);

  /* ========== Using @NameQuery - JPQL ========= */
  Page<Contact> findOpenMsgs(@Param("status") String status, Pageable pageable);
  @Transactional
  @Modifying
 int updateMsgStatus(int contactId,String status);

  /* ========== Using @NameNativeQuery - Native ========= */
  @Query(nativeQuery = true)
  Page<Contact> findOpenMsgsNative(@Param("status") String status, Pageable pageable);
  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int updateMsgStatusNative(int contactId,String status);

}
