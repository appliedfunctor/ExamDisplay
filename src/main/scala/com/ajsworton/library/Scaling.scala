package com.ajsworton.library

import scalafx.stage.Screen

/**
  * Created by aworton on 03/03/17.
  */
object Scaling {

  private val os = System.getProperty("os.name")
  private val baseDpi: Double = 96d

  def scalingValue(screen: Screen): Double = os match{
    case name: String if name.startsWith("Linux")  => scalingFromDpi(screen.dpi).getOrElse(1d)
    case name: String if name.startsWith("Win")  => 1d
    case name: String if name.startsWith("Mac")  => 1d
    case _ => scalingFromDpi(screen.dpi).getOrElse(1d)
  }

  private def scalingFromDpi(dpi: Double): Option[Double] = dpi match {
    case dpi: Double if dpi <= 0 => None
    case _ => Some(dpi / baseDpi)
  }

}
