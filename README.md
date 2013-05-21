# Wroclaw Ortho Map #

Ever wanted to use Id editor to map your Wroclaw neighbourhood but had no high quality maps? Now You can use Official Wroclaw Ortofotomap!

This web application translates slippy map tilenames to their WMS representation on [Wrocław Spatial Information System](http://geoportal.wroclaw.pl) website.

You can use deployed application at [http://wroclaw.herokuapp.com/](http://wroclaw.herokuapp.com/).

## Build & Run ##

```sh
$ git clone https://github.com/lustefaniak/wroclaworthomap.git wroclaworthomap
$ cd wroclaworthomap
$ sbt
> container:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.
