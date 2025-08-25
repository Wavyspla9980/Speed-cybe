package org.example.scene.world;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.physics.box2d.dynamics.World;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.EntityType;

public class Maps {
    private static Entity wallUp;
    private static Entity wallDown;

    private static Entity wallLeft;
    private static Entity wallRig;

    public Maps(){
        draw();
        event();
    }

    private static void draw(){
        new Players();
        new Coin();

        wallUp = FXGL.entityBuilder().
            type(EntityType.WALL).
            viewWithBBox(new Rectangle(500,20, Color.BLACK)).with(new CollidableComponent(true)).bbox(new HitBox(BoundingShape.box(500,20))).
        at(1,1).buildAndAttach();

        wallDown = FXGL.entityBuilder().
            type(EntityType.WALL).
            viewWithBBox(new Rectangle(500,7, Color.BLACK)).with(new CollidableComponent(true)).bbox(new HitBox(BoundingShape.box(7,20))).
            at(1,494).buildAndAttach();

        wallLeft = FXGL.entityBuilder().
            type(EntityType.WALL).
            viewWithBBox(new Rectangle(7,500, Color.BLACK)).with(new CollidableComponent(true)).bbox(new HitBox(BoundingShape.box(7,500))).
            at(494,1).buildAndAttach();

        wallRig = FXGL.entityBuilder().
            type(EntityType.WALL).
            viewWithBBox(new Rectangle(7,500, Color.BLACK)).with(new CollidableComponent(true)).bbox(new HitBox(BoundingShape.box(7,500))).
            at(-1,1).buildAndAttach();
    }

    private static void event(){
        PhysicsWorld physicsWorld = FXGL.getPhysicsWorld();

        physicsWorld.addCollisionHandler(new CollisionHandler(EntityType.PLAYERS,EntityType.WALL) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                Players.entity.setPosition(200,200);
                Coin.coins.setPosition(200,300);

                Players.speed = 5.4f;
                super.onCollisionBegin(a, b);
            }
        });
    }
}
