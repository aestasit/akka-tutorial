package com.aestas.blog.akka.remote;

import akka.actor.ActorRef;
import akka.actor.TypedActor;
import akka.config.Supervision;
import akka.config.TypedActorConfigurator;
import com.aestas.blog.akka.actor.MyAkkaService;
import com.aestas.blog.akka.actor.MyService;

import static akka.actor.Actors.registry;
import static akka.actor.Actors.remote;

/**
 * User: lfi
 */
public class Bootstrap {

    private static TypedActorConfigurator configurator = new TypedActorConfigurator();
    private final static int TIMEOUT = 5000;

    static {
//        System.out.println("entering block");

//        Supervision.SuperviseTypedActor[] targets = new Supervision.SuperviseTypedActor[1];
//        targets[0] = new Supervision.SuperviseTypedActor(MyService.class, MyAkkaService.class, Supervision
//                .permanent(), TIMEOUT);
//
//        configurator.configure(new Supervision.AllForOneStrategy(new Class[]{Exception.class}, 50, 1000), targets);
//        listActors();


        MyService typedActor = TypedActor.newInstance(MyService.class, MyAkkaService.class, 2000);
        remote().registerTypedActor("my-service", typedActor);
        listActors();

    }

    private static void listActors() {
        ActorRef[] actors = registry().actors();
        for (ActorRef ar : actors) {
            System.out.println("ACTOR ID: " + ar.getId());
            // ar.start(); // is that needed?
        }
    }
}
