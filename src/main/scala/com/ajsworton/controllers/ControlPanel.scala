package com.ajsworton.controllers

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.Initializable
import javafx.beans.property._
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.input.KeyEvent

import com.ajsworton.Config.Settings

/**
  * Created by aworton on 03/03/17.
  */
class ControlPanel extends Initializable{

  val centreNumber: ObjectProperty[String] = new SimpleObjectProperty(Settings.centreNumber)

  def getCentreNumber: String = centreNumber.get

  def centreNumberProperty: ObjectProperty[String] = centreNumber

  def setCentreNumber(number: String): Unit = centreNumber.set(number)

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
  }

  @FXML
  def handleTextChange(action: KeyEvent): Unit = action.getSource match{
    case src: TextField => setCentreNumber(src.getText)
  }
}
