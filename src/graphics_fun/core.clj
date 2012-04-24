(ns graphics-fun.core
  (:use [quil.core] [quil.applet]))

;; Ideas
;; Mouse controlled graphics
;; Asteroids
;; Something pretty that draws itself randomly
;; Boucing ball, click mouse to change direction
;; driving style game


(defn setup []
  (smooth)                          ;;Turn on anti-aliasing
  (frame-rate 8)                    ;;Set framerate to 1 FPS
  (background (random 600)))                 ;;Set the background colour to
                                    ;;  a nice shade of grey (200) or
                                    ;;  blue (400) or random (random
;;600)

(defn draw-line []
  (line 25 175))


(defn draw []
  (stroke (random 255))             ;;Set the stroke colour to a random grey
  (stroke-weight (random 10))       ;;Set the stroke thickness randomly
  (fill (random 200))               ;;Set the fill colour to a random grey

  (let [diam (random 100)           ;;Set the diameter to a value between 0 and 100
        x    (random (width))       ;;Set the x coord randomly within the sketch
        y    (random (height))]
    (ellipse x y diam diam)))       ;;Draw a circle at x y with the correct diameter

(defn handle_click []
  )


(defapplet example                  ;;Define a new sketch named example
  :title "Oh so many grey circles"  ;;Set the title of the sketch
  :setup setup                      ;;Specify the setup fn
;  :draw draw                        ;;Specify the draw fn
;  :mouse-clicked handle_click 
  :mouse-pressed draw
  :mouse-released draw
;  :draw line
  :size [360 240])                  ;;You struggle to beat the golden ratio

;; seem to need a java applet to get mouse positions



(defn -main
  "I don't do a whole lot."
  [& args]
  (println "Hello, World!")
  (applet-start example))
