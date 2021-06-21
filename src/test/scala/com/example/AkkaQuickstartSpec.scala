//#full-example
package com.example

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit

import org.scalatest.wordspec.AnyWordSpecLike
import com.example.Actor.Action

//#definition
class AkkaQuickstartSpec extends ScalaTestWithActorTestKit with AnyWordSpecLike {
//#definition

  // "An Actor" must {
  //   //#test
  //   "call to another Actor" in {
  //     val replyProbe = createTestProbe[Action]()
  //     val underTest = spawn(Actor())
  //     underTest ! Action(10, 1, 0)
  //     replyProbe.expectMessage(Action(10, 10, 9))
  //   }
  //   //#test
  // }

}
//#full-example
