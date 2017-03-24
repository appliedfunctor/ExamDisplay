package com.ajsworton.library.display
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.input.KeyCombination.CONTROL_DOWN
import javafx.scene.input.{KeyCode, KeyCodeCombination}
import javafx.scene.{Parent, Scene}
import javafx.stage.{Screen, Stage, StageStyle}

import com.ajsworton.Config.Settings
import com.ajsworton.library.Scaling

/**
  * @author Alexander Worton
  */
class JavaFxDisplayBuilder extends DisplayBuilder{

  private var builtRoot: Option[Parent] = None
  private var builtScene: Option[Scene] = None
  private var builtStage: Option[Stage] = None

  override def buildRoot(fxmlView: String = "MainPanel"): DisplayBuilder = {
    println(s"/view/$fxmlView.fxml")
    val viewTemplate = new FXMLLoader(getClass.getResource(s"/view/$fxmlView.fxml"))
    val loadedRoot: Parent = viewTemplate.load()
    builtRoot = Some(Scaling.addScalingStyle(loadedRoot))
    this
  }

  override def buildScene(width: Int = 800, height: Int = 500): DisplayBuilder = {
    if (builtRoot.isDefined) {
      val scaling = Scaling.scalingValue(Screen.getPrimary)
      val tempScene = new Scene(builtRoot.get, width * scaling, height * scaling)
      tempScene.getStylesheets.add(getClass.getResource(Settings.styleSheet).toExternalForm)
      builtScene = Some(tempScene)
    }
    this
  }

//  override def buildStage(title: String = "",
//                          initStyle: StageStyle = StageStyle.DECORATED,
//                          fullScreen: Boolean = false,
//                          alwaysOnTop: Boolean = false,
//                          fullScreenExitCombo: KeyCodeCombination
//                          = new KeyCodeCombination(KeyCode.E, CONTROL_DOWN)
//                         ): DisplayBuilder = {
//    if (builtScene.isDefined) {
//      val stage = new Stage()
//      stage.setTitle(title)
//      stage.setAlwaysOnTop(true)
//      stage.setScene(builtScene.get)
//      stage.setOnCloseRequest(e => Platform.exit())
//      stage.sizeToScene()
//      builtStage = Some(stage)
//    }
//    this
//  }


  override def buildStage(title: String,
                          initStyle: StageStyle,
                          fullScreen: Boolean,
                          alwaysOnTop: Boolean,
                          xPos: Int,
                          yPos: Int,
                          fullScreenExitCombo: KeyCodeCombination): DisplayBuilder = {
        if (builtScene.isDefined) {
          val stage = new Stage()
          stage.setTitle(title)
          stage.setAlwaysOnTop(true)
          stage.setScene(builtScene.get)
          stage.setOnCloseRequest(e => Platform.exit())
          stage.sizeToScene()
          builtStage = Some(stage)
        }
        this
      }

  override def getDisplay: Option[Stage] = builtStage

}
