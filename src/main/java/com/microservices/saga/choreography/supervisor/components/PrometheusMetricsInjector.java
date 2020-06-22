package com.microservices.saga.choreography.supervisor.components;

import io.prometheus.client.CollectorRegistry;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import io.prometheus.client.exporter.MetricsServlet;

@Component
public class PrometheusMetricsInjector {
    @Bean
    public ServletRegistrationBean injectPrometheusServlet() {
        var targetUrl = "/metrics-prometheus";
        var prometheusServlet = new MetricsServlet(CollectorRegistry.defaultRegistry);
        return new ServletRegistrationBean(prometheusServlet, targetUrl);
    }
}
