package com.ajsworton

import com.ajsworton.library.Scaling

import scalafx.application.{JFXApp, Platform}
import scalafx.scene.Scene
import javafx.{fxml => jfxf}
import javafx.{scene => jfxs}

import com.ajsworton.Config.Settings
import com.ajsworton.Start.stage
import scalafx.Includes._
import scalafx.application.JFXApp.PrimaryStage
import javafx.scene.layout.{Pane}
import scalafx.stage.Screen

/**
  * Created by aworton on 24/02/17.
  */
object Start extends JFXApp{

  val root = initRoot
  stage = initStage("Exam Display", initScene(root))

  private def initStage(suppliedTitle: String, suppliedScene: Scene): PrimaryStage = {
    val stage = new JFXApp.PrimaryStage{
      title = suppliedTitle
      alwaysOnTop = true
      scene = suppliedScene
      onCloseRequest = e => Platform.exit()
    }
    stage
  }

  private def initRoot: jfxs.Parent = {
    val viewContent: jfxs.Parent  = jfxf.FXMLLoader.load(getClass.getResource("/view/ControlPanel.fxml"))
    Scaling.addScalingStyle(viewContent)
  }

  private def initScene(root: jfxs.Parent): Scene = {
    val scaling = Scaling.scalingValue(Screen.primary)
    val scene = new Scene(root, 800 * scaling, 500 * scaling)
    scene.getStylesheets().add(getClass.getResource(Settings.styleSheet).toExternalForm)
    scene
  }
}
