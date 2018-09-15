package pl.coderslab;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.coderslab")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.coderslab.Repository")
public class AppConfig extends WebMvcConfigurerAdapter {
  @Bean
  public LocalEntityManagerFactoryBean entityManagerFactory() {
    LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
    emfb.setPersistenceUnitName("Warsztaty_W_6");
    return emfb;
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
    JpaTransactionManager tm = new JpaTransactionManager(emf);
    return tm;
  }

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }

  @Bean
  public Validator validator() {
    return new LocalValidatorFactoryBean();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }
}
