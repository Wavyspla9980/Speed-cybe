package org.example.scene.world;


import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.EntityType;

public class Players extends com.almasb.fxgl.entity.Entity {
    public static Entity entity;
    public static float speed = 5.4f;

    public Players(){
        draw();
        key();
        border();
    }

    private static void draw(){
        Rectangle box = new Rectangle(40,40,Color.GRAY);
        entity = FXGL.entityBuilder().type(EntityType.PLAYERS).at(200,200).viewWithBBox(box).
            bbox(new HitBox(BoundingShape.box(10,10))).
            with(new CollidableComponent(true)).buildAndAttach();
    }

    private static void key(){
        Input input = FXGL.getInput();

        input.addAction(new UserAction("D") {
            @Override
            protected void onAction() {
                entity.translateX(speed);
                super.onAction();
            }
        },KeyCode.D);


        input.addAction(new UserAction("A") {
            @Override
            protected void onAction() {
                entity.translateX(-speed);
                super.onAction();
            }
        },KeyCode.A);

        input.addAction(new UserAction("W") {
            @Override
            protected void onAction() {
                entity.translateY(-speed);
                super.onAction();
            }
        },KeyCode.W);

        input.addAction(new UserAction("S") {
            @Override
            protected void onAction() {
                entity.translateY(speed);
                super.onAction();
            }
        },KeyCode.S);
    }
    private static void border(){

    }
}
