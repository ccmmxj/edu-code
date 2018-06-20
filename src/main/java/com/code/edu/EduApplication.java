package com.code.edu;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.File;
import java.io.IOException;

@EnableWebMvc
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableAutoConfiguration
@MapperScan(basePackages = "com.code.edu.mapper")
public class EduApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
//		SpringApplication.run(EduApplication.class, args);
		new SpringApplicationBuilder(EduApplication.class).web(true).run(args);
	}
	@Value("${https.port}")
	private Integer port;

	@Value("${https.ssl.key-store-password}")
	private String key_store_password;

	@Value("${https.ssl.key-password}")
	private String key_password;

	@Value("${https.ssl.store-name}")
	private String store_name;
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		tomcat.addAdditionalTomcatConnectors(createSslConnector()); // 添加http
		return tomcat;
	}
	private Connector createSslConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
//		try {
//			File keystore = new ClassPathResource(store_name).getFile();
            /*File truststore = new ClassPathResource("sample.jks").getFile();*/
			connector.setScheme("https");
			connector.setSecure(true);
			connector.setPort(port);
			protocol.setSSLEnabled(true);
//			protocol.setKeystoreFile(keystore.getAbsolutePath());
			protocol.setKeystorePass(key_store_password);
			protocol.setKeyPass(key_password);
			return connector;
//		}
//		catch (IOException ex) {
//			throw new IllegalStateException("can't access keystore: [" + "keystore"
//					+ "] or truststore: [" + "keystore" + "]", ex);
//		}
	}
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
}
