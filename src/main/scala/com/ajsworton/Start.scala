package com.ajsworton

import javafx.application.Application
import javafx.stage.Stage

import com.ajsworton.library.display.JavaFxDisplayBuilder

/**
  * Created by aworton on 24/02/17.
  */
class Start extends Application{

  override def start(primaryStage: Stage) {

    val newStage = new JavaFxDisplayBuilder()
      .buildRoot("MainPanel")
      .buildScene(800, 500)
      .buildStage(title = "Exam Display")
      .getDisplay

    if (newStage.isDefined) {
        newStage.get.show()
    }
  }

}
