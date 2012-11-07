(ns graphics-fun.core
  (:use [quil.core] [quil.applet]))

;; Ideas
;; Mouse controlled graphics
;; Asteroids
;; Something pretty that draws itself randomly
;; Boucing ball, click mouse to change direction
;; driving style game


(defn setup-circles-medium []
  (smooth)                          ;;Turn on anti-aliasing
  (frame-rate 8)                    ;;Set framerate to 1 FPS
  (background (random 600)))        ;;Set the background colour to
                                    ;;  a nice shade of grey (200) or
                                    ;;  blue (400) or random (random 600)


(defn flatland []
  "Defines a very basic scene with the slowest possible drawing interval"
  (smooth)
  (frame-rate 1)
  (background 500))


(defn draw-random-lines []
  (fill 200)
  (let [
        x-start (random (width))
        y-start (random (height))
        x-end   (random (width))
        y-end   (random (width))]
    (line x-start y-start x-end y-end)))



(defn draw-random-circles []
  "Draw lots of randomly positioned circles of random size on the screen at the frame rate given in the setup function.

Stroke is the line around a primative (i.e. boarder) and the value is the colour used to draw that primative.  The stroke weight defines how thick the line around a primative is.  I am guessing the maximum value for stroke weight is 100 - to represent 100%."

  (stroke (random 255))             ;;Set the stroke colour to a random grey
  (stroke-weight (random 10))       ;;Set the stroke thickness randomly
  (fill (random 200))               ;;Set the fill colour to a random grey

  (let [diam (random 100)           ;;Set the diameter to a value between 0 and 100
        x    (random (width))       ;;Set the x coord randomly within the sketch
        y    (random (height))]
    (ellipse x y diam diam)))       ;;Draw a circle at x y with the correct diameter

(defn handle_click []
  "Do something interesting when the mouse button is clicked. Called from defsketch or defapplet"
  )


;;; Using the macro defsketch you define what is going to be displayed
;;; on screen.  You can define multiple defsketches, for each one a
;;; seperated 'sketch' will be drawn.

(defsketch a-simple-line
  :title "The sensible one in flat land"
  :setup flatland
  :draw draw-random-lines 
  :size [304 240])


(defsketch never-ending-circles
  :title "Will these circles forever hound me?"
  :setup setup-circles-medium
  :draw draw-random-circles
  :size [304 240] )



(defapplet draw-me-an-applet        ;;Define a new sketch named example
  :title "Oh so many grey circles"  ;;Set the title of the sketch
  :setup setup-circles-medium       ;;Specify the setup fn
  :draw draw-random-circles         ;;Specify the draw fn

;  :mouse-clicked handle_click 
;  :mouse-pressed draw
;  :mouse-released draw
;  :draw line
  :size [360 240])                  ;;You struggle to beat the golden ratio

;; seem to need a java applet to get mouse positions



(defn -main
  "I don't do a whole lot."
  [& args]
  (println "Hello, World!")
  (applet-start draw-me-an-applet)
  )

