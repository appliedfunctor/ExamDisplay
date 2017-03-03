package com.ajsworton

import com.ajsworton.library.Scaling

import scalafx.application.{JFXApp, Platform}
import scalafx.scene.Scene
import javafx.{fxml => jfxf}
import javafx.{scene => jfxs}

import com.ajsworton.Config.Settings

import scalafx.scene.layout.GridPane
import scalafx.stage.Screen

/**
  * Created by aworton on 24/02/17.
  */
object Start extends JFXApp{

  val scaling = Scaling.scalingValue(Screen.primary)

  stage = new JFXApp.PrimaryStage
  stage.setAlwaysOnTop(true)
  stage.setTitle("Exam Display")
  var root: GridPane = ContainerFactory.root
  val viewContent: jfxs.Parent  = jfxf.FXMLLoader.load(getClass.getResource("/view/ControlPanel.fxml"))
  root.getChildren().add(viewContent)

  var scene = new Scene(root, 400 * scaling, 500 * scaling)
  scene.getStylesheets().add(getClass.getResource(Settings.styleSheet).toExternalForm)

  stage.setScene(scene)
  stage.setOnCloseRequest(e => Platform.exit())
}
