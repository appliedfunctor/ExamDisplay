package com.ajsworton.controllers

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml
import javafx.fxml.{FXML, Initializable}
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane

/**
  * Created by aworton on 10/03/17.
  */
class Main extends Initializable {

  @FXML
  var contentRoot: Pane = _

  @FXML
  def handleClickEvent(action: MouseEvent): Unit = {
    loadContentPanel(getSelectedPanel(action))
  }

  def loadContentPanel(panelName: Option[String]): Unit = {
    val panel: Option[Node] = getPanel(panelName)
    contentRoot.getChildren.clear()
    panel.foreach(n => contentRoot.getChildren.add(n))
  }

  def getSelectedPanel(action: MouseEvent): Option[String] = action.getSource match {
    case src: Button if src.getId == "settingsBtn" =>  Some("SettingsPanel")
    case src: Button if src.getId == "screensBtn" =>  Some("ScreenPanel")
    case _ => None
  }

  def getPanel(panel: Option[String]): Option[Node] = {
    if (panel.isDefined) {
      try {
        Some((new fxml.FXMLLoader(getClass.getResource("/view/" + panel.get + ".fxml"))).load())
      } catch {
        case err: Exception =>
          println("Failed to load file: " + panel.get)
          println(err.getMessage)
          None

      }
    } else {
      None
    }
  }
  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    //set start panel
    loadContentPanel(Some("SettingsPanel"))
  }
}
