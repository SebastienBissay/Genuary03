package genuary._2025.parameters;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static processing.core.PConstants.ADD;

public final class Parameters {
    public static final long SEED = 20250103;
    public static final int WIDTH = 2025;
    public static final int HEIGHT = 2025;
    public static final int BLEND_MODE = ADD;
    public static final int MINIMUM_NUMBER_OF_ANGLES = 3;
    public static final int MAXIMUM_NUMBER_OF_ANGLES = 9;
    public static final int NUMBER_OF_STROKES = 3500;
    public static final float RADIUS_FACTOR = .4f;
    public static final float LENGTH_GAUSSIAN_FACTOR = .1f;
    public static final float MINIMUM_NOISE_SCALE = 1 / 100f;
    public static final float MAXIMUM_NOISE_SCALE = 1 / 20f;
    public static final Color BACKGROUND_COLOR = new Color(15);
    public static final Color STROKE_COLOR_1 = new Color(127, 127, 127, 5);
    public static final Color STROKE_COLOR_2 = new Color(127, 255, 255, 5);

    /**
     * Helper method to extract the constants in order to save them to a json file
     *
     * @return a Map of the constants (name -> value)
     */
    public static Map<String, Object> toJsonMap() throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();

        Field[] declaredFields = Parameters.class.getDeclaredFields();
        for (Field field : declaredFields) {
            map.put(field.getName(), field.get(Parameters.class));
        }

        return Collections.singletonMap(Parameters.class.getSimpleName(), map);
    }

    public record Color(float red, float green, float blue, float alpha) {
        public Color(float red, float green, float blue) {
            this(red, green, blue, 255);
        }

        public Color(float grayscale, float alpha) {
            this(grayscale, grayscale, grayscale, alpha);
        }

        public Color(float grayscale) {
            this(grayscale, 255);
        }

        public Color(String hexCode) {
            this(decode(hexCode));
        }

        public Color(Color color) {
            this(color.red, color.green, color.blue, color.alpha);
        }

        public static Color decode(String hexCode) {
            return switch (hexCode.length()) {
                case 2 -> new Color(Integer.valueOf(hexCode, 16));
                case 4 -> new Color(Integer.valueOf(hexCode.substring(0, 2), 16),
                        Integer.valueOf(hexCode.substring(2, 4), 16));
                case 6 -> new Color(Integer.valueOf(hexCode.substring(0, 2), 16),
                        Integer.valueOf(hexCode.substring(2, 4), 16),
                        Integer.valueOf(hexCode.substring(4, 6), 16));
                case 8 -> new Color(Integer.valueOf(hexCode.substring(0, 2), 16),
                        Integer.valueOf(hexCode.substring(2, 4), 16),
                        Integer.valueOf(hexCode.substring(4, 6), 16),
                        Integer.valueOf(hexCode.substring(6, 8), 16));
                default -> throw new IllegalArgumentException();
            };
        }
    }
}
