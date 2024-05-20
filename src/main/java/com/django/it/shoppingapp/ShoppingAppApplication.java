package com.django.it.shoppingapp;

import com.django.it.shoppingapp.dtos.requests.RegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;



@EnableJpaAuditing
@EnableAsync
@SpringBootApplication
public class ShoppingAppApplication {




	private static final Logger LOG = LoggerFactory.getLogger(ShoppingAppApplication.class);

	private static final String HTTP_DEFAULT_PORT = "8080";

	public static void main(String[] args) throws UnknownHostException {
		final Environment env = SpringApplication.run(ShoppingAppApplication.class, args).getEnvironment();
		logApplicationStartup(env);
	}


	private static void logApplicationStartup(final Environment env)throws UnknownHostException {
		String protocol ="http";
		if (env.getProperty("server.ssl.key-store") != null){
			protocol = "https";
		}
		final String serverPort = Optional.ofNullable(env.getProperty("server.port")).orElse(HTTP_DEFAULT_PORT);
		String contextPath = env.getProperty("server.servlet.context-path");
		if (!StringUtils.hasText(contextPath)){
			contextPath = "/";
		}

		final String hostAddress = InetAddress.getLocalHost().getHostAddress();

		LOG.info("""
                        \t
                        --------------------------------------------------------
                        \tApplication '{} ({})' is running!
                        \tAccess URLs:
                        \tLocal: \t\t{}: //localhost:{}{}
                        \tExternal: \t{}: //{}:{}{}
                        \tProfile(s): \t{}
                        -----------------------------------------------------------
                        \t
                        """,
				env.getProperty("spring.application.name"), env.getProperty("application.version"), //
				protocol, serverPort, contextPath, protocol, hostAddress, //
				serverPort, contextPath, env.getActiveProfiles());
	}


}
