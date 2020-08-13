package com.example.demo.persistence;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository
		extends JpaRepository<URLEntity, Long> {

	@Query(value = "SELECT u FROM URLEntity u WHERE u.url = ?1")
	Optional<URLEntity> findByURL(String url);

	@Query(value = "SELECT u FROM URLEntity u WHERE u.hashcode = ?1")
	Optional<URLEntity> findByHashCode(String hashcode);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM TBL_URL WHERE TO_TIMESTAMP(created_date,'YYYY-MM-DD hh:mm:ss') "
			+ "<= DATEADD(MINUTE, -30,CURRENT_TIMESTAMP)", nativeQuery = true)
	void deleteOldUrls();
}
