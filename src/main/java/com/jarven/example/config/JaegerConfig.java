package com.jarven.example.config;

import com.uber.jaeger.Configuration;
import com.uber.jaeger.samplers.ConstSampler;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-07-28 19:37
 */
@Component
public class JaegerConfig {

    @Value("${server.name}")
    private String serviceName;

    @Bean
    public Tracer jaegerTracer() {
        var senderConfiguration = new Configuration.SenderConfiguration();
        var reporterConfiguration = new Configuration.ReporterConfiguration()
                .withSender(senderConfiguration)
                .withLogSpans(false)
                .withMaxQueueSize(1000)
                .withFlushInterval(100);
        var samplerConfiguration = new Configuration
                .SamplerConfiguration()
                .withType(ConstSampler.TYPE).withParam(1);
        var configuration = new Configuration(serviceName)
                .withReporter(reporterConfiguration)
                .withSampler(samplerConfiguration);
        return configuration.getTracer();
    }
}
