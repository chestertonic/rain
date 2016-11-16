package com.chestertonic.rain.entity;

import com.chestertonic.rain.entity.particle.Particle;
import com.chestertonic.rain.level.Level;



/**
 * Created by Chester on 11/15/2016.
 */
public class Spawn extends Entity {


    public enum Type {
        MOB, PARTICLE
    }

    private Type type;

    public Spawn(int x, int y, Type type, int amount, Level level) {
        this.x = x;
        this.y = y;
        this.type = type;
        for (int i = 0; i < amount; i++) {
            if (type == Type.PARTICLE) {
                level.add(new Particle(x, y, 50));
            }
        }
    }
}
