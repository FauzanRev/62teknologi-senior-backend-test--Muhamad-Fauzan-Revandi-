package com.teknologi.senior.backend.test.service;

import com.teknologi.senior.backend.test.model.entity.Business;
import org.springframework.data.crossstore.ChangeSetPersister;


import java.util.List;

public interface BusinessService {
    List<Business> getListBusiness() throws ChangeSetPersister.NotFoundException;

    Boolean createdBusiness(Business body);

    Boolean updateBusinessById(Integer id, Business body);

    Boolean deleteBusinessById(Integer id);

    List<Business> getListBusinessBySearch(String body) throws Exception;
}
