//package com.code.edu.config;
//
//import org.apache.catalina.connector.Connector;
//import org.apache.coyote.http11.Http11NioProtocol;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
//import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import java.io.File;
//import java.io.IOException;
//
//@Configuration
//public class HttpsConfig {
//
//    @Value("${https.port}")
//    private Integer port;
//
//    @Value("${https.ssl.key-store-password}")
//    private String key_store_password;
//
//    @Value("${https.ssl.key-password}")
//    private String key_password;
//
//    @Value("${https.ssl.store-name}")
//    private String store_name;
//
//    @Value("${spring.profiles.active}")
//    private String active;
//
//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//        tomcat.addAdditionalTomcatConnectors(createSslConnector()); // 添加http
//        return tomcat;
//    }
//
//    private Connector createSslConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
//        /*File truststore = new ClassPathResource("sample.jks").getFile();*/
//        connector.setScheme("https");
//        connector.setSecure(true);
//        connector.setPort(port);
//        protocol.setSSLEnabled(true);
//        try {
//            protocol.setKeystorePass(key_store_password);
//            protocol.setKeyPass(key_password);
//            if("pro".equals(active)){
//                store_name = "/root/.jenkins/workspace/edu-code/target/edu-0.0.1-SNAPSHOT/WEB-INF/classes/" + store_name;
//                protocol.setKeystoreFile(store_name);
//            }else {
//                File keystore = new ClassPathResource(store_name).getFile();
//                protocol.setKeystoreFile(keystore.getAbsolutePath());
//            }
//        } catch (IOException ex) {
//            throw new IllegalStateException("can't access keystore: [" + "keystore"
//                    + "] or truststore: [" + "keystore" + "]", ex);
//        }
////        }
//        return connector;
//    }
////    @Bean
////    @LoadBalanced
////    public RestTemplate restTemplate() {
////        return new RestTemplate();
////    }
//}
