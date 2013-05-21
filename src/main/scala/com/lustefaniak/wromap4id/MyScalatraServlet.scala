package com.lustefaniak.wromap4id

import org.scalatra.scalate.ScalateSupport

class MyScalatraServlet extends Wromap4idStack with ScalateSupport{

  val baseUrl = "http://gis.um.wroc.pl/services/ogc_orto/MapServer/WMSServer?LAYERS=ortofotomapa_2011&TRANSPARENT=FALSE&FORMAT=image%2Fpng&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&STYLES=&SRS=CRS%3A84&BBOX=%%BBOX%%&WIDTH=256&HEIGHT=256"

  case class Boundries(val left: Double, val right: Double, val top: Double, val bottom: Double)

  def tile2boundingBox(x: Int, y: Int, zoom: Int) = {

    val north = tile2lat(y, zoom);
    val south = tile2lat(y + 1, zoom);
    val west = tile2lon(x, zoom);
    val east = tile2lon(x + 1, zoom);
    Boundries(west, east, north, south)
  }

  def tile2lon(x: Int, z: Int) = {
    x / Math.pow(2.0, z) * 360.0 - 180;
  }

  def tile2lat(y: Int, z: Int): Double = {
    val n = Math.PI - (2.0 * Math.PI * y) / Math.pow(2.0, z);
    Math.toDegrees(Math.atan(Math.sinh(n)));
  }


  def slippyTileToUrl(x:Int, y:Int, z:Int) = {
    val wmsExtent = tile2boundingBox(x, y, z)

    val bbox = "%.7f,%.7f,%.7f,%.7f".format(wmsExtent.left, wmsExtent.bottom, wmsExtent.right, wmsExtent.top)
    val finalUrl = baseUrl.replaceAll("%%BBOX%%", bbox)

    finalUrl
  }

  get("/ortomap/:z/:x/:y") {
    val x = params("x").toInt
    val y = params("y").toInt
    val z = params("z").toInt

    val finalUrl = slippyTileToUrl(x, y, z)

    redirect(finalUrl)
  }



  get("/") {
    contentType = "text/html"

    layoutTemplate("index.ssp")
  }


}
