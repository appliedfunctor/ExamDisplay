package com.ajsworton.controllers

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.Initializable

import scalafx.stage.Screen

/**
  * Created by aworton on 17/03/17.
  */
class ScreenPanel extends Initializable {

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    Screen.screens
  }
}
