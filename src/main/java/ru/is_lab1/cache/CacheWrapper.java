package ru.is_lab1.cache;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ru.is_lab1.config.InfinispanConfig;
import ru.is_lab1.entity.Movie;

import static ru.is_lab1.config.InfinispanConfig.CACHE_SIZE;

@ApplicationScoped
public class CacheWrapper {
    @Inject
    InfinispanConfig config;

    @Inject
    CacheMetrics metrics;

    public Object get(String key){
        Object m = config.getCache().get(key);
        if (m != null) metrics.hit();
        else metrics.miss();
        return m;
    }

    public void put(String key, Object value){
        if (config.getCache().size() == CACHE_SIZE) metrics.evict();
        config.getCache().put(key, value);
        metrics.put();
    }
    public void clear(){
        config.getCache().clear();
        metrics.reset();
    }
}
