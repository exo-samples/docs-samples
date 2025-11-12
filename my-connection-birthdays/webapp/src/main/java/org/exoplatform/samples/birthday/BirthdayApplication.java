package org.exoplatform.samples.birthday;

import io.meeds.spring.AvailableIntegration;
import io.meeds.spring.kernel.PortalApplicationContextInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(scanBasePackages = {
    BirthdayApplication.MODULE_NAME,
    AvailableIntegration.KERNEL_MODULE,
    AvailableIntegration.WEB_MODULE,
}, exclude = {
    LiquibaseAutoConfiguration.class,
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class,
    HibernateJpaAutoConfiguration.class
})
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-common.properties")

public class BirthdayApplication extends PortalApplicationContextInitializer {
  public static final String MODULE_NAME = "org.exoplatform.samples";

}
