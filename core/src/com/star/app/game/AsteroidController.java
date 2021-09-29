package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.star.app.game.helpers.ObjectPool;
import com.star.app.game.helpers.Poolable;

public class AsteroidController extends ObjectPool<Asteroid> {


    @Override
    protected Asteroid newObject() {
        return new Asteroid();
    }

    public AsteroidController() {


    }
    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Asteroid a = activeList.get(i);
            a.render(batch);



        }
    }
    public void setup(float x,float y, float vx, float vy,float scale){
        getActiveElement().activate(x,y,vx,vy,scale);
    }

    public void update(float dt){
        for (int i = 0; i <activeList.size() ; i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }
}
