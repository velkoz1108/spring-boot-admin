package com.wangtao.quartztask;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

/**
 * @author : wangtao
 * @date : 2018/12/20 10:01  星期四
 */

@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {
    /**
     * 查询
     *
     * @param id
     * @return
     */
    @Query(value = "select id ,cron from config where id = ?1 ", nativeQuery = true)
    public Config findOne(long id);
}
