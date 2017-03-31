package com.ajsworton.library

import javafx.fxml.FXMLLoader
import java.util.Optional
import javafx.application.Platform
import javafx.concurrent.Task
import javafx.scene.{Cursor, Parent}
import javafx.stage.{Screen, StageStyle}

import com.ajsworton.config.Settings
import com.ajsworton.library.display.JavaFxDisplayBuilder

/**
  * @author Alexander Worton
  */
object ScreenDisplayer {

  //to hold reference to open screens
  var activeScreens: List[String] = Nil

  def toggleScreen(screenHashCode: String): Unit = {

    //if activeScreensContains
    //close window

    //else
    //open new display
    //var bounds: Rectangle2D = screen.getVisualBounds();
    showScreen(screenHashCode)

  }

  def showScreen(screenHashCode: String): Unit = {
    val screen: Optional[Screen] = getScreen(screenHashCode)
    if(screen.isPresent) {
      openNewWindow
    } else {
      println(s"Screen $screenHashCode is unknown")
    }
  }

  def getScreen(screenHashCode: String): Optional[Screen] = {
    Screen.getScreens
          .stream()
          .filter(screen => screen.hashCode.toString == screenHashCode)
          .findFirst()
  }

  private def initRoot(resource: String): Parent = {
    val viewTemplate = new FXMLLoader(getClass.getResource(resource))
    val loadedRoot: Parent = viewTemplate.load()
    Scaling.addScalingStyle(loadedRoot)
  }

  def openNewWindow: Unit = {
    Settings.primaryStage.get.getScene.setCursor(Cursor.WAIT)

    val newStage = new JavaFxDisplayBuilder()
      .buildRoot(fxmlView = "DisplayPanel")
      .buildScene()
      .buildStage(title = "Display", initStyle = StageStyle.UNDECORATED)
      .getDisplay


    val openWindow: Task[Unit] = () => {
      if (newStage.isDefined) {
        newStage.get.show()
        newStage.get.setMaximized(true)
      }
    }

    openWindow.setOnSucceeded(e => Settings.primaryStage.foreach(_.getScene.setCursor(Cursor.DEFAULT)))
    openWindow.setOnFailed(e => Settings.primaryStage.foreach(_.getScene.setCursor(Cursor.DEFAULT)))

    val thread: Thread = new Thread(openWindow)
    thread.run()


  }
}
