package ru.is_lab1.service;

import com.alibaba.druid.pool.DruidDataSource;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.SQLException;

@ApplicationScoped
public class DruidMonitoringService {
//    @Resource(lookup = "java:jboss/datasource/MyPostgresDS")
//    @Resource(lookup = "java:/jdbc/DruidPostgresDS")

//    @Inject
//    DruidConfig config;
    @Resource(lookup = "java:/jdbc/DruidPostgresDS")
    private DataSource dataSource;

//    @Inject
//    DruidDataSource druid;


    public String getDruidStats() throws SQLException {
//        if (dataSource instanceof DruidDataSource){
            DruidDataSource druid = (DruidDataSource) dataSource;
//            druid.getConnection();

            StringBuilder stats = new StringBuilder();

            stats.append("Druid Connection Pool Statistics:\n");
            stats.append("================================\n");
            stats.append("Active Connections: ").append(druid.getActiveCount()).append("\n");
            stats.append("Idle Connections: ").append(druid.getPoolingCount()).append("\n");
            stats.append("Max Active: ").append(druid.getMaxActive()).append("\n");
            stats.append("Wait Thread Count: ").append(druid.getWaitThreadCount()).append("\n");
            stats.append("Total Connections: ").append(druid.getConnectCount()).append("\n");
            stats.append("Error Count: ").append(druid.getErrorCount()).append("\n");
//
//            druid.getConnection().close();
            return stats.toString();
//            return """
//                Active: %d
//                Idle: %d
//                Max Active: %d
//                Wait Threads: %d
//                Total Created: %d
//                Error Count: %d
//                """.formatted(
//                    druid.getActiveCount(),
//                    druid.getPoolingCount(),
//                    druid.getMaxActive(),
//                    druid.getWaitThreadCount(),
//                    druid.getConnectCount(),
//                    druid.getErrorCount()
//            );
//        }
//        return "NOT a DruidDataSource";
    }

}
