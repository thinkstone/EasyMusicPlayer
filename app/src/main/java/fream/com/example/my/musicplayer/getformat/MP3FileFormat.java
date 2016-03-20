package fream.com.example.my.musicplayer.getformat;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by shiwei on 2016/3/19.
 */
public class MP3FileFormat implements FilenameFilter {

    //得到格式为MP3的音频
    @Override
    public boolean accept(File file, String s) {
        return s.endsWith(".mp3");
    }
}
