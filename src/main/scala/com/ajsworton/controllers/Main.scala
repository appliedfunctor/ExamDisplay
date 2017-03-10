package com.ajsworton.controllers

import javafx.fxml.FXML
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.input.MouseEvent

/**
  * Created by aworton on 10/03/17.
  */
class Main {
  @FXML
  def handleClickEvent(action: MouseEvent): Unit = action.getSource match {
    case src: Button if(src.getId == "settingsBtn")=>  switchPanel("SettingsPanel", src.getScene)
    case src: Button if(src.getId == "screensBtn")=>  switchPanel("ScreenPanel", src.getScene)
    case _ => println("Unknown source")
  }

  private def switchPanel(panel: String, scene: Scene): Unit ={
    println("Switching to panel " + panel)
    //scene.get
  }
}
