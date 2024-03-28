package org.profit.avia.security;

import org.profit.avia.repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Autowired
    private CompanyRepository companyRepository;

    public static String createToken() {
        return UUID.randomUUID().toString();
    }

    public String getUserNameFromJwtToken(String token) {
        String userName = companyRepository.getCompanyNameByToken(token);
        if(userName != null){
            return userName;
        }else{
            throw new RuntimeException("Пользователь не найден");
        }
    }

    public boolean validateJwtToken(String authToken) {
        return getUserNameFromJwtToken(authToken) != null;
    }
}
