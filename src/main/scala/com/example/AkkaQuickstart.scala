//#full-example
package com.example

import akka.actor.typed.ActorRef
import akka.actor.typed.ActorSystem
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.example.Actor.Action

object Actor
{
  final case class Action(n: Int, id: Int, numberOfHopsTravelled: Int)

  def apply(): Behavior[Action] = Behaviors.setup {
    context =>

    val nextActorRef = context.spawn(Actor(), "actor")

    Behaviors.receiveMessage {
      message =>

      context.log.info(message.toString())

      val nextMessageId =  message.id+1

      if (nextMessageId <= message.n)
      {
        nextActorRef ! Action(message.n, nextMessageId, message.numberOfHopsTravelled+1)
      }

      Behaviors.same
    }
  }
}


//#main-class
object AkkaQuickstart extends App {
  //#actor-system
  val processor: ActorSystem[Action] = ActorSystem(Actor(), "actor")
  //#actor-system

  println("Please, input the n - number of actors:")
  val n:Int = scala.io.StdIn.readInt()

  //#main-send-messages
  processor ! Action(n, 1, 0)
  //#main-send-messages
}
//#main-class
//#full-example
