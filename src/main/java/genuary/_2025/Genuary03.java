package genuary._2025;

import processing.core.PApplet;
import processing.core.PVector;
import static genuary._2025.parameters.Parameters.*;
import static genuary._2025.save.SaveUtil.saveSketch;

public class Genuary03 extends PApplet {
    public static void main(String[] args) {PApplet.main(Genuary03.class);}

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        randomSeed(SEED);
        noiseSeed(floor(random(MAX_INT)));
    }

    @Override
    public void setup() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.green());
        blendMode(BLEND_MODE);
        noLoop();
    }

    @Override
    public void draw() {
        for (int numberOfSizes = MINIMUM_NUMBER_OF_ANGLES; numberOfSizes < MAXIMUM_NUMBER_OF_ANGLES; numberOfSizes++) {
            for (int i = 0; i < NUMBER_OF_STROKES; i++) {
                stroke(STROKE_COLOR_1.red(), random(2) > 1 ? STROKE_COLOR_1.green() : STROKE_COLOR_2.green(), random(2) > 1 ? STROKE_COLOR_1.blue() : STROKE_COLOR_2.blue(), STROKE_COLOR_1.alpha());
                float angle = HALF_PI + (2 * floor(random(2)) - 1) * random(random(PI));
                PVector p = PVector.fromAngle(angle).mult(min(width, height) * RADIUS_FACTOR).add(width / 2f, height / 2f);
                float maxLength = min(width, height) * (2 * RADIUS_FACTOR + LENGTH_GAUSSIAN_FACTOR * randomGaussian());
                for (int k = 0; k < maxLength; k++) {
                    point(p.x, p.y);
                    float scale = map(p.y, 0, height, MINIMUM_NOISE_SCALE, MAXIMUM_NOISE_SCALE);
                    p.add(PVector.fromAngle(angle + 1.5f * PI * (.5f - floor(numberOfSizes * sq(noise(p.x * scale, p.y * scale)))) / numberOfSizes).mult(-.5f));
                }
            }
        }
        saveSketch(this);
    }
}