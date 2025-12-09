package ru.is_lab1.cache;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.infinispan.Cache;
import org.infinispan.manager.CacheContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.config.InfinispanConfig;
import ru.is_lab1.entity.Movie;

import java.util.List;

@Dependent
@Interceptor
@L2Cached
public class L2CacheInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(L2CacheInterceptor.class);
//    @Inject
//    InfinispanConfig config;
//    private boolean isInit = false;
    @Inject
    CacheWrapper wrapper;

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception{
        logger.info("Intercept class ===> {}", ctx.proceed().getClass());
        logger.info("Intercept method ===> {}", ctx.getMethod());

//        if (ctx.getMethod().equals(""))

        String key = buildKey(ctx);

        if (wrapper.get(key) != null) return wrapper.get(key);
        Object m = ctx.proceed();
        if (m != null) wrapper.put(key, m);
        return m;
    }

    private String buildKey(InvocationContext ctx){
        StringBuilder sb = new StringBuilder();
        sb.append(ctx.getTarget().getClass().getSimpleName())
                .append(".")
                .append(ctx.getMethod().getName());

        for (Object param: ctx.getParameters()){
            sb.append(":").append(param);
        }
        return sb.toString();
    }
}
