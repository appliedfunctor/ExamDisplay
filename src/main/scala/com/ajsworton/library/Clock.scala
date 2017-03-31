package com.ajsworton.library

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javafx.application.Platform
import javafx.beans.property.{ObjectProperty, SimpleObjectProperty}

import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.duration._
/**
  * @author Alexander Worton.
  */
object Clock{

  val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
  val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
  val dateTime = LocalDateTime.now()

  val dateDisplay: ObjectProperty[String] = new SimpleObjectProperty(dateTime.format(dateFormat))
  def getDateDisplay: String = dateDisplay.get
  def dateDisplayProperty: ObjectProperty[String] = dateDisplay
  def setDateDisplay(number: String): Unit = dateDisplay.set(number)

  val timeDisplay: ObjectProperty[String] = new SimpleObjectProperty(dateTime.format(timeFormat))
  def getTimeDisplay: String = timeDisplay.get
  def timeDisplayProperty: ObjectProperty[String] = timeDisplay
  def setTimeDisplay(number: String): Unit = timeDisplay.set(number)

  val system = ActorSystem("clockSystem")
  val updater = system.actorOf(Props(classOf[ClockUpdater], dateFormat, timeFormat))

  import system.dispatcher

  val cancellable =
    system.scheduler.schedule(
      0 milliseconds,
      5 seconds,
      updater,
      Tick)

  def shutDown: Unit = {
    if (!cancellable.isCancelled) cancellable.cancel
    system.terminate()
  }

}

class ClockUpdater(dateFormat: DateTimeFormatter, timeFormat: DateTimeFormatter) extends Actor {
  override def receive: Receive = {
    case Tick => Platform.runLater(() => {
      Clock.setDateDisplay(LocalDateTime.now().format(dateFormat))
      Clock.setTimeDisplay(LocalDateTime.now().format(timeFormat))
    })
    case _ => println("Unknown message received")
  }
}

case class Tick()