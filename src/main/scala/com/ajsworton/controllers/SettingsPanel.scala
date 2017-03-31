package com.ajsworton.controllers

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.Initializable
import javafx.beans.property._
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.input.KeyEvent

import com.ajsworton.config.Settings

/**
  * Created by aworton on 03/03/17.
  */
class SettingsPanel extends Initializable{

  @FXML
  var centreInput: TextField = _

  val centreNumber: ObjectProperty[String] = Settings.centreNumber

  def getCentreNumber: String = Settings.getCentreNumber

  def centreNumberProperty: ObjectProperty[String] = Settings.centreNumber

  def setCentreNumber(number: String): Unit = Settings.setCentreNumber(number)

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    centreInput.setText(getCentreNumber)
  }

  @FXML
  def handleTextChange(action: KeyEvent): Unit = action.getSource match{
    case src: TextField => setCentreNumber(src.getText)
  }
}
