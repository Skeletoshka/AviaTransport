package org.profit.avia.repository;

import org.profit.avia.model.Airport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AirportRepository implements TableRepository<Airport>{

    private static Logger logger = LoggerFactory.getLogger(AirportRepository.class);

    /*Методы для работы с таблицей Airport*/
}
