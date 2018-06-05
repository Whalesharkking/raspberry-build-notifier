package humannotifier;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTS {
    private static final String VOICENAME = "kevin16";
    private final String text;

    public FreeTTS(final String text) {
        this.text = text;
    }

    public void speak() {
        Voice voice;
        final VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICENAME);
        voice.allocate();
        voice.speak(text);
    }
}
