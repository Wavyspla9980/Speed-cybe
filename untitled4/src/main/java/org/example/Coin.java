package org.example;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.ui.UI;
import javafx.scene.control.Labeled;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;

public class Coin extends  com.almasb.fxgl.entity.Entity {
    public static Entity coins;
    private static int cointNumb;

    public Coin(){
        draw();
        event();
    }

    private static void draw(){
        coins = FXGL.entityBuilder().type(EntityType.COINS).at(200,300).viewWithBBox(new Circle(50,50,20,Color.GOLD)).
            bbox(new HitBox(BoundingShape.circle(30))).
            with(new CollidableComponent(true)).
            buildAndAttach();
    }

    private static void event(){
        PhysicsWorld physicsWorld = FXGL.getPhysicsWorld();

        physicsWorld.addCollisionHandler(new CollisionHandler(EntityType.PLAYERS,EntityType.COINS){
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                int random = new Random().nextInt(4);

                int randomX = new Random().nextInt(200);
                int randomY = new Random().nextInt(320);

                if (random == 3){
                    coins.setPosition(randomX,randomY);
                    Players.speed += 1.1f;
                }

                else if (random == 0){
                    coins.setPosition(randomX,randomY);
                    Players.speed += 2.1f;
                }

                else if (random == 2){
                    coins.setPosition(randomX,randomY);
                    Players.speed += 0.1f;
                }
                else {
                    coins.setPosition(randomX,randomY);
                    Players.speed += 3.1f;
                }

                System.out.println(cointNumb);
                super.onCollisionBegin(a, b);
            }
        });
    }
}
