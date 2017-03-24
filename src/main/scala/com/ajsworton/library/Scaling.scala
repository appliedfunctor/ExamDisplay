package com.ajsworton.library

import javafx.stage.Screen
import javafx.{scene => jfxs}

/**
  * @author Alexander Worton
  */
object Scaling {

  private val os = System.getProperty("os.name")
  private val baseDpi: Double = 96d

  def scalingValue(screen: Screen): Double = os match{
    case name: String if name.startsWith("Linux")  => scalingFromDpi(screen.getDpi).getOrElse(1d)
    case name: String if name.startsWith("Win")  => 1d
    case name: String if name.startsWith("Mac")  => 1d
    case _ => scalingFromDpi(screen.getDpi).getOrElse(1d)
  }

  private def scalingFromDpi(dpi: Double): Option[Double] = dpi match {
    case dpi: Double if dpi <= 0 => None
    case _ => Some(dpi / baseDpi)
  }

  def addScalingStyle(viewContent: jfxs.Parent): jfxs.Parent = {
    val scaling: Double = Scaling.scalingValue(Screen.getPrimary)
    viewContent.setStyle("-fx-font-size:" + (scaling * 14) + ";")
    viewContent
  }

}
