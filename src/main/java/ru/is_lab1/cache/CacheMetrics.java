package ru.is_lab1.cache;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class CacheMetrics {

    private final AtomicLong hits = new AtomicLong();
    private final AtomicLong misses = new AtomicLong();
    private final AtomicLong puts = new AtomicLong();
    private final AtomicLong evictions = new AtomicLong();

    public void hit(){ hits.incrementAndGet();}
    public void miss(){ misses.incrementAndGet();}
    public void put(){ puts.incrementAndGet();}
    public void evict(){ evictions.incrementAndGet();}

    public Map<String, Object> getStats(){
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("hits", hits.get() / 2);
        m.put("misses", misses.get());
        m.put("hitRatio", (hits.get() / 2) + misses.get() == 0 ? 0 :
                (double) (hits.get() / 2) / (hits.get() / 2 + misses.get()));
        m.put("puts", puts.get());
        m.put("evictions", evictions.get());
        return m;
    }

    public void reset(){
        hits.set(0);
        misses.set(0);
        puts.set(0);
        evictions.set(0);
    }
}
