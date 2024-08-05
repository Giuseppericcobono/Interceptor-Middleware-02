package co.develhope.interceptor2.services;


import co.develhope.interceptor2.entities.Month;
import co.develhope.interceptor2.repositories.MonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonthService {

    @Autowired
    private MonthRepository monthRepository;

    public Optional<Month> getMonthByID(Integer id) {
        return monthRepository.findById(id);
    }
}
