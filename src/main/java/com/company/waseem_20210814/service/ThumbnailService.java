package com.company.waseem_20210814.service;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.company.waseem_20210814.dto.VideoThumbnailDto;
import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;

@Service
public class ThumbnailService {

    private VideoThumbnailService videoThumbnailService;

    public ThumbnailService(final VideoThumbnailService videoThumbnailService) {
        this.videoThumbnailService = videoThumbnailService;
    }

    private static String outputFilePrefix = System.getProperty("user.home")+"/thumbnail/thumbnail";

    @Async
    public void createThumbnailAsync(String inputFilename, Integer videoId) {
        IMediaReader mediaReader = ToolFactory.makeReader(inputFilename);
        mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
        mediaReader.addListener(new ImageSnapListener(videoId));
        while (mediaReader.readPacket() == null);
    }

    private class ImageSnapListener extends MediaListenerAdapter {

        private Integer videoId;

        public ImageSnapListener(final Integer videoId) {
            this.videoId = videoId;
        }

        public void onVideoPicture(IVideoPictureEvent event) {
            if (event.getTimeStamp() == 0) {
                var image = event.getImage();

                List<VideoThumbnailDto> thumbnailDtoList = new ArrayList<>(3);
                var outputPath = dumpImageToFile(resize(image, 64, 64), 64);
                System.out.println(outputPath);
                thumbnailDtoList.add(new VideoThumbnailDto(outputPath, 64));

                outputPath = dumpImageToFile(resize(image, 128, 128), 128);
                System.out.println(outputPath);
                thumbnailDtoList.add(new VideoThumbnailDto(outputPath, 128));

                outputPath = dumpImageToFile(resize(image, 256, 256), 256);
                System.out.println(outputPath);
                thumbnailDtoList.add(new VideoThumbnailDto(outputPath, 256));

                videoThumbnailService.save(thumbnailDtoList, videoId);
            }

        }

        public BufferedImage resize(BufferedImage img, int newW, int newH) {
            Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();

            return dimg;
        }

        private String dumpImageToFile(BufferedImage image, int size) {
            try {
                String outputFilename = outputFilePrefix + "_"+ size + "_" + System.currentTimeMillis() + ".png";
                ImageIO.write(image, "png", new File(outputFilename));
                return outputFilename;
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

    }
}
