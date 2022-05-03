package dev.cop.map;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Micro Audio Player
 *
 * Info:
 * Micro Audio Player is a simple audio player, using Java Native API, does not need to import other jar packages.
 * At present, only WAV audio format has been tested, and MP3, FLAC and other formats will be added later.
 *
 * Micro Audio Player 是一款简易的音频播放器，使用 Java 原生 API，不需要导入其他 jar 包。
 * 目前经过测试的音频格式只有 WAV 一种，后续会添加 MP3、FLAC 等格式的支持。
 *
 * @author Copcin
 */

public class AudioPlayer {
    /**
     * 播放音频
     * @param sourceAudio 源音频 Audio 对象
     */
    public void play(Audio sourceAudio){
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(sourceAudio.getAudio());
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
        // 获取音频编码对象
        AudioFormat audioFormat = audioInputStream.getFormat();

        // 设置数据输入
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
                audioFormat, AudioSystem.NOT_SPECIFIED);
        SourceDataLine sourceDataLine = null;
        try {
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            sourceDataLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        sourceDataLine.start();

        // 从输入流中读取数据发送到混音器
        int count;
        byte[] tempBuffer = new byte[1024];
        while (true) {
            try {
                if ((count = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) == -1) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (count > 0) {
                sourceDataLine.write(tempBuffer, 0, count);
            }
        }

        // 清空数据缓冲,并关闭输入
        sourceDataLine.drain();
        sourceDataLine.close();
    }

    /**
     * 播放音频
     * @param sourceAudioPath 源音频文件路径
     */
    public void play(String sourceAudioPath){
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new Audio(sourceAudioPath).getAudio());
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
        // 获取音频编码对象
        AudioFormat audioFormat = audioInputStream.getFormat();

        // 设置数据输入
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
                audioFormat, AudioSystem.NOT_SPECIFIED);
        SourceDataLine sourceDataLine = null;
        try {
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            sourceDataLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        sourceDataLine.start();

        // 从输入流中读取数据发送到混音器
        int count;
        byte[] tempBuffer = new byte[1024];
        while (true) {
            try {
                if ((count = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) == -1) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (count > 0) {
                sourceDataLine.write(tempBuffer, 0, count);
            }
        }

        // 清空数据缓冲,并关闭输入
        sourceDataLine.drain();
        sourceDataLine.close();
    }
}
