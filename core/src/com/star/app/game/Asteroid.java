package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;

public class Asteroid implements Poolable {
    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;
    private int hp;
    private int hpMax;
    private float angle;
    private float rotationSpeed;//скорость вращения
    private float scale;
    private boolean active;
    private Circle hitArea;

    private final float BASE_SIZE = 256.0f;
    private final float BASE_RADIUS = BASE_SIZE /2;



    public Asteroid() {
        this.texture = new Texture("Asteroid.png");
        this.position = new Vector2(0,0 );
        this.velocity = new Vector2(0,0);
        this.hitArea = new Circle(0,0,0);
        //this.scale = Math.abs(velocity.x) / 400f * MathUtils.random(0.1f,0.5f);
        this.active= false;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x - 128, position.y - 128, 128, 128, 256, 256, scale, scale,
                angle, 0, 0, 256, 256, false, false);
    }

    public Circle getHitArea() {
        return hitArea;
    }

    public void deactivate(){active = false;}

    public void activate(float x,float y, float vx, float vy,float scale){
        this.position.set(x,y);
        this.velocity.set(vx,vy);
        this.hpMax = 3;
        this.hp = hpMax;
        this.angle = MathUtils.random(0.0f,360.0f);
        this.rotationSpeed = MathUtils.random(-180.0f,180.0f );
        this.hitArea.setPosition(position);
        this.hitArea.setRadius(100);
        this.active = true;
        this.scale = scale;
        this.hitArea.setRadius(BASE_RADIUS * scale * 0.9f);
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean takeDamage(int amount){
        hp-=amount;
        if (hp<=0){
            deactivate();
            return true;
        }
        else return false;
    }

    public void update(float dt) {

        position.mulAdd(velocity,dt);
        angle += rotationSpeed * dt;


        if (position.x < -200) {
            position.x = ScreenManager.SCREEN_WIDTH + 200;
        }
        if (position.x >ScreenManager.SCREEN_WIDTH +200){
            position.x = -200;
        }
        if (position.y< -200) {
            position.y = ScreenManager.SCREEN_HEIGHT + 200;
        }
        if (position.y >ScreenManager.SCREEN_HEIGHT +200){
            position.y = -200;
        }

        hitArea.setPosition(position);

    }


    @Override
    public boolean isActive() {
        return active;
    }
}