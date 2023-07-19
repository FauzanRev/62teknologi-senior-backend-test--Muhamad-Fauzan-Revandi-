package com.teknologi.senior.backend.test.serviceimpl;

import com.teknologi.senior.backend.test.config.Tools;
import com.teknologi.senior.backend.test.model.dto.request.RequestBody;
import com.teknologi.senior.backend.test.model.entity.Business;
import com.teknologi.senior.backend.test.repository.BusinessRepository;
import com.teknologi.senior.backend.test.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Locale;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessRepository repo;

    @Autowired
    Tools tools;

    @Override
    public List<Business> getListBusiness(){

        return repo.findAll();
    }

    @Override
    public Boolean createdBusiness(Business body) {
       Integer max= repo.checkMaxId();

        Business business = new Business(
                max+ 1,
                body.getBusiness_id(),
                body.getName(),
                body.getAddress(),
                body.getCity(),
                body.getState(),
                body.getPostal_code(),
                body.getLatitude(),
                body.getLongitude(),
                body.getStars(),
                body.getReview_count(),
                body.getIs_open(),
                body.getCategories()
        );
        repo.save(business);
        return true;
    }

    @Override
    public Boolean updateBusinessById(Integer id, Business body) {
        Business business = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Business not found"));

        business.setBusiness_id(body.getBusiness_id());
        business.setName(body.getName());
        business.setAddress(body.getAddress());
        business.setCity(body.getCity());
        business.setState(body.getState());
        business.setPostal_code(body.getPostal_code());
        business.setLatitude(body.getLatitude());
        business.setLongitude(body.getLongitude());
        business.setStars(body.getStars());
        business.setReview_count(body.getReview_count());
        business.setIs_open(body.getIs_open());
        business.setCategories(body.getCategories());

        // Save the updated Business object back to the repository
        repo.save(business);

        return true;
    }

    @Override
    public Boolean deleteBusinessById(Integer id) {
        repo.deleteById(id);
        return true;
    }

    @Override
    public List<Business> getListBusinessBySearch(String body) throws Exception {
        RequestBody req = tools.convertStringToObject(body,RequestBody.class);
        String formated = "";
        if (req.getOpenNow()){
            formated = "1";
        }


        List<Business> businessList = repo.getListBySearch(req.getTerm(), req.getCategories(), formated, req.getCountryCode(), req.getSortByRating());

        return businessList;
    }

}
