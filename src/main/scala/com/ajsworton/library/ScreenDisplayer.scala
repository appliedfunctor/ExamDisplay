package com.ajsworton.library

import javafx.fxml.FXMLLoader
import java.util.Optional
import javafx.geometry.Rectangle2D
import javafx.scene.Parent
import javafx.stage.{Screen, StageStyle}

import com.ajsworton.library.display.JavaFxDisplayBuilder

/**
  * @author Alexander Worton
  */
object ScreenDisplayer {

  //to hold reference to open screens
  var activeScreens: List[String] = Nil

  def toggleScreen(screenHashCode: String) = {

    //if activeScreensContains
    //close window

    //else
    //open new display
    //var bounds: Rectangle2D = screen.getVisualBounds();

  }

  def showScreen(screenHashCode: String) = {
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

  def openNewWindow = {



    val newStage = new JavaFxDisplayBuilder()
      .buildRoot(fxmlView = "Display")
      .buildScene(width = 800, height = 600)
      .buildStage(title = "My New Stage Title", initStyle = StageStyle.UNDECORATED)
      .getDisplay

    if (newStage.isDefined) {
      newStage.get.show()
    }
  }
}
