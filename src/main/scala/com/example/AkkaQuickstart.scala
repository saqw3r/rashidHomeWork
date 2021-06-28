//#full-example
package com.example

import akka.actor.typed.ActorRef
import akka.actor.typed.ActorSystem
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.example.Actor.Action

object Actor
{
  final case class Action(messageBody: String, n: Int, id: Int, numberOfHopsTravelled: Int, timesInMs: Array[(Int, Long)])

  def apply(): Behavior[Action] = Behaviors.setup {
    context =>
    Behaviors.receiveMessage {
      message =>

      val currentTimeMs : Long = System.currentTimeMillis()

      val outputLine = s"actor ${message.id}, message received ${currentTimeMs}"

      //context.log.info(outputLine)
      //println(outputLine)

      val nextMessageId =  message.id+1

      if (message.id <= message.n)
      {
        val newTimesInMs: Array[(Int, Long)] = message.timesInMs :+ ((message.id, currentTimeMs))

        val nextActorRef = context.spawn(Actor(), "actor")
        
        nextActorRef ! Action(message.messageBody, message.n, nextMessageId, message.numberOfHopsTravelled+1, newTimesInMs)
      }
      else
      {
        //println(message.numberOfHopsTravelled)
        for (time <- message.timesInMs)
        {
          println(s"actor ${time._1}, message received ${time._2}")
        }

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
  processor ! Action(messageBody, n, 1, 0, Array[(Int,Long)]())
  //#main-send-messages
}
//#main-class
//#full-example
