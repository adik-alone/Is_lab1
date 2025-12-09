package ru.is_lab1.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.Produces;
import org.infinispan.Cache;
//import org.infinispan.commons.api.CacheContainerAdmin;
import org.infinispan.commons.marshall.JavaSerializationMarshaller;
import org.infinispan.configuration.ConfigurationManager;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.cache.StorageType;
import org.infinispan.configuration.global.GlobalAuthorizationConfiguration;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.CacheContainer;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import ru.is_lab1.entity.Movie;

import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class InfinispanConfig {

    public static final Long CACHE_SIZE = 10L;

    private DefaultCacheManager cacheManager;
    private Cache<String, Object> cache;

    @PostConstruct
    public void init() {
        GlobalConfigurationBuilder global = new GlobalConfigurationBuilder()
                .nonClusteredDefault();
//        global.globalJmxStatistics().enable().jmxDomain("my.infinispan");
//        global.jmx().enable(true).domain("my.infinispan");


        cacheManager = new DefaultCacheManager(global.build());
        ConfigurationBuilder builder = new ConfigurationBuilder();

        builder
//                .memory().size(10000)
                .memory().maxCount(10)
                .expiration().lifespan(1, TimeUnit.MINUTES)
                .statistics().enable().enabled(true)
//                .jmxStatistics().enable()
                .clustering().cacheMode(CacheMode.LOCAL);

        cacheManager.defineConfiguration("movieCache", builder.build());
        cache = cacheManager.getCache("movieCache");
    }

    public Cache<String, Object> getCache() {
        return cache;
    }

    @PreDestroy
    public void destroy() {
        cacheManager.stop();
    }
}