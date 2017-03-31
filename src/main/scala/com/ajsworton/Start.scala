package com.ajsworton

import javafx.application.Application
import javafx.scene.Cursor
import javafx.stage.Stage

import com.ajsworton.config.Settings
import com.ajsworton.library.display.JavaFxDisplayBuilder

/**
  * @author Alexander Worton.
  */
class Start extends Application{

  override def start(primaryStage: Stage) {

    val newStage = new JavaFxDisplayBuilder()
      .buildRoot("MainPanel")
      .buildScene()
      .buildStage(title = "Exam Display", masterWindow = true)
      .getDisplay

    if (newStage.isDefined) {
        Settings.primaryStage = newStage
        newStage.get.show()
    }
  }
}

object Start {
  def main(args: Array[String]) {
    Application.launch(classOf[Start], args: _*)
  }
}
