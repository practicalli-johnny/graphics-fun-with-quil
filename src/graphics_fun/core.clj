(ns graphics-fun.core
  (:use [quil.core] [quil.applet]))

;; Ideas
;; Mouse controlled graphics
;; Asteroids
;; Something pretty that draws itself randomly
;; Boucing ball, click mouse to change direction
;; driving style game


;;; Define an atom to hold the ever changing mouse position
;;; Needs a funtion to set the mouse position!
(def mouse-position (atom [0 0]))


(defn update-mouse-position []
  "Update the mouse position data from the mouse location (mouse-x, mouse-y) relative to the scene"
  (let
      [x (mouse-x)
       y (mouse-y)]
    (println (str "Mouse position:" x y))
    (reset! mouse-position [x y])))



(defn setup-circles-medium []
  (smooth)                          ;;Turn on anti-aliasing
  (frame-rate 8)                    ;;Set framerate to 1 FPS
  (background (random 600)))        ;;Set the background colour to
                                    ;;  a nice shade of grey (200) or
                                    ;;  blue (400) or random (random 600)


(defn flatland []
  "Defines a scene in which to draw in, defining anti-alasing, frame rate and background colour"
  (smooth)
  (frame-rate 1)
  (background 500))


(defn draw-random-lines []
  "I draw lots of circles at random"
  (fill 200)
  (let [
        x-start (random (width))
        y-start (random (height))
        x-end   (random (width))
        y-end   (random (width))]
    (line x-start y-start x-end y-end)))


(defn draw-circles-at-mouse-position []
  "Use the mouse to control where new circles are drawn on the scene"

  (stroke (random 255))
  (stroke-weight (random 10))
  (fill (random 200))

  (let [diam (random 100)
       [mx my] @mouse-position]
    (ellipse mx my diam diam)))


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


;;Define a new sketch to make something appear

(defapplet draw-me-an-applet
  :title "Press my mouse buttons for more circles"
  :setup setup-circles-medium       ;; Set up the scene

  ;; Use the mouse to control when the respective drawing function is called
  :mouse-clicked draw-circles-at-mouse-position
  :mouse-pressed  draw-random-circles  ;; Draw one circle at random
  :mouse-released draw-random-circles  ;; Draw another circle
  :size [360 240])

(defn -main
  "I call any sketches that are defined above."
  [& args]
  (println "Hello, World!")

  ;; A specific call is required for applets, but not for sketches
  (applet-start draw-me-an-applet))
