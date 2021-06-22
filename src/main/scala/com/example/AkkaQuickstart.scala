//#full-example
package com.example

import akka.actor.typed.ActorRef
import akka.actor.typed.ActorSystem
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.example.Actor.Action

object Actor
{
  final case class Action(messageBody: String, n: Int, id: Int, numberOfHopsTravelled: Int)

  def apply(): Behavior[Action] = Behaviors.setup {
    context =>

    val nextActorRef = context.spawn(Actor(), "actor")

    Behaviors.receiveMessage {
      message =>

      context.log.info(message.toString())

      val nextMessageId =  message.id+1

      if (nextMessageId <= message.n)
      {
        nextActorRef ! Action(message.messageBody, message.n, nextMessageId, message.numberOfHopsTravelled+1)
      }
      else
      {
        println(message.numberOfHopsTravelled)
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

  println("Please, input the message for sending:")
  val messageBody:String = scala.io.StdIn.readLine()

  //#main-send-messages
  processor ! Action(messageBody, n, 1, 0)
  //#main-send-messages
}
//#main-class
//#full-example
