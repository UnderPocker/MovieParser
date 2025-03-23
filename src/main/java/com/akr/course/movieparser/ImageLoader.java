package com.akr.course.movieparser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ImageLoader {
    public static void loadImage(String imageUrl, Path folder, int frame){
        try {
            // Открываем поток для чтения данных по URL
            InputStream in = new URL(imageUrl).openStream();
            // Копируем содержимое потока в файл на диске
            Path targetPath = folder.resolve(new File("FRAME_" + frame + ".jpeg").toPath());
            if (!Files.exists(targetPath)) {
                Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
            // Закрываем поток
            in.close();
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке изображения: " + e.getMessage());
        }
    }
}
