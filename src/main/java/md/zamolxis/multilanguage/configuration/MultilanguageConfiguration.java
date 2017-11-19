package md.zamolxis.multilanguage.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import md.zamolxis.multilanguage.service.SettingsService;

@Configuration
@ComponentScan(basePackages = "md.zamolxis.multilanguage")
@PropertySource("classpath:multilanguage.properties")
@EnableJpaRepositories(basePackages = "md.zamolxis.multilanguage", entityManagerFactoryRef = "entityManagerFactoryBean")
public class MultilanguageConfiguration {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "spring.datasource.driverClassName";
	private static final String PROPERTY_NAME_DATABASE_URL = "spring.datasource.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "spring.datasource.username";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "spring.datasource.password";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_HIBERNATE_HBM_AUTO = "hibernate.hbm2ddl.auto";

	private static final String PROPERTY_NAME_SETTINGS_TIMESTAMP_MIN = "settings.timestamp.min";
	private static final String PROPERTY_NAME_SETTINGS_TIMESTAMP_MAX = "settings.timestamp.max";
	private static final String PROPERTY_NAME_SETTINGS_TIMESTAMP_DIFF = "settings.timestamp.diff";
	private static final String PROPERTY_NAME_SETTINGS_LATENCY_DAYS = "settings.latency.days";

	@Resource
	private Environment environment;

	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		return dataSource;
	}

	@Bean
	public JpaTransactionManager transactionManager() throws ClassNotFoundException, PropertyVetoException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		transactionManager.setNestedTransactionAllowed(true);
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
			throws ClassNotFoundException, PropertyVetoException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("md.zamolxis.multilanguage.entity");
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		Properties properties = new Properties();
		properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
				environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		properties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL,
				environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
		properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
				environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		if (environment.containsProperty(PROPERTY_NAME_HIBERNATE_HBM_AUTO)) {
			properties.put(PROPERTY_NAME_HIBERNATE_HBM_AUTO,
					environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM_AUTO));
		}
		entityManagerFactoryBean.setJpaProperties(properties);
		return entityManagerFactoryBean;
	}

	@Bean
	public SettingsService settingsService() {
		SettingsService settingsService = new SettingsService();
		settingsService
				.setTimestampMin(Long.parseLong(environment.getRequiredProperty(PROPERTY_NAME_SETTINGS_TIMESTAMP_MIN)));
		settingsService
				.setTimestampMax(Long.parseLong(environment.getRequiredProperty(PROPERTY_NAME_SETTINGS_TIMESTAMP_MAX)));
		settingsService.setTimestampDiff(
				Long.parseLong(environment.getRequiredProperty(PROPERTY_NAME_SETTINGS_TIMESTAMP_DIFF)));
		settingsService
				.setLatencyDays(Integer.parseInt(environment.getRequiredProperty(PROPERTY_NAME_SETTINGS_LATENCY_DAYS)));
		return settingsService;
	}

}
