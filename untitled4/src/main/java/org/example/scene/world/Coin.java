package org.example.scene.world;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsWorld;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.example.EntityType;

import java.util.Random;

public class Coin extends  com.almasb.fxgl.entity.Entity {
    public static Entity coins;
    private static int cointNumb;

    private static Text textScore;

    public Coin(){
        draw();
        event();
    }

    private static void draw(){
        coins = FXGL.entityBuilder().type(EntityType.COINS).at(200,300).viewWithBBox(new Circle(50,50,20,Color.GOLD)).
            bbox(new HitBox(BoundingShape.circle(36))).
            with(new CollidableComponent(true)).
            buildAndAttach();

        textScore = FXGL.getUIFactoryService().
            newText("Coin: 0",Color.WHITE,15);

        textScore.setTranslateX(200);
        textScore.setTranslateY(11);

        FXGL.getGameScene().addUINode(textScore);
    }

    private static void event(){
        PhysicsWorld physicsWorld = FXGL.getPhysicsWorld();

        physicsWorld.addCollisionHandler(new CollisionHandler(EntityType.PLAYERS,EntityType.COINS){
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                int random = new Random().nextInt(4);

                int randomX = new Random().nextInt(330);
                int randomY = new Random().nextInt(320);

                if (random == 3){
                    coins.setPosition(randomX,randomY);
                    Players.speed += 1.1f;

                    cointNumb+= 1;
                    textScore.setText("Coin "+cointNumb);
                }

                else if (random == 0){
                    coins.setPosition(randomX,randomY);
                    Players.speed += 2.1f;

                    cointNumb+= 1;
                    textScore.setText("Coin "+cointNumb);
                }

                else if (random == 2){
                    coins.setPosition(randomX,randomY);
                    Players.speed += 0.1f;

                    cointNumb+= 1;
                    textScore.setText("Coin "+cointNumb);
                }
                else {
                    coins.setPosition(randomX,randomY);
                    Players.speed += 3.1f;

                    cointNumb+= 1;
                    textScore.setText("Coin "+cointNumb);
                }
                super.onCollisionBegin(a, b);
            }
        });
    }
}
