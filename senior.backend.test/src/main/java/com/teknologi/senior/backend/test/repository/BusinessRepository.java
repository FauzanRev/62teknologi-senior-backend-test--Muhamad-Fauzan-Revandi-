package com.teknologi.senior.backend.test.repository;

import com.teknologi.senior.backend.test.model.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, Integer> {
    @Query(value = """
            SELECT ISNULL(MAX(id), 0) FROM business;
            """, nativeQuery = true)
    Integer checkMaxId();


    @Query(value = """
            DECLARE @term VARCHAR(256) = ?1
            DECLARE @categories VARCHAR(256) = ?2
            DECLARE @openNow bit =?3
            DECLARE @countrycode char(2) =?4
            Declare @sortbyrating varchar(6) =?5
                        
                        
            if(@term = '' AND @categories ='' AND @openNow ='' AND @countrycode ='' AND @sortbyrating = '')
            BEGIN
            SELECT *
            FROM business
            ORDER BY stars asc
            END
                        
            else if(@term !='' AND @categories ='' AND @openNow ='' AND @countrycode ='' AND @sortbyrating = '')
            BEGIN
            SELECT *
            FROM business
            WHERE (@term = '' OR name LIKE '%' + @term + '%')\s
            END
                        
            else if(@categories !='' AND @term ='' AND @openNow ='' AND @countrycode ='' AND @sortbyrating = '')
            BEGIN
            SELECT *
            FROM business
            WHERE (@categories = '' OR categories LIKE '%' + @categories + '%')\s
            END
                        
            else if(@openNow !='' AND @categories ='' AND @term ='' AND @countrycode ='' AND @sortbyrating = '')
            BEGIN
            SELECT *
            FROM business
            WHERE (@openNow = null OR is_open = @openNow )\s
            END
                        
            else if(@countrycode !='' AND @categories ='' AND @term ='' AND @openNow ='' AND @sortbyrating = '')
            BEGIN
            SELECT *
            FROM business
            WHERE (@countrycode = '' OR  state LIKE '%' + @countrycode + '%' )\s
            END
                        
            else IF (@sortbyrating = 'desc' AND @categories = '' AND @term = '' AND @openNow = '' AND @countrycode = '')
            BEGIN
                SELECT *
                FROM business
                order by stars desc
            END
            """,nativeQuery = true)
    List<Business> getListBySearch(String term,String categories,String opennow,String countrycode,String sortbyrating);
}
