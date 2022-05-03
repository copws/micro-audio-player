package dev.cop.map;

import java.io.File;

/**
 * Source audio, will be used to convert MP3, FLAC and other audio formats to WAV format in later versions.
 * 源音频，在之后的版本将被用于 MP3、FLAC 等音频格式与 WAV 格式的转换。
 *
 * @author Copcin
 */

public class Audio {
    public File file;

    /**
     * 构造方法，初始化音频
     * @param sourceAudioFile 源音频文件
     */
    public Audio(File sourceAudioFile){
        this.file = sourceAudioFile;
    }

    /**
     * 构造方法，初始化音频
     * @param sourceAudioPath 源音频文件路径
     */
    public Audio(String sourceAudioPath){
        this.file = new File(sourceAudioPath);
    }

    /**
     * 获取音频文件
     * @return 音频文件
     */
    public File getAudio(){
        return this.file;
    }
}
