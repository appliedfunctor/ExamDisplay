package com.ajsworton.controllers

import java.net.URL
import java.util.ResourceBundle
import javafx.beans.property.{ObjectProperty, SimpleObjectProperty}
import javafx.fxml.Initializable

import com.ajsworton.config.Settings
import com.ajsworton.library.Clock

/**
  * @author Alexander Worton.
  */
class DisplayPanel extends Initializable {

  val centreNumber: ObjectProperty[String] = Settings.centreNumber
  def getCentreNumber: String = "Centre: " + Settings.getCentreNumber
  def centreNumberProperty: ObjectProperty[String] = Settings.centreNumber
  def setCentreNumber(number: String): Unit = Settings.setCentreNumber(number)

  val date: ObjectProperty[String] = Clock.dateDisplay
  def getDate: String = Clock.getDateDisplay
  def dateProperty: ObjectProperty[String] = Clock.dateDisplay

  val time: ObjectProperty[String] = Clock.timeDisplay
  def getTime: String = Clock.getTimeDisplay
  def timeProperty: ObjectProperty[String] = Clock.timeDisplay

  override def initialize(location: URL, resources: ResourceBundle): Unit = {}
}
