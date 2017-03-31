package com.ajsworton.config

import javafx.beans.property.{ObjectProperty, SimpleObjectProperty}

/**
  * Created by aworton on 03/03/17.
  */
@SerialVersionUID(1L)
object Settings extends Serializable{

  var styleSheet = "/css/light.css"

  val centreNumber: ObjectProperty[String] = new SimpleObjectProperty("13340")
  def getCentreNumber: String = centreNumber.get
  def centreNumberProperty: ObjectProperty[String] = centreNumber
  def setCentreNumber(number: String): Unit = centreNumber.set(number)

}
