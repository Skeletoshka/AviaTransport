package org.profit.avia.repository;

import org.profit.avia.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyRepository implements TableRepository<Company>{

    private static Logger logger = LoggerFactory.getLogger(CompanyRepository.class);

    /*Методы для работы с таблицей company*/
}
