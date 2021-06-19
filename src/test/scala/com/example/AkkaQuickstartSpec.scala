//#full-example
package com.example

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit

import org.scalatest.wordspec.AnyWordSpecLike
import com.example.Notifier.Notification
import com.example.Shipper.Shipment

//#definition
class AkkaQuickstartSpec extends ScalaTestWithActorTestKit with AnyWordSpecLike {
//#definition

  "A Shipper" must {
    //#test
    "notify to notifier" in {
      val replyProbe = createTestProbe[Notification]()
      val underTest = spawn(Shipper())
      underTest ! Shipment(123, "T-shirt", 100, replyProbe.ref)
      replyProbe.expectMessage(Notification(123, true))
    }
    //#test
  }

}
//#full-example
