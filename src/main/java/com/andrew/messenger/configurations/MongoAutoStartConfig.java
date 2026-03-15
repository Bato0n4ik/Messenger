package com.andrew.messenger.configurations;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

@Configuration
public class MongoAutoStartConfig {

    @Value("${mongodb.exe.path}")
    private String mongoExePath;

    @Value("${mongodb.data.path}")
    private String mongoDataPath;

    private Process mongoProcess;

    @PostConstruct
    public void startMongo() {

        if (isPortInUse("localhost", 27017)) {
            System.out.println(">>> MongoDB уже запущена на порту 27017. Используем существующий экземпляр.");
            return;
        }

        try {
            File dbDir = new File(mongoDataPath);
            if (!dbDir.exists()) {
                boolean created = dbDir.mkdirs();
                if (created) {
                    System.out.println("--- Папка для БД создана: " + mongoDataPath);
                }
            }


            ProcessBuilder pb = new ProcessBuilder(
                    mongoExePath,
                    "--dbpath", mongoDataPath,
                    "--port", "27017"
            );


            pb.inheritIO();

            // 3. Запускаем
            this.mongoProcess = pb.start();
            System.out.println("--- MongoDB запущена автоматически ---");

        } catch (IOException e) {
            System.err.println("!!! Не удалось запустить MongoDB: " + e.getMessage());
            System.err.println("Проверьте путь к mongod.exe и права доступа к папке.");
        }
    }

    private boolean isPortInUse(String host, int port) {
        try (Socket ignored = new Socket(host, port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @PreDestroy
    public void stopMongo() {
        if (mongoProcess != null && mongoProcess.isAlive()) {
            mongoProcess.destroy();
            System.out.println("--- MongoDB остановлена вместе с приложением ---");
            try {
                mongoProcess.waitFor(15, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
