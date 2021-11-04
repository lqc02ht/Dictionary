package dictionary.dictionarypro;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Sound {
    public static void speak(String target) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice;
        voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
        }
        try {
            voice.setRate(190);
            voice.setPitch(150);
            voice.setVolume(10);
            voice.speak(target);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
