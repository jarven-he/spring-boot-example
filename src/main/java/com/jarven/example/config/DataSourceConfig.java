package com.jarven.example.config;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-04-23 22:57
 */
@Configuration
@ServletComponentScan
@EnableTransactionManagement
@EntityScan(value = "com.jarven.example.domain")
@EnableJpaRepositories(basePackages = {"com.jarven.example.repository"})
public class DataSourceConfig {

    @Value(value = "${redis.host}")
    private String redisHost;

    @Value(value = "${redis.port}")
    private Integer redisPort;

    /**
     * 连接池
     *
     * @return 连接池
     */
    @Bean("duridDatasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        var filter = new Slf4jLogFilter();
        filter.setConnectionLoggerName("sql");
        filter.setStatementExecutableSqlLogEnable(true);
        var druidDataSource = new DruidDataSource();
        druidDataSource.setProxyFilters(Collections.singletonList(filter));
        return druidDataSource;
    }

    /**
     * redis 客户端
     *
     * @return redis客户端
     */
    @Bean
    public RedissonClient redissonClient() {
        var config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redisHost + ":" + redisPort);
        return Redisson.create(config);
    }
}
