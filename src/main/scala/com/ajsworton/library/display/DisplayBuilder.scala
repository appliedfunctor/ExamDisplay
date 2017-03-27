package com.ajsworton.library.display

import javafx.scene.input.KeyCombination.CONTROL_DOWN
import javafx.scene.input.{KeyCode, KeyCodeCombination}
import javafx.stage.{Stage, StageStyle}

/**
  * @author Alexander Worton
  */
trait DisplayBuilder {

  val defaultWidth = 800
  val defaultHeight = 500

  def buildRoot(fxmlView: String): DisplayBuilder

  def buildScene(width: Int = defaultWidth, height: Int = defaultHeight): DisplayBuilder

  def buildStage(title: String = "",
                 initStyle: StageStyle = StageStyle.DECORATED,
                 fullScreen: Boolean = false,
                 alwaysOnTop: Boolean = false,
                 xPos: Int = 0,
                 yPos: Int = 0,
                 fullScreenExitCombo: KeyCodeCombination
                 = new KeyCodeCombination(KeyCode.E, CONTROL_DOWN),
                 masterWindow: Boolean = false
                ): DisplayBuilder

  def getDisplay: Option[Stage]

}
