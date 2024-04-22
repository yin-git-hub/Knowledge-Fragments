package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RateLimitService {
    private static final Map<String, Integer> userIdUsage = new ConcurrentHashMap<>();
    private static final long TWO_MINUTES = 2 * 60 * 1000; // 2分钟的毫秒数

    public static boolean isAllowed(String userId) throws Exception{
        int count = userIdUsage.getOrDefault(userId, 0);
        long currentTime = System.currentTimeMillis();

        // 删除超过两分钟未使用的userId
        userIdUsage.entrySet().removeIf(entry -> currentTime - entry.getValue() > TWO_MINUTES);

        if (count < 20) {
            userIdUsage.put(userId, (int) currentTime);
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        // 使用ScheduledExecutorService定期清理不活跃的userId
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            long currentTime = System.currentTimeMillis();
            userIdUsage.entrySet().removeIf(entry -> currentTime - entry.getValue() > TWO_MINUTES);
        }, 0, 1, TimeUnit.MINUTES);

        // 示例用法
        for (int i = 0; i < 25; i++) {
            boolean allowed = isAllowed("exampleUserId");
            System.out.println("Request " + (i + 1) + ": " + (allowed ? "Allowed" : "Rate Limited"));
        }

        // 关闭定时任务
        scheduler.shutdown();
    }
}
