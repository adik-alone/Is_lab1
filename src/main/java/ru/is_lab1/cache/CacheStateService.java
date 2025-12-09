package ru.is_lab1.cache;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
//import lombok.var;
import org.infinispan.stats.Stats;
import ru.is_lab1.config.InfinispanConfig;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.LinkedHashMap;
import java.util.Map;


@ApplicationScoped
public class CacheStateService {
    @Inject
    InfinispanConfig config;

//    private MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

    public Map<String, Object> getStat(){
        Stats stats = config.getCache().getAdvancedCache().getStats();
//        try{
//            ObjectName name = new ObjectName("my.infinispan:type=Cache,name=\"movieCache(local)\"," +
//                    "manager=\"DefaultCacheManager\",component=Statistics");

        Map<String, Object> result = new LinkedHashMap<>();

//            result.put("hits", mbs.getAttribute(name, "Hits"));
//            result.put("misses", mbs.getAttribute(name, "Misses"));
//            result.put("hitRatio", mbs.getAttribute(name, "HitRatio"));
//            result.put("stores", mbs.getAttribute(name, "Stores"));
//            result.put("retrievals", mbs.getAttribute(name, "Retrievals"));
//            result.put("evictions", mbs.getAttribute(name, "Evictions"));

//            result.put("hitRatio", stats.getHitRatio());
        result.put("hits", stats.getHits());
        result.put("misses", stats.getMisses());
        result.put("stores", stats.getStores());
        result.put("retrievals", stats.getRetrievals());
        result.put("evictions", stats.getEvictions());
        result.put("removeHits", stats.getRemoveHits());
        result.put("removeMisses", stats.getRemoveMisses());
        return result;

//        }catch (Exception e){
//            throw new RuntimeException(e);
//        }
    }
}
