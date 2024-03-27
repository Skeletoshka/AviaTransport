package org.profit.avia.repository;

import org.profit.avia.model.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class FlightRepository implements TableRepository<Flight>{

    private static Logger logger = LoggerFactory.getLogger(FlightRepository.class);

    /*Методы для работы с таблицей Flight*/
}
