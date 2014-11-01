package com.ktenas.orestis.p03078.fuelstationfinder.elastic;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
    
    @Bean
    public Client getESClient() {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "keevosh-es").build();
        
        Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("ezekiel", 9300));
        return client;
    }
}