package com.ajsworton

import com.ajsworton.library.Scaling

import scalafx.application.{JFXApp, Platform}
import scalafx.scene.Scene
import javafx.{fxml => jfxf}
import javafx.{scene => jfxs}

import com.ajsworton.Config.Settings
import scalafx.Includes._
import scalafx.application.JFXApp.PrimaryStage
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
    stage.sizeToScene()
    stage
  }

  private def initRoot: jfxs.Parent = {
    val viewTemplate = new jfxf.FXMLLoader(getClass.getResource("/view/Main.fxml"))
    val loadedRoot: jfxs.Parent = viewTemplate.load()
    Scaling.addScalingStyle(loadedRoot)
  }

  private def initScene(root: jfxs.Parent): Scene = {
    val scaling = Scaling.scalingValue(Screen.primary)
    val scene = new Scene(root, 800 * scaling, 500 * scaling)
    scene.getStylesheets.add(getClass.getResource(Settings.styleSheet).toExternalForm)
    scene
  }
}
