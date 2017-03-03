package com.ajsworton.controllers

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.Initializable
import javafx.beans.binding.Bindings
import javafx.beans.property._
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.input.KeyEvent

import com.ajsworton.Config.Settings

import scalafx.beans.property.StringProperty

/**
  * Created by aworton on 03/03/17.
  */
class ControlPanel extends Initializable{

  var centreNumber: String = Settings.centreNumber

  def getCentreNumber: String = centreNumber

  def setCentreNumber(number: String): Unit = centreNumber = number

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
  }

  @FXML
  def handleTextChange(action: KeyEvent): Unit = action.getSource match{
    case src: TextField => setCentreNumber(src.getText)
  }
}
