package com.ajsworton

import com.ajsworton.library.Scaling

import scalafx.stage.Screen
import scalafx.scene.layout.GridPane

/**
  * Created by aworton on 03/03/17.
  */
object ContainerFactory {

  def root: GridPane = {
    val scaling: Double = Scaling.scalingValue(Screen.primary)
    val root: GridPane = new GridPane
    root.setId("root");
    root.setStyle("-fx-font-size:" + (scaling * 14) + ";")
    root
  }
}
