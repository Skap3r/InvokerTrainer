
package com.dota.invoker.sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Skaper
 */
public class Sound {
    private static InputStream stream;
    private static AudioStream aStream;
    
    public static void playInvoke() {
        try {
            stream = new FileInputStream( new File("src/com/dota/invoker/sound/Invoke.wav"));
            aStream = new AudioStream(stream);
            AudioPlayer.player.start(aStream);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
