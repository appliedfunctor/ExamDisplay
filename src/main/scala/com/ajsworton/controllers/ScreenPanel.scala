package com.ajsworton.controllers

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.layout.{FlowPane, VBox}
import javafx.stage.Screen

import com.ajsworton.library.ScreenDisplayer

/**
  * Created by aworton on 17/03/17.
  */
class ScreenPanel extends Initializable {

  @FXML
  var screenFlow: FlowPane = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    val primaryScreen = Screen.getPrimary

    var isPrimary = true

    screenFlow.getChildren.add(getScreenNode(primaryScreen, isPrimary))
    isPrimary = false

    Screen.getScreens
      .filtered(screen => screen != primaryScreen)
      .forEach(screen => screenFlow.getChildren.add(getScreenNode(screen, isPrimary)))
  }

  def getScreenNode(screen: Screen, isPrimary: Boolean, number: Int = 2): Node = {
    val node = new VBox()
    node.getStyleClass.add("screen")

    //event: MouseEvent =>

    val screenComponent = new VBox()
    screenComponent.setId(screen.hashCode.toString)
    screenComponent.getStyleClass.add("screenComponent")
    screenComponent.setOnMouseEntered((event: MouseEvent) => event.getSource match {
      case src: VBox => src.getStyleClass.add("screenComponentHovered")
    })

    screenComponent.setOnMouseExited((event: MouseEvent) => event.getSource match {
      case src: VBox => src.getStyleClass.remove("screenComponentHovered")
    })

    screenComponent.setOnMouseClicked((event: MouseEvent) => event.getSource match{
      case src: VBox => ScreenDisplayer.toggleScreen(src.getId)
    })

    var screenLabelText = "Screen 2"
    if (isPrimary) screenLabelText = "Primary\nScreen 1"

    val label = new Label(screenLabelText)
    label.setWrapText(true)
    label.getStyleClass.add("lightColour")
    screenComponent.getChildren.add(label)

    node.getChildren.add(screenComponent)
    node
  }
}
