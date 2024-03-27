package org.profit.avia.config;

import org.profit.avia.MainApplication;
import org.profit.avia.utils.OrmUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**Класс, запускающийся с запуском приложения
* */
@Component
public class InitApp implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(InitApp.class);

    @Autowired
    ApplicationContext applicationContext;

    @Value("${spring.core.run-as-test:false}")
    private boolean runAsTest;

    @Value("${spring.datasource.url}")
    private String url;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Init app ... run");
        logger.info("connecting to " + url);
        //Заполняем метаданные таблиц
        OrmUtils.fillTableMetadata("org.profit.avia");
        MainApplication.setApplicationContext(applicationContext);
        logger.info("Init app ... complete");
    }
}
