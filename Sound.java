public class Sound {
        private final int pitch;
        private final int duration;
        private final int instrument;
        private final int volume;

        public Sound(int offset, int octave, int instrument, int volume, int bpm) {
                this.volume = volume;
                this.instrument = instrument;
                this.duration = makeDuration(bpm);
                this.pitch = makePitch(offset, octave);
        }

        private int makeDuration(int bpm) {
                final float MILLISECONDS_PER_MINUTE = 60000;

                return Math.round(MILLISECONDS_PER_MINUTE / bpm);
        }

        private int makePitch(int offset, int octave) {
                return 12 * (octave + 1) + offset;
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
