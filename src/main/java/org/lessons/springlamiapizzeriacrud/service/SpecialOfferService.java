package org.lessons.springlamiapizzeriacrud.service;

import org.lessons.springlamiapizzeriacrud.model.SpecialOffer;
import org.lessons.springlamiapizzeriacrud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {

    @Autowired
    SpecialOfferRepository specialOfferRepository;

    public SpecialOffer create(SpecialOffer formSpecialOffer) {
        return specialOfferRepository.save(formSpecialOffer);
    }
}