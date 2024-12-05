public class Sound {
        private final int pitch;
        private final int duration;
        private final int instrument;
        private final int volume;

        public Sound(float frequency, int octave, int instrument, int volume, int bpm) {
                this.volume = volume;
                this.instrument = instrument;
                this.duration = makeDuration(bpm);
                this.pitch = makePitch(frequency, octave);
        }

        private int makeDuration(int bpm) {
                final float MILLISECONDS_PER_MINUTE = 60000;

                return Math.round(MILLISECONDS_PER_MINUTE / bpm);
        }

        private int makePitch(float frequency, int octave) {
                return Math.round(frequency * (float) Math.pow(2, octave-1));
        }

        public int getPitch() {
                return pitch;
        }

        public int getDuration() {
                return duration;
        }

        public int getInstrument() {
                return instrument;
        }

        public int getVolume() {
                return volume;
        }
}
