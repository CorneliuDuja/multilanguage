package md.zamolxis.multilanguage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "md.zamolxis.multilanguage")
public class MultilanguageInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MultilanguageInitializer.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MultilanguageInitializer.class, args);
	}

}
